package views;

public interface IView {
    void showMenu();
    int getMenuSelection();
    String getInput(String prompt);
    double getDoubleInput(String prompt);
    void displayMessage(String message);
    void displayData(String data);
    void close();
}
