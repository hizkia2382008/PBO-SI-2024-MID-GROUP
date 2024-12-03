package service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JemaatService implements IJemaatService {
    private static final String JEMAAT_FILE = "jemaat.csv";

    @Override
    public void simpanDataJemaat(String nama, String alamat) {
        try (FileWriter writer = new FileWriter(JEMAAT_FILE, true)) {
            writer.write(nama + "," + alamat + "\n");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data jemaat.");
        }
    }

    @Override
    public void tampilkanDataJemaat() {
        List<String> data = bacaDataDariFile(JEMAAT_FILE);
        if (data.isEmpty()) {
            System.out.println("Tidak ada data jemaat.");
        } else {
            data.forEach(System.out::println);
        }
    }

    @Override
    public void hapusDataJemaat(String nama) {
        List<String> jemaatList = bacaDataDariFile(JEMAAT_FILE);
        jemaatList.removeIf(jemaat -> jemaat.split(",")[0].equals(nama));
        tulisDataKeFile(JEMAAT_FILE, jemaatList, false);
    }

    @Override
    public void cariJemaat(String nama) {
        List<String> jemaatList = bacaDataDariFile(JEMAAT_FILE);
        jemaatList.stream()
                .filter(jemaat -> jemaat.split(",")[0].equalsIgnoreCase(nama))
                .forEach(System.out::println);
    }

    private List<String> bacaDataDariFile(String fileName) {
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

    private void tulisDataKeFile(String fileName, List<String> data, boolean append) {
        try (FileWriter writer = new FileWriter(fileName, append)) {
            for (String line : data) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menulis ke file: " + fileName);
        }
    }
}
