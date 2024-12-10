package repositories;

import entities.Jemaat;
import java.util.List;

public interface IJemaatRepository {
    void simpanData(List<Jemaat> jemaatList);
    List<Jemaat> bacaData();
    void tambahData(Jemaat jemaat);
    void updateData(String namaLama, Jemaat jemaatBaru);
    void hapusData(String nama);
}
