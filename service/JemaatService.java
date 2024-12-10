package service;

import entities.Jemaat;
import repositories.IJemaatRepository;
import java.util.List;

public class JemaatService implements services.IJemaatService {
    private final IJemaatRepository jemaatRepository;

    public JemaatService(IJemaatRepository jemaatRepository) {
        this.jemaatRepository = jemaatRepository;
    }

    @Override
    public void tambahJemaat(Jemaat jemaat) {
        jemaatRepository.tambahData(jemaat);
    }

    @Override
    public List<Jemaat> tampilkanJemaat() {
        return jemaatRepository.bacaData();
    }

    @Override
    public void hitungPerpuluhan(String nama, double gaji) {
        double perpuluhan = gaji * 0.1;
        System.out.println("Perpuluhan untuk " + nama + ": " + perpuluhan);
    }

    @Override
    public void updateJemaat(String namaLama, Jemaat jemaatBaru) {
        jemaatRepository.updateData(namaLama, jemaatBaru);
    }

    @Override
    public void hapusJemaat(String nama) {
        jemaatRepository.hapusData(nama);
    }
}
