import repositories.FileRepository;
import repositories.JemaatRepository;
import service.impl.JemaatService;
import service.impl.PersembahanService;
import service.impl.PerbendaharaanService;
import views.impl.AplikasiPerpuluhanView;

public class Main {
    public static void main(String[] args) {
        FileRepository fileRepository = new FileRepository();
        JemaatRepository jemaatRepository = new JemaatRepository(fileRepository);

        JemaatService jemaatService = new JemaatService(jemaatRepository);
        PersembahanService persembahanService = new PersembahanService(fileRepository);
        PerbendaharaanService perbendaharaanService = new PerbendaharaanService(fileRepository);

        AplikasiPerpuluhanView aplikasiPerpuluhanView = new AplikasiPerpuluhanView(jemaatService, persembahanService, perbendaharaanService);

        aplikasiPerpuluhanView.handleMenuSelection();
    }
}
