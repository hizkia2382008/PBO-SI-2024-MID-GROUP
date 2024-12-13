package service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JemaatServiceImpl implements JemaatService {

    private static final String JEMAAT_FILE = "jemaat.csv";
    private static final String PERSEMBAHAN_FILE = "Persembahan.csv";
    private static final String PERBENDAHARAAN_FILE = "Perbendaharaan.csv";

    @Override
    public void tambahJemaat(String nama, String alamat) {
        writeToCSV(JEMAAT_FILE, nama + "," + alamat, true);
    }

    @Override
    public void hitungPerpuluhan(String nama, double gaji) {
        double perpuluhan = gaji * 0.1;
        writeToCSV(JEMAAT_FILE, nama + ",Perpuluhan: " + perpuluhan, true);
    }

    @Override
    public void catatPersembahan(double persembahan) {
        double hasilBagi2 = persembahan / 2;
        writeToCSV(PERSEMBAHAN_FILE, String.valueOf(hasilBagi2), true);
    }

    @Override
    public void hitungSaldoPerbendaharaan(double kasSebelumnya, double pengeluaran) {
        List<String> persembahanData = readFromCSV(PERSEMBAHAN_FILE);
        double totalPersembahan = persembahanData.stream().mapToDouble(Double::parseDouble).sum();
        double totalKas = kasSebelumnya + totalPersembahan - pengeluaran;
        writeToCSV(PERBENDAHARAAN_FILE, String.valueOf(totalKas), true);
    }

    @Override
    public List<String> tampilkanJemaat() {
        return readFromCSV(JEMAAT_FILE);
    }

    @Override
    public List<String> tampilkanPerbendaharaan() {
        return readFromCSV(PERBENDAHARAAN_FILE);
    }

    @Override
    public void updateJemaat(String namaLama, String namaBaru, String alamatBaru) {
        List<String> jemaatList = readFromCSV(JEMAAT_FILE);
        List<String> updatedList = new ArrayList<>();
        jemaatList.forEach(jemaat -> {
            String[] parts = jemaat.split(",");
            if (parts[0].equals(namaLama)) {
                updatedList.add(namaBaru + "," + alamatBaru);
            } else {
                updatedList.add(jemaat);
            }
        });
        try (FileWriter writer = new FileWriter(JEMAAT_FILE)) {
            for (String updatedData : updatedList) {
                writer.write(updatedData + "\n");
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat mengupdate data jemaat.");
        }
    }

    @Override
    public void hapusJemaat(String nama) {
        List<String> jemaatList = readFromCSV(JEMAAT_FILE);
        jemaatList.removeIf(jemaat -> jemaat.split(",")[0].equals(nama));
        try (FileWriter writer = new FileWriter(JEMAAT_FILE)) {
            for (String jemaat : jemaatList) {
                writer.write(jemaat + "\n");
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menghapus data jemaat.");
        }
    }

    @Override
    public List<String> cariJemaat(String nama) {
        List<String> jemaatList = readFromCSV(JEMAAT_FILE);
        List<String> result = new ArrayList<>();
        jemaatList.stream()
                .filter(jemaat -> jemaat.split(",")[0].equalsIgnoreCase(nama))
                .forEach(result::add);
        return result;
    }

    @Override
    public void rekapitulasiPersembahan() {
        List<String> persembahanData = readFromCSV(PERSEMBAHAN_FILE);
        double totalPersembahan = persembahanData.stream().mapToDouble(Double::parseDouble).sum();
        System.out.println("Total Persembahan: " + totalPersembahan);
    }

    private void writeToCSV(String fileName, String data, boolean append) {
        try (FileWriter writer = new FileWriter(fileName, append)) {
            writer.write(data + "\n");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menulis ke file: " + fileName);
        }
    }

    private List<String> readFromCSV(String fileName) {
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + fileName);
        }
        return data;
    }
}
