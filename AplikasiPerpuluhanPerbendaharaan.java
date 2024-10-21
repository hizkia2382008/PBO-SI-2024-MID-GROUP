import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AplikasiPerpuluhanPerbendaharaan {

    public static final String JEMAAT_FILE = "jemaat.csv";
    public static final String PERSEMBAHAN_FILE = "Persembahan.csv";
    public static final String PERBENDAHARAAN_FILE = "Perbendaharaan.csv";
    public static Scanner scanner = new Scanner(System.in);


    public static void writeToCSV(String fileName, String data, boolean append) {
        try (FileWriter writer = new FileWriter(fileName, append)) {
            writer.write(data + "\n");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menulis ke file: " + fileName);
            }
    }

    public static void simpanDataJemaat(String nama, String alamat) {
        writeToCSV(JEMAAT_FILE, nama + "," + alamat,true);
    }

}

