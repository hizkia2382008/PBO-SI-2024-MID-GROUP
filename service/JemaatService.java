import java.util.List;

public interface JemaatService {

    void tambahJemaat(String nama, String alamat);

    void hitungPerpuluhan(String nama, double gaji);

    void catatPersembahan(double persembahan);

    void hitungSaldoPerbendaharaan(double kasSebelumnya, double pengeluaran);

    List<String> tampilkanJemaat();

    List<String> tampilkanPerbendaharaan();

    void updateJemaat(String namaLama, String namaBaru, String alamatBaru);

    void hapusJemaat(String nama);

    List<String> cariJemaat(String nama);

    void rekapitulasiPersembahan();
}
