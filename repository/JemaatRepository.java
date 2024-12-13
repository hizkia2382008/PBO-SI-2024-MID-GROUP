package repository;

import entity.Entities.Jemaat;
import java.util.List;

public interface JemaatRepository {

    void save(Jemaat jemaat);

    void update(Jemaat jemaat);

    void deleteById(int id);

    Jemaat findById(int id);

    List<Jemaat> findAll();

    Jemaat findByName(String namaCari);

    void deleteByName(String namaHapus);
}
