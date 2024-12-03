package service;

public interface IJemaatService {
    void simpanDataJemaat(String nama, String alamat);

    void tampilkanDataJemaat();

    void hapusDataJemaat(String nama);

    void cariJemaat(String nama);
}
