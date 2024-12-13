package view;

import entity.Entities.Jemaat;
import java.util.List;

public interface AplikasiPerpuluhanPerbendaharaanView {
    void tampilkanMenu();

    void tampilkanJemaat(List<Jemaat> jemaatList);

    void tampilkanPerbendaharaan(List<String> perbendaharaanList);

    void tampilkanPesan(String message);

    void tampilkanRekapitulasiPersembahan(double totalPersembahan);

    void prosesMenu();

    String getInput();

    double getInputDouble();
}
