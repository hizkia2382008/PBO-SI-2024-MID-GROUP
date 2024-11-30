package repositories;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileRepository implements IFileRepository {

    @Override
    public void writeToCSV(String fileName, String data, boolean append) {
        try (FileWriter writer = new FileWriter(fileName, append)) {
            writer.write(data + "\n");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menulis ke file: " + fileName);
        }
    }

    @Override
    public List<String> readFromCSV(String fileName) {
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + fileName);
        }
        return data;
    }
}
