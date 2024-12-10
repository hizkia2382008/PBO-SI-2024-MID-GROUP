package repositories;

import entities.Jemaat;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JemaatRepository implements IJemaatRepository {
    private static final String JEMAAT_FILE = "jemaat.csv";

    // Menyimpan data jemaat ke dalam file CSV
    @Override
    public void simpanData(List<Jemaat> jemaatList) {
        try (FileWriter writer = new FileWriter(JEMAAT_FILE, false)) {
            // Menulis setiap jemaat ke dalam file CSV
            for (Jemaat jemaat : jemaatList) {
                writer.write(jemaat.getNama() + "," + jemaat.getAlamat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data jemaat.");
            e.printStackTrace();
        }
    }

    // Membaca data jemaat dari file CSV
    @Override
    public List<Jemaat> bacaData() {
        List<Jemaat> jemaatList = new ArrayList<>();
        File file = new File(JEMAAT_FILE);

        // Mengecek apakah file kosong
        if (file.exists() && file.length() > 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(JEMAAT_FILE))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 2) { // Pastikan data valid (nama dan alamat)
                        jemaatList.add(new Jemaat(data[0], data[1]));
                    }
                }
            } catch (IOException e) {
                System.out.println("Terjadi kesalahan saat membaca data jemaat.");
                e.printStackTrace();
            }
        }
        return jemaatList;
    }

    // Menambahkan data jemaat baru
    @Override
    public void tambahData(Jemaat jemaat) {
        List<Jemaat> jemaatList = bacaData();
        jemaatList.add(jemaat);
        simpanData(jemaatList);
    }

    // Memperbarui data jemaat berdasarkan nama lama
    @Override
    public void updateData(String namaLama, Jemaat jemaatBaru) {
        List<Jemaat> jemaatList = bacaData();
        boolean ditemukan = false;

        // Mencari jemaat berdasarkan nama lama dan mengganti dengan jemaat baru
        for (int i = 0; i < jemaatList.size(); i++) {
            if (jemaatList.get(i).getNama().equals(namaLama)) {
                jemaatList.set(i, jemaatBaru);
                ditemukan = true;
                break;
            }
        }

        // Jika ditemukan, simpan data kembali
        if (ditemukan) {
            simpanData(jemaatList);
        } else {
            System.out.println("Jemaat dengan nama " + namaLama + " tidak ditemukan.");
        }
    }

    // Menghapus data jemaat berdasarkan nama
    @Override
    public void hapusData(String nama) {
        List<Jemaat> jemaatList = bacaData();
        boolean ditemukan = jemaatList.removeIf(jemaat -> jemaat.getNama().equals(nama));

        if (ditemukan) {
            simpanData(jemaatList);
        } else {
            System.out.println("Jemaat dengan nama " + nama + " tidak ditemukan.");
        }
    }
}
