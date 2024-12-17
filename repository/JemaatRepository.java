package repository;

import entity.Entities.Jemaat;
import entity.Entities.Persembahan;
import entity.Entities.Perbendaharaan;

import java.util.List;
import java.util.Optional;

public interface JemaatRepository {

    List<Jemaat> getAllJemaat();
    Optional<Jemaat> findJemaatById(String id);
    Optional<Jemaat> findJemaatByName(String nama);

    Optional<Jemaat> findJemaatById(int id);

    void addJemaat(Jemaat jemaat);
    void updateJemaat(Jemaat jemaat);
    void deleteJemaat(int id);
    void deleteJemaatByName(String nama);

    void addPersembahan(Persembahan persembahan);
    double getTotalPersembahan();

    void addPerbendaharaan(Perbendaharaan perbendaharaan);
    List<Perbendaharaan> getAllPerbendaharaan();
}
