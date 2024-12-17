package view;

import entity.Entities;
import entity.Entities.Jemaat;
import service.JemaatService;
import service.JemaatServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AplikasiPerpuluhanPerbendaharaanViewImpl implements AplikasiPerpuluhanPerbendaharaanView {

    private final Scanner scanner = new Scanner(System.in);
    private final JemaatService jemaatService;

    public AplikasiPerpuluhanPerbendaharaanViewImpl(JemaatService jemaatService) {
        this.jemaatService = jemaatService;
    }

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
    }

    @Override
    public void prosesMenu() {
        while (true) {
            tampilkanMenu();
            int pilihan = getInputInt("Pilih menu: ");
            switch (pilihan) {
                case 1 -> tambahJemaat();
                case 2 -> hitungPerpuluhan();
                case 3 -> inputPersembahan();
                case 4 -> hitungPerbendaharaan();
                case 5 -> tampilkanJemaat(jemaatService.getJemaatList());
                case 6 -> tampilkanPerbendaharaan();
                case 7 -> updateDataJemaat();
                case 8 -> hapusDataJemaat();
                case 9 -> cariJemaat();
                case 10 -> tampilkanRekapitulasiPersembahan(jemaatService.getTotalPersembahan());
                case 0 -> keluar();
                default -> tampilkanPesan("Pilihan tidak valid.");
            }
        }
    }

    @Override
    public String getInput() {
        return "";
    }

    @Override
    public double getInputDouble() {
        return 0;
    }

    @Override
    public Optional<Jemaat> searchJemaatByName(String nama) {
        return Optional.empty();
    }

    @Override
    public void tampilkanJemaat(List<Jemaat> jemaatList) {
        if (jemaatList.isEmpty()) {
            tampilkanPesan("Belum ada data jemaat.");
        } else {
            System.out.println("Daftar Jemaat:");
            for (int i = 0; i < jemaatList.size(); i++) {
                Jemaat jemaat = jemaatList.get(i);
                System.out.printf("%d. %s, Alamat: %s, Perpuluhan: %.2f%n",
                        i + 1, jemaat.getNama(), jemaat.getAlamat(), jemaat.getPerpuluhan());
            }
        }
    }

    @Override
    public void tampilkanPerbendaharaan() {
        List<Entities.Perbendaharaan> perbendaharaanList = jemaatService.getPerbendaharaanList();
        if (perbendaharaanList.isEmpty()) {
            tampilkanPesan("Belum ada data perbendaharaan.");
        } else {
            System.out.println("Daftar Perbendaharaan:");
            perbendaharaanList.forEach(System.out::println);
        }
    }

    @Override
    public void tampilkanPesan(String message) {
        System.out.println(message);
    }

    @Override
    public void tampilkanRekapitulasiPersembahan(double totalPersembahan) {
        System.out.printf("Total Persembahan: %.2f%n", totalPersembahan);
    }

    private void tambahJemaat() {
        String nama = getInputString("Masukkan nama jemaat: ");
        String alamat = getInputString("Masukkan alamat jemaat: ");
        jemaatService.addJemaat(nama, alamat);
        tampilkanPesan("Data jemaat berhasil disimpan.");
    }

    private void hitungPerpuluhan() {
        List<Jemaat> jemaatList = jemaatService.getJemaatList();
        tampilkanJemaat(jemaatList);
        if (!jemaatList.isEmpty()) {
            int index = getInputInt("Pilih jemaat (nomor): ") - 1;
            if (index >= 0 && index < jemaatList.size()) {
                double gaji = getInputDouble("Masukkan gaji bulan ini: ");
                jemaatService.calculateTithe(jemaatList.get(index).getNama(), gaji);
                tampilkanPesan("Perpuluhan berhasil dihitung dan disimpan.");
            } else {
                tampilkanPesan("Pilihan tidak valid.");
            }
        }
    }

    private void inputPersembahan() {
        double persembahan = getInputDouble("Masukkan jumlah persembahan: ");
        jemaatService.recordPersembahan(persembahan);
        tampilkanPesan("Persembahan berhasil dicatat.");
    }

    private void hitungPerbendaharaan() {
        double kasSebelumnya = getInputDouble("Masukkan jumlah kas sebelumnya: ");
        double pengeluaran = getInputDouble("Masukkan jumlah pengeluaran: ");
        jemaatService.calculateTreasuryBalance(kasSebelumnya, pengeluaran);
        tampilkanPesan("Perbendaharaan berhasil dihitung dan disimpan.");
    }

    private void updateDataJemaat() {
        String namaLama = getInputString("Masukkan nama jemaat yang ingin diupdate: ");
        Optional<Jemaat> jemaatOptional = jemaatService.searchJemaat(namaLama);
        if (jemaatOptional.isPresent()) {
            String namaBaru = getInputString("Masukkan nama baru: ");
            String alamatBaru = getInputString("Masukkan alamat baru: ");
            jemaatService.updateJemaat(namaLama, namaBaru, alamatBaru);
            tampilkanPesan("Data jemaat berhasil diperbarui.");
        } else {
            tampilkanPesan("Jemaat dengan nama tersebut tidak ditemukan.");
        }
    }

    private void hapusDataJemaat() {
        String nama = getInputString("Masukkan nama jemaat yang ingin dihapus: ");
        jemaatService.deleteJemaatByName(nama);
        tampilkanPesan("Data jemaat berhasil dihapus.");
    }

    private void cariJemaat() {
        String nama = getInputString("Masukkan nama jemaat yang ingin dicari: ");
        Optional<Jemaat> jemaatOptional = jemaatService.searchJemaat(nama);
        if (jemaatOptional.isPresent()) {
            Jemaat jemaat = jemaatOptional.get();
            System.out.printf("Nama: %s, Alamat: %s, Perpuluhan: %.2f%n",
                    jemaat.getNama(), jemaat.getAlamat(), jemaat.getPerpuluhan());
        } else {
            tampilkanPesan("Jemaat tidak ditemukan.");
        }
    }

    private void keluar() {
        tampilkanPesan("Keluar dari aplikasi. Terima kasih!");
        scanner.close();
        System.exit(0);
    }

    private String getInputString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int getInputInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            tampilkanPesan("Input tidak valid. Masukkan angka.");
            scanner.next();
        }
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    private double getInputDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            tampilkanPesan("Input tidak valid. Masukkan angka desimal.");
            scanner.next();
        }
        double result = scanner.nextDouble();
        scanner.nextLine();
        return result;
    }
}
