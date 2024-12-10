import repositories.JemaatRepository;
import service.JemaatService;
import views.JemaatView;

public class Main {
    public static void main(String[] args) {
        JemaatRepository jemaatRepository = new JemaatRepository();
        JemaatService jemaatService = new JemaatService(jemaatRepository);
        JemaatView jemaatView = new JemaatView(jemaatService);
        jemaatView.tampilkanMenu();
    }
}
