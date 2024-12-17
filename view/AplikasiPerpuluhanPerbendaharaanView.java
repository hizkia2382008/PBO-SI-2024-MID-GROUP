package view;

import entity.Entities.Jemaat;
import java.util.List;
import java.util.Optional;

public interface AplikasiPerpuluhanPerbendaharaanView {

    void tampilkanMenu();

    void tampilkanJemaat(List<Jemaat> jemaatList);

    void tampilkanPerbendaharaan();

    void tampilkanPesan(String message);

    void tampilkanRekapitulasiPersembahan(double totalPersembahan);

    void prosesMenu();

    String getInput();

    double getInputDouble();

    Optional<Jemaat> searchJemaatByName(String nama);
}
