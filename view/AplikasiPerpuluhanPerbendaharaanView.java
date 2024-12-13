package view;

import java.util.List;

public interface AplikasiPerpuluhanPerbendaharaanView {
    void tampilkanMenu();
    void tampilkanJemaat(List<String> jemaatList);
    void tampilkanPerbendaharaan(List<String> perbendaharaanList);
    void tampilkanPesan(String message);
    void tampilkanRekapitulasiPersembahan(double totalPersembahan);

    // Menambahkan metode untuk memproses interaksi menu
    void prosesMenu();  // Proses menu yang diterima dari pengguna
}
