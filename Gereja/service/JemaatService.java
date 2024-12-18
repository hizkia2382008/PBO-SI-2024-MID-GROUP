package Gereja.service;

import Gereja.entity.Entities;
import java.util.List;
import java.util.Optional;

public interface JemaatService {

    void addJemaat(String nama, String alamat);
    void addJemaatWithGaji(String nama, String alamat, double gaji);

    void calculateTithe(String nama, double gaji);
    void calculateTitheById(int idJemaat, double gaji);

    void recordPersembahan(double persembahan);

    void calculateTreasuryBalance(double kasSebelumnya, double pengeluaran);

    List<Entities.Jemaat> getJemaatList();
    List<Entities.Perbendaharaan> getPerbendaharaanList();

    void updateJemaat(String namaLama, String namaBaru, String alamatBaru);
    void updateJemaatById(int idJemaat, String namaBaru, String alamatBaru);
    void deleteJemaatByName(String nama);
    void deleteJemaatById(int idJemaat);

    Optional<Entities.Jemaat> searchJemaat(String nama);
    double getTotalPersembahan();
    void recalculatePersembahan();
}
