package Gereja.service;

import Gereja.entity.Entities;
import Gereja.repository.JemaatRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JemaatServiceImpl implements JemaatService {

    private final JemaatRepository jemaatRepository;

    public JemaatServiceImpl(JemaatRepository jemaatRepository) {
        this.jemaatRepository = jemaatRepository;
    }

    @Override
    public void addJemaat(String nama, String alamat) {
        try {
            Entities.Jemaat jemaat = new Entities.Jemaat(nama, alamat);
            jemaat.setPerpuluhan(0);
            jemaatRepository.addJemaat(jemaat);
        } catch (Exception e) {
            System.err.println("Error while adding Jemaat: " + e.getMessage());
        }
    }

    @Override
    public void addJemaatWithGaji(String nama, String alamat, double gaji) {
        try {
            Entities.Jemaat jemaat = new Entities.Jemaat(nama, alamat);
            jemaat.setPerpuluhan(gaji * 0.1);
            jemaatRepository.addJemaat(jemaat);
        } catch (Exception e) {
            System.err.println("Error while adding Jemaat with salary: " + e.getMessage());
        }
    }

    @Override
    public void calculateTithe(String nama, double gaji) {
        try {
            Optional<Entities.Jemaat> jemaatOptional = jemaatRepository.findJemaatByName(nama);
            jemaatOptional.ifPresent(jemaat -> {
                jemaat.setPerpuluhan(gaji * 0.1);
                jemaatRepository.updateJemaat(jemaat);
            });
        } catch (Exception e) {
            System.err.println("Error while calculating tithe for name " + nama + ": " + e.getMessage());
        }
    }

    @Override
    public void calculateTitheById(int idJemaat, double gaji) {
        try {
            Optional<Entities.Jemaat> jemaatOptional = jemaatRepository.findJemaatById(String.valueOf(idJemaat));
            jemaatOptional.ifPresent(jemaat -> {
                jemaat.setPerpuluhan(gaji * 0.1);
                jemaatRepository.updateJemaat(jemaat);
            });
        } catch (Exception e) {
            System.err.println("Error while calculating tithe for ID " + idJemaat + ": " + e.getMessage());
        }
    }

    @Override
    public void recordPersembahan(double persembahan) {
        try {
            Entities.Persembahan persembahanEntity = new Entities.Persembahan(persembahan);
            jemaatRepository.addPersembahan(persembahanEntity);
        } catch (Exception e) {
            System.err.println("Error while recording Persembahan: " + e.getMessage());
        }
    }

    @Override
    public void calculateTreasuryBalance(double kasSebelumnya, double pengeluaran) {
        try {
            double totalPersembahan = jemaatRepository.getTotalPersembahan();
            double totalKas = kasSebelumnya + totalPersembahan - pengeluaran;

            Entities.Perbendaharaan perbendaharaan = new Entities.Perbendaharaan(kasSebelumnya, totalKas, pengeluaran);
            jemaatRepository.addPerbendaharaan(perbendaharaan);
        } catch (Exception e) {
            System.err.println("Error while calculating treasury balance: " + e.getMessage());
        }
    }

    @Override
    public List<Entities.Jemaat> getJemaatList() {
        try {
            return jemaatRepository.getAllJemaat();
        } catch (Exception e) {
            System.err.println("Error while retrieving Jemaat list: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Entities.Perbendaharaan> getPerbendaharaanList() {
        try {
            return jemaatRepository.getAllPerbendaharaan();
        } catch (Exception e) {
            System.err.println("Error while retrieving Perbendaharaan list: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void updateJemaat(String namaLama, String namaBaru, String alamatBaru) {
        try {
            Optional<Entities.Jemaat> jemaatOptional = jemaatRepository.findJemaatByName(namaLama);
            jemaatOptional.ifPresent(jemaat -> {
                jemaat.setNama(namaBaru);
                jemaat.setAlamat(alamatBaru);
                jemaatRepository.updateJemaat(jemaat);
            });
        } catch (Exception e) {
            System.err.println("Error while updating Jemaat by name: " + e.getMessage());
        }
    }

    @Override
    public void updateJemaatById(int idJemaat, String namaBaru, String alamatBaru) {
        try {
            Optional<Entities.Jemaat> jemaatOptional = jemaatRepository.findJemaatById(String.valueOf(idJemaat));
            jemaatOptional.ifPresent(jemaat -> {
                jemaat.setNama(namaBaru);
                jemaat.setAlamat(alamatBaru);
                jemaatRepository.updateJemaat(jemaat);
            });
        } catch (Exception e) {
            System.err.println("Error while updating Jemaat by ID: " + e.getMessage());
        }
    }

    @Override
    public void deleteJemaatByName(String nama) {
        try {
            jemaatRepository.deleteJemaatByName(nama);
        } catch (Exception e) {
            System.err.println("Error while deleting Jemaat by name: " + e.getMessage());
        }
    }

    @Override
    public void deleteJemaatById(int idJemaat) {
        try {
            jemaatRepository.deleteJemaat(idJemaat);
        } catch (Exception e) {
            System.err.println("Error while deleting Jemaat by ID: " + e.getMessage());
        }
    }

    @Override
    public Optional<Entities.Jemaat> searchJemaat(String nama) {
        try {
            return jemaatRepository.findJemaatByName(nama);
        } catch (Exception e) {
            System.err.println("Error while searching Jemaat: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public double getTotalPersembahan() {
        try {
            return jemaatRepository.getTotalPersembahan();
        } catch (Exception e) {
            System.err.println("Error while getting total Persembahan: " + e.getMessage());
            return 0.0;
        }
    }

    @Override
    public void recalculatePersembahan() {
        try {
            double totalPersembahan = getTotalPersembahan();
            System.out.println("Total Persembahan: " + totalPersembahan);
        } catch (Exception e) {
            System.err.println("Error while recalculating Persembahan: " + e.getMessage());
        }
    }
}
