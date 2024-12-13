package view;

import entity.Entities.Jemaat;
import repository.JemaatRepository;
import repository.JemaatRepositoryImpl;
import service.JemaatServiceImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AplikasiPerpuluhanPerbendaharaanViewImpl implements AplikasiPerpuluhanPerbendaharaanView {

    private Scanner scanner = new Scanner(System.in);
    private JemaatRepository jemaatRepository = new JemaatRepositoryImpl();
    private static final String PERBENDAHARAAN_FILE = JemaatServiceImpl.PERBENDAHARAAN_FILE;
    private static final String PERSEMBAHAN_FILE = JemaatServiceImpl.PERSEMBAHAN_FILE;

    @Override
    public void tampilkanMenu() {
        System.out.println("\n=== Aplikasi Perpuluhan dan Perbendaharaan Jemaat ===");
        System.out.println("1. Tambah Jemaat");
        System.out.println("2. Hitung Perpuluhan");
        System.out.println("3. Input Persembahan");
        System.out.println("4. Hitung Perbendaharaan");
        System.out.println("5. Tampilkan Data Jemaat");
        System.out.println("6. Tampilkan Perbendaharaan");
        System.out.println("7. Update Data Jemaat");
        System.out.println("8. Hapus Data Jemaat");
        System.out.println("9. Cari Jemaat");
        System.out.println("10. Rekapitulasi Persembahan");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    @Override
    public void tampilkanJemaat(List<Jemaat> jemaatList) {
        if (jemaatList.isEmpty()) {
            System.out.println("Belum ada data jemaat.");
        } else {
            System.out.println("Daftar anggota jemaat:");
            for (int i = 0; i < jemaatList.size(); i++) {
                Jemaat jemaat = jemaatList.get(i);
                System.out.println((i + 1) + ". " + jemaat.getNama() + ", " + jemaat.getAlamat() + ", Perpuluhan: " + jemaat.getPerpuluhan());
            }
        }
    }

    @Override
    public void tampilkanPerbendaharaan(List<String> perbendaharaanList) {
        if (perbendaharaanList.isEmpty()) {
            System.out.println("Belum ada data perbendaharaan.");
        } else {
            perbendaharaanList.forEach(System.out::println);
        }
    }

    @Override
    public void tampilkanPesan(String message) {
        System.out.println(message);
    }

    @Override
    public void tampilkanRekapitulasiPersembahan(double totalPersembahan) {
        System.out.println("Total Persembahan: " + totalPersembahan);
    }

    @Override
    public void prosesMenu() {
        while (true) {
            tampilkanMenu();
            int pilihan = (int) getInputDouble();
            scanner.nextLine(); // Consume the newline character
            switch (pilihan) {
                case 1:
                    tambahJemaat();
                    break;
                case 2:
                    hitungPerpuluhan();
                    break;
                case 3:
                    inputPersembahan();
                    break;
                case 4:
                    hitungPerbendaharaan();
                    break;
                case 5:
                    tampilkanJemaat(jemaatRepository.findAll());
                    break;
                case 6:
                    tampilkanPerbendaharaan(readFromCSV(PERBENDAHARAAN_FILE));
                    break;
                case 7:
                    updateDataJemaat();
                    break;
                case 8:
                    hapusDataJemaat();
                    break;
                case 9:
                    cariJemaat();
                    break;
                case 10:
                    tampilkanRekapitulasiPersembahan(getTotalPersembahan());
                    break;
                case 0:
                    keluar();
                    break;
                default:
                    tampilkanPesan("Pilihan tidak valid.");
            }
        }
    }

    @Override
    public String getInput() {
        return scanner.nextLine();
    }

    @Override
    public double getInputDouble() {
        return scanner.nextDouble();
    }

    private void tambahJemaat() {
        System.out.print("Masukkan nama jemaat: ");
        String nama = getInput();
        System.out.print("Masukkan alamat jemaat: ");
        String alamat = getInput();
        Jemaat jemaat = new Jemaat(nama, alamat); // Create Jemaat object
        simpanDataJemaat(jemaat); // Save the object
        tampilkanPesan("Data jemaat berhasil disimpan.");
    }

    private void hitungPerpuluhan() {
        List<Jemaat> jemaatList = jemaatRepository.findAll();
        tampilkanJemaat(jemaatList);
        if (!jemaatList.isEmpty()) {
            System.out.print("Pilih jemaat: ");
            int jemaatIndex = (int) getInputDouble() - 1;
            if (jemaatIndex >= 0 && jemaatIndex < jemaatList.size()) {
                System.out.print("Masukkan gaji bulan ini: ");
                double gajiBulan = getInputDouble();
                Jemaat jemaat = jemaatList.get(jemaatIndex);
                jemaat.hitungPerpuluhan(gajiBulan); // Calculate the tithe
                jemaatRepository.update(jemaat); // Update the jemaat data
                tampilkanPesan("Perpuluhan dihitung dan disimpan.");
            }
        }
    }

    private void inputPersembahan() {
        System.out.print("Masukkan jumlah persembahan: ");
        double persembahan = getInputDouble();
        hitungPersembahan(persembahan);
        tampilkanPesan("Persembahan dihitung dan disimpan.");
    }

    private void hitungPerbendaharaan() {
        System.out.print("Masukkan jumlah kas sebelumnya: ");
        double kasSebelumnya = getInputDouble();
        System.out.print("Masukkan jumlah pengeluaran: ");
        double pengeluaran = getInputDouble();
        hitungPerbendaharaan(kasSebelumnya, pengeluaran);
        tampilkanPesan("Perbendaharaan dihitung dan disimpan.");
    }

    private void updateDataJemaat() {
        System.out.print("Masukkan nama jemaat yang akan diupdate: ");
        String namaLama = getInput();
        Jemaat jemaatLama = jemaatRepository.findByName(namaLama);
        if (jemaatLama != null) {
            System.out.print("Masukkan nama baru: ");
            String namaBaru = getInput();
            System.out.print("Masukkan alamat baru: ");
            String alamatBaru = getInput();
            double perpuluhanLama = jemaatLama.getPerpuluhan();
            Jemaat jemaatBaru = new Jemaat(jemaatLama.getId(), namaBaru, alamatBaru, perpuluhanLama);
            jemaatRepository.update(jemaatBaru);
            tampilkanPesan("Data jemaat berhasil diupdate.");
        } else {
            tampilkanPesan("Jemaat dengan nama tersebut tidak ditemukan.");
        }
    }



    private void hapusDataJemaat() {
        System.out.print("Masukkan nama jemaat yang akan dihapus: ");
        String namaHapus = getInput();
        jemaatRepository.deleteByName(namaHapus);
        tampilkanPesan("Data jemaat berhasil dihapus.");
    }

    private void cariJemaat() {
        System.out.print("Masukkan nama jemaat yang dicari: ");
        String namaCari = getInput();
        Jemaat jemaat = jemaatRepository.findByName(namaCari);
        if (jemaat != null) {
            System.out.println("Ditemukan jemaat: " + jemaat.getNama());
            System.out.println("Alamat: " +jemaat.getAlamat());
            System.out.println("Perpuluhan: "+ jemaat.getPerpuluhan());
        } else {
            System.out.println("Jemaat tidak ditemukan.");
        }
    }

    private double getTotalPersembahan() {
        List<String> persembahanData = readFromCSV(PERSEMBAHAN_FILE);
        return persembahanData.stream().mapToDouble(Double::parseDouble).sum();
    }

    private void keluar() {
        tampilkanPesan("Keluar dari aplikasi.");
        scanner.close();
        System.exit(0);
    }

    private void simpanDataJemaat(Jemaat jemaat) {
        jemaatRepository.save(jemaat);
    }

    private void hitungPersembahan(double persembahan) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PERSEMBAHAN_FILE, true))) {
            writer.write(Double.toString(persembahan));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data persembahan: " + e.getMessage());
        }
    }

    private void hitungPerbendaharaan(double kasSebelumnya, double pengeluaran) {
        double kasAkhir = kasSebelumnya - pengeluaran;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PERBENDAHARAAN_FILE, true))) {
            writer.write("Kas Akhir: " + kasAkhir);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data perbendaharaan: " + e.getMessage());
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
            System.out.println("Terjadi kesalahan dalam membaca file CSV: " + e.getMessage());
        }
        return data;
    }

    private void ensureFileExists(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
    }
}
