import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

    public static List<String> readFromCSV(String fileName) {
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

    public static void simpanDataJemaat(String nama, String alamat) {
        writeToCSV(JEMAAT_FILE, nama + "," + alamat, true);
    }

    public static void hitungPerpuluhan(String nama, double gaji) {
        double perpuluhan = gaji * 0.1;
        writeToCSV(JEMAAT_FILE, nama + ",Perpuluhan: " + perpuluhan, true);
    }

    public static void hitungPersembahan(double persembahan) {
        double hasilBagi2 = persembahan / 2;
        writeToCSV(PERSEMBAHAN_FILE, String.valueOf(hasilBagi2), true);
    }

    public static void hitungPerbendaharaan(double kasSebelumnya, double pengeluaran) {
        List<String> persembahanData = readFromCSV(PERSEMBAHAN_FILE);
        double totalPersembahan = persembahanData.stream().mapToDouble(Double::parseDouble).sum();
        double totalKas = kasSebelumnya + totalPersembahan - pengeluaran;
        writeToCSV(PERBENDAHARAAN_FILE, String.valueOf(totalKas), true);
    }

    public static void tampilkanDataCSV(String fileName) {
        List<String> data = readFromCSV(fileName);
        data.forEach(System.out::println);
    }

    public static void updateDataJemaat(String namaLama, String namaBaru, String alamatBaru) {
        List<String> jemaatList = readFromCSV(JEMAAT_FILE);
        List<String> updatedList = new ArrayList<>();
        jemaatList.forEach(jemaat -> {
            String[] parts = jemaat.split(",");
            if (parts[0].equals(namaLama)) {
                updatedList.add(namaBaru + "," + alamatBaru);
            } else {
                updatedList.add(jemaat);
            }
        });
        try (FileWriter writer = new FileWriter(JEMAAT_FILE)) {
            for (String updatedData : updatedList) {
                writer.write(updatedData + "\n");
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat mengupdate file jemaat.");
        }
    }

    public static void hapusDataJemaat(String nama) {
        List<String> jemaatList = readFromCSV(JEMAAT_FILE);
        jemaatList.removeIf(jemaat -> jemaat.split(",")[0].equals(nama));
        try (FileWriter writer = new FileWriter(JEMAAT_FILE)) {
            for (String jemaat : jemaatList) {
                writer.write(jemaat + "\n");
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menghapus data jemaat.");
        }
    }

    public static void cariJemaat(String nama) {
        List<String> jemaatList = readFromCSV(JEMAAT_FILE);
        jemaatList.stream()
                .filter(jemaat -> jemaat.split(",")[0].equalsIgnoreCase(nama))
                .forEach(System.out::println);
    }

    public static void rekapitulasiPersembahan() {
        List<String> persembahanData = readFromCSV(PERSEMBAHAN_FILE);
        double totalPersembahan = persembahanData.stream().mapToDouble(Double::parseDouble).sum();
        System.out.println("Total Persembahan: " + totalPersembahan);
    }

    public static void main(String[] args) {
        while (true) {
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
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> {
                    System.out.print("Masukkan nama jemaat: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan alamat jemaat: ");
                    String alamat = scanner.nextLine();
                    simpanDataJemaat(nama, alamat);
                }
                case 2 -> {
                    List<String> jemaatList = readFromCSV(JEMAAT_FILE);
                    if (jemaatList.isEmpty()) {
                        System.out.println("Belum ada data jemaat.");
                    } else {
                        System.out.println("Pilih jemaat:");
                        for (int i = 0; i < jemaatList.size(); i++) {
                            System.out.println((i + 1) + ". " + jemaatList.get(i));
                        }
                        int jemaatIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
                        if (jemaatIndex >= 0 && jemaatIndex < jemaatList.size()) {
                            System.out.print("Masukkan gaji bulan ini: ");
                            double gajiBulan = scanner.nextDouble();
                            hitungPerpuluhan(jemaatList.get(jemaatIndex).split(",")[0], gajiBulan);
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Masukkan jumlah persembahan: ");
                    double persembahan = scanner.nextDouble();
                    hitungPersembahan(persembahan);
                }
                case 4 -> {
                    System.out.print("Masukkan jumlah kas sebelumnya: ");
                    double kasSebelumnya = scanner.nextDouble();
                    System.out.print("Masukkan jumlah pengeluaran: ");
                    double pengeluaran = scanner.nextDouble();
                    hitungPerbendaharaan(kasSebelumnya, pengeluaran);
                }
                case 5 -> tampilkanDataCSV(JEMAAT_FILE);
                case 6 -> tampilkanDataCSV(PERBENDAHARAAN_FILE);
                case 7 -> {
                    System.out.print("Masukkan nama jemaat yang akan diupdate: ");
                    String namaLama = scanner.nextLine();
                    System.out.print("Masukkan nama baru: ");
                    String namaBaru = scanner.nextLine();
                    System.out.print("Masukkan alamat baru: ");
                    String alamatBaru = scanner.nextLine();
                    updateDataJemaat(namaLama, namaBaru, alamatBaru);
                }
                case 8 -> {
                    System.out.print("Masukkan nama jemaat yang akan dihapus: ");
                    String namaHapus = scanner.nextLine();
                    hapusDataJemaat(namaHapus);
                }
                case 9 -> {
                    System.out.print("Masukkan nama jemaat yang dicari: ");
                    String namaCari = scanner.nextLine();
                    cariJemaat(namaCari);
                }
                case 10 -> rekapitulasiPersembahan();
                case 11 -> {
                    System.out.println("Keluar dari aplikasi.");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
