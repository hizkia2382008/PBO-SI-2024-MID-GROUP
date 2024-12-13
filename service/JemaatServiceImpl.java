package service;

import entity.Entities;
import repository.JemaatRepositoryImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JemaatServiceImpl implements JemaatService {

    public static final String JEMAAT_FILE = "jemaat.csv";
    public static final String PERSEMBAHAN_FILE = "Persembahan.csv";
    public static final String PERBENDAHARAAN_FILE = "Perbendaharaan.csv";
    private final JemaatRepositoryImpl jemaatRepository;

    public JemaatServiceImpl(JemaatRepositoryImpl jemaatRepository) {
        this.jemaatRepository = jemaatRepository;
    }

    @Override
    public void tambahJemaat(String nama, String alamat) {
        Entities.Jemaat jemaat = new Entities.Jemaat(nama, alamat);
        jemaat.setId(generateId(JEMAAT_FILE));
        writeToCSV(JEMAAT_FILE, jemaatToCSV(jemaat), true);
    }

    @Override
    public void tambahJemaat(String nama, String alamat, double gaji) {
        Entities.Jemaat jemaat = new Entities.Jemaat(nama, alamat);
        jemaat.setId(generateId(JEMAAT_FILE));
        jemaat.hitungPerpuluhan(gaji);
        writeToCSV(JEMAAT_FILE, jemaatToCSV(jemaat), true);
    }

    @Override
    public void hitungPerpuluhan(String nama, double gaji) {
        List<Entities.Jemaat> jemaatList = parseJemaatList(readFromCSV(JEMAAT_FILE));
        jemaatList.stream()
                .filter(jemaat -> jemaat.getNama().equalsIgnoreCase(nama))
                .forEach(jemaat -> jemaat.hitungPerpuluhan(gaji));
        writeListToFile(JEMAAT_FILE, jemaatListToCSV(jemaatList));
    }

    @Override
    public void catatPersembahan(double persembahan) {
        Entities.Persembahan persembahanEntity = new Entities.Persembahan(persembahan);
        persembahanEntity.setId(generateId(PERSEMBAHAN_FILE));
        writeToCSV(PERSEMBAHAN_FILE, persembahanToCSV(persembahanEntity), true);
    }

    @Override
    public void hitungSaldoPerbendaharaan(double kasSebelumnya, double pengeluaran) {
        List<Entities.Persembahan> persembahanData = parsePersembahanList(readFromCSV(PERSEMBAHAN_FILE));
        double totalPersembahan = persembahanData.stream().mapToDouble(Entities.Persembahan::getJumlah).sum();
        double totalKas = kasSebelumnya + totalPersembahan - pengeluaran;

        Entities.Perbendaharaan perbendaharaan = new Entities.Perbendaharaan(kasSebelumnya, totalKas, pengeluaran);
        perbendaharaan.setId(generateId(PERBENDAHARAAN_FILE));
        writeToCSV(PERBENDAHARAAN_FILE, perbendaharaanToCSV(perbendaharaan), true);
    }

    @Override
    public List<Entities.Jemaat> tampilkanJemaat() {
        return parseJemaatList(readFromCSV(JEMAAT_FILE));
    }

    @Override
    public List<String> tampilkanPerbendaharaan() {
        return readFromCSV(PERBENDAHARAAN_FILE);
    }

    @Override
    public void updateJemaat(String namaLama, String namaBaru, String alamatBaru) {
        List<Entities.Jemaat> jemaatList = parseJemaatList(readFromCSV(JEMAAT_FILE));
        jemaatList.stream()
                .filter(jemaat -> jemaat.getNama().equals(namaLama))
                .forEach(jemaat -> {
                    jemaat.setNama(namaBaru);
                    jemaat.setAlamat(alamatBaru);
                });
        writeListToFile(JEMAAT_FILE, jemaatListToCSV(jemaatList));
    }

    @Override
    public void hapusJemaat(String nama) {
        List<Entities.Jemaat> jemaatList = parseJemaatList(readFromCSV(JEMAAT_FILE));
        jemaatList.removeIf(jemaat -> jemaat.getNama().equals(nama));
        writeListToFile(JEMAAT_FILE, jemaatListToCSV(jemaatList));
    }

    @Override
    public List<String> cariJemaat(String nama) {
        List<Entities.Jemaat> jemaatList = parseJemaatList(readFromCSV(JEMAAT_FILE));
        List<String> result = new ArrayList<>();
        jemaatList.stream()
                .filter(jemaat -> jemaat.getNama().equalsIgnoreCase(nama))
                .forEach(jemaat -> result.add(jemaatToCSV(jemaat)));
        return result;
    }

    @Override
    public double hitungTotalPersembahan() {
        List<Entities.Persembahan> persembahanData = parsePersembahanList(readFromCSV(PERSEMBAHAN_FILE));
        return persembahanData.stream().mapToDouble(Entities.Persembahan::getJumlah).sum();
    }

    @Override
    public void rekapitulasiPersembahan() {
        double totalPersembahan = hitungTotalPersembahan();
        System.out.println("Total Persembahan: " + totalPersembahan);
    }

    private void writeToCSV(String fileName, String data, boolean append) {
        try {
            ensureFileExists(fileName);
            try (FileWriter writer = new FileWriter(fileName, append)) {
                writer.write(data + "\n");
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menulis ke file: " + fileName);
        }
    }

    private List<String> readFromCSV(String fileName) {
        List<String> data = new ArrayList<>();
        try {
            ensureFileExists(fileName);
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    data.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + fileName);
        }
        return data;
    }

    private void writeListToFile(String fileName, List<String> data) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String line : data) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menulis ke file: " + fileName);
        }
    }

    private void ensureFileExists(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + fileName);
            }
        }
    }

    private List<Entities.Jemaat> parseJemaatList(List<String> data) {
        List<Entities.Jemaat> jemaatList = new ArrayList<>();
        for (String line : data) {
            String[] parts = line.split(",");
            Entities.Jemaat jemaat = new Entities.Jemaat(
                    Integer.parseInt(parts[0]),
                    parts[1],
                    parts[2],
                    Double.parseDouble(parts[3])
            );
            jemaatList.add(jemaat);
        }
        return jemaatList;
    }

    private List<Entities.Persembahan> parsePersembahanList(List<String> data) {
        List<Entities.Persembahan> persembahanList = new ArrayList<>();
        for (String line : data) {
            String[] parts = line.split(",");
            Entities.Persembahan persembahan = new Entities.Persembahan(
                    Integer.parseInt(parts[0]),
                    Double.parseDouble(parts[1])
            );
            persembahanList.add(persembahan);
        }
        return persembahanList;
    }

    private String jemaatToCSV(Entities.Jemaat jemaat) {
        return jemaat.getId() + "," + jemaat.getNama() + "," + jemaat.getAlamat() + "," + jemaat.getPerpuluhan();
    }

    private List<String> jemaatListToCSV(List<Entities.Jemaat> jemaatList) {
        List<String> data = new ArrayList<>();
        for (Entities.Jemaat jemaat : jemaatList) {
            data.add(jemaatToCSV(jemaat));
        }
        return data;
    }

    private String persembahanToCSV(Entities.Persembahan persembahan) {
        return persembahan.getId() + "," + persembahan.getJumlah();
    }

    private String perbendaharaanToCSV(Entities.Perbendaharaan perbendaharaan) {
        return perbendaharaan.getId() + ","
                + perbendaharaan.getKasSebelumnya() + ","
                + perbendaharaan.getTotalKas() + ","
                + perbendaharaan.getPengeluaran();
    }

    private int generateId(String fileName) {
        List<String> data = readFromCSV(fileName);
        if (data.isEmpty()) {
            return 1;
        }
        String lastLine = data.get(data.size() - 1);
        String[] parts = lastLine.split(",");
        return Integer.parseInt(parts[0]) + 1;
    }
}
