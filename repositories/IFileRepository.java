package repositories;

import java.util.List;

public interface IFileRepository {
    void writeToCSV(String fileName, String data, boolean append);
    List<String> readFromCSV(String fileName);
}
