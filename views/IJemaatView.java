package views;

import entities.Jemaat;
import java.util.List;

public interface IJemaatView {
    void tampilkanMenu();
    void tampilkanDataJemaat(List<Jemaat> jemaatList);
}
