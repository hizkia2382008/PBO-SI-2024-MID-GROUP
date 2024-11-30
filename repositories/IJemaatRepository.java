package repositories;

import java.util.List;

public interface IJemaatRepository {
    void saveJemaat(String nama, String alamat);
    void updateJemaat(String namaLama, String namaBaru, String alamatBaru);
    void deleteJemaat(String nama);
    List<String> getAllJemaat();
    void cariJemaat(String nama);
}
