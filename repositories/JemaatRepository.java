package repositories;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JemaatRepository implements IJemaatRepository {
    private IFileRepository fileRepository;

    public JemaatRepository(IFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void saveJemaat(String nama, String alamat) {
        fileRepository.writeToCSV("jemaat.csv", nama + "," + alamat, true);
    }

    @Override
    public void updateJemaat(String namaLama, String namaBaru, String alamatBaru) {
        List<String> jemaatList = fileRepository.readFromCSV("jemaat.csv");
        List<String> updatedList = new ArrayList<>();
        jemaatList.forEach(jemaat -> {
            String[] parts = jemaat.split(",");
            if (parts[0].equals(namaLama)) {
                updatedList.add(namaBaru + "," + alamatBaru);
            } else {
                updatedList.add(jemaat);
            }
        });
        writeListToFile(updatedList);
    }

    @Override
    public void deleteJemaat(String nama) {
        List<String> jemaatList = fileRepository.readFromCSV("jemaat.csv");
        jemaatList.removeIf(jemaat -> jemaat.split(",")[0].equals(nama));
        writeListToFile(jemaatList);
    }

    @Override
    public List<String> getAllJemaat() {
        return fileRepository.readFromCSV("jemaat.csv");
    }

    @Override
    public void cariJemaat(String nama) {
        List<String> jemaatList = fileRepository.readFromCSV("jemaat.csv");
        jemaatList.stream()
                .filter(jemaat -> jemaat.split(",")[0].equalsIgnoreCase(nama))
                .forEach(System.out::println);
    }

    // Method untuk menulis ulang daftar jemaat ke file
    private void writeListToFile(List<String> jemaatList) {
        try (FileWriter writer = new FileWriter("jemaat.csv")) {
            for (String jemaat : jemaatList) {
                writer.write(jemaat + "\n");
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menulis ulang data jemaat ke file.");
        }
    }
}
