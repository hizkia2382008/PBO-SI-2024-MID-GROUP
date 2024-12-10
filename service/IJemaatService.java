package services;

import entities.Jemaat;
import java.util.List;

public interface IJemaatService {
    void tambahJemaat(Jemaat jemaat);
    List<Jemaat> tampilkanJemaat();
    void hitungPerpuluhan(String nama, double gaji);
    void updateJemaat(String namaLama, Jemaat jemaatBaru);
    void hapusJemaat(String nama);
}
