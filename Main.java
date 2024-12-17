import repository.JemaatRepositoryImpl;
import service.JemaatServiceImpl;
import view.AplikasiPerpuluhanPerbendaharaanViewImpl;
import config.Database;
import repository.JemaatRepository;
import service.JemaatService;
import view.AplikasiPerpuluhanPerbendaharaanView;

public class Main {
    public static void main(String[] args) {

        Database database = new Database("jemaat_db", "root", "", "localhost", "3306");

        JemaatRepository jemaatRepository = new JemaatRepositoryImpl(database);

        JemaatService jemaatService = new JemaatServiceImpl(jemaatRepository);

        AplikasiPerpuluhanPerbendaharaanView jemaatView = new AplikasiPerpuluhanPerbendaharaanViewImpl(jemaatService);

        jemaatView.prosesMenu();
    }
}
