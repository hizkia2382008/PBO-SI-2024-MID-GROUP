package views;

import entities.Jemaat;
import services.IJemaatService;
import java.util.List;
import java.util.Scanner;

public class JemaatView implements IJemaatView {
    private final IJemaatService jemaatService;
    private static final Scanner scanner = new Scanner(System.in);

    public JemaatView(IJemaatService jemaatService) {
        this.jemaatService = jemaatService;
    }

    @Override
    public void tampilkanMenu() {
        while (true) {
            System.out.println("\n=== Aplikasi Perpuluhan dan Perbendaharaan Jemaat ===");
            System.out.println("1. Tambah Jemaat");
            System.out.println("2. Tampilkan Jemaat");
            System.out.println("3. Hitung Perpuluhan");
            System.out.println("4. Update Jemaat");
            System.out.println("5. Hapus Jemaat");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (pilihan) {
                case 1:
                    tambahJemaat();
                    break;
                case 2:
                    tampilkanDataJemaat(jemaatService.tampilkanJemaat());
                    break;
                case 3:
                    hitungPerpuluhan();
                    break;
                case 4:
                    updateJemaat();
                    break;
                case 5:
                    hapusJemaat();
                    break;
                case 6:
                    System.out.println("Keluar dari aplikasi.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }

    @Override
    public void tampilkanDataJemaat(List<Jemaat> jemaatList) {
        if (jemaatList.isEmpty()) {
            System.out.println("Belum ada data jemaat.");
        } else {
            for (Jemaat jemaat : jemaatList) {
                System.out.println(jemaat.getNama() + " - " + jemaat.getAlamat());
            }
        }
    }

    private void tambahJemaat() {
        System.out.print("Masukkan nama jemaat: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan alamat jemaat: ");
        String alamat = scanner.nextLine();
        Jemaat jemaat = new Jemaat(nama, alamat);
        jemaatService.tambahJemaat(jemaat);
    }

    private void hitungPerpuluhan() {
        System.out.print("Masukkan nama jemaat: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan gaji bulan ini: ");
        double gaji = scanner.nextDouble();
        jemaatService.hitungPerpuluhan(nama, gaji);
    }

    private void updateJemaat() {
        System.out.print("Masukkan nama jemaat yang akan diupdate: ");
        String namaLama = scanner.nextLine();
        System.out.print("Masukkan nama baru: ");
        String namaBaru = scanner.nextLine();
        System.out.print("Masukkan alamat baru: ");
        String alamatBaru = scanner.nextLine();
        Jemaat jemaatBaru = new Jemaat(namaBaru, alamatBaru);
        jemaatService.updateJemaat(namaLama, jemaatBaru);
    }

    private void hapusJemaat() {
        System.out.print("Masukkan nama jemaat yang akan dihapus: ");
        String nama = scanner.nextLine();
        jemaatService.hapusJemaat(nama);
    }
}
