import repository.JemaatRepositoryImpl;
import service.JemaatServiceImpl;
import view.AplikasiPerpuluhanPerbendaharaanViewImpl;

public class Main {
    public static void main(String[] args) {
        JemaatRepositoryImpl jemaatRepository = new JemaatRepositoryImpl();
        JemaatServiceImpl jemaatService = new JemaatServiceImpl(jemaatRepository);
        AplikasiPerpuluhanPerbendaharaanViewImpl jemaatView = new AplikasiPerpuluhanPerbendaharaanViewImpl();
        jemaatView.prosesMenu();
    }
}
