package views;

import java.util.Scanner;

public class ConsoleView implements IView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void showMenu() {
        System.out.println("\n=== Aplikasi Perpuluhan dan Perbendaharaan Jemaat ===");
        System.out.println("1. Tambah Jemaat");
        System.out.println("2. Hitung Perpuluhan");
        System.out.println("3. Input Persembahan");
        System.out.println("4. Hitung Perbendaharaan");
        System.out.println("5. Tampilkan Data Jemaat");
        System.out.println("6. Tampilkan Perbendaharaan");
        System.out.println("7. Update Data Jemaat");
        System.out.println("8. Hapus Data Jemaat");
        System.out.println("9. Cari Jemaat");
        System.out.println("10. Rekapitulasi Persembahan");
        System.out.println("11. Keluar");
        System.out.print("Pilih menu: ");
    }

    @Override
    public int getMenuSelection() {
        return scanner.nextInt();
    }

    @Override
    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    @Override
    public double getDoubleInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextDouble();
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void displayData(String data) {
        System.out.println(data);
    }

    @Override
    public void close() {
        scanner.close();
    }
}
