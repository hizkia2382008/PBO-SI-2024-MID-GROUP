package repository;

import entity.Entities.Jemaat;
import repository.JemaatRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JemaatRepositoryImpl implements JemaatRepository {

    private static final String FILE_PATH = "jemaat.csv";

    private void ensureFileExists() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }
    }

    @Override
    public void save(Jemaat jemaat) {
        ensureFileExists();
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(jemaat.getId() + "," + jemaat.getNama() + "," + jemaat.getAlamat() + "," + jemaat.getPerpuluhan() + "\n");
        } catch (IOException e) {
            System.err.println("Error saving jemaat: " + e.getMessage());
        }
    }

    @Override
    public void update(Jemaat jemaat) {
        List<Jemaat> jemaats = findAll();
        boolean updated = false;
        List<Jemaat> updatedJemaats = new ArrayList<>();

        for (Jemaat j : jemaats) {
            if (j.getId() == jemaat.getId()) {
                updatedJemaats.add(jemaat);
                updated = true;
            } else {
                updatedJemaats.add(j);
            }
        }

        if (updated) {
            try (FileWriter writer = new FileWriter(FILE_PATH)) {
                for (Jemaat j : updatedJemaats) {
                    writer.write(j.getId() + "," + j.getNama() + "," + j.getAlamat() + "," + j.getPerpuluhan() + "\n");
                }
            } catch (IOException e) {
                System.err.println("Error updating jemaat with ID " + jemaat.getId() + ": " + e.getMessage());
            }
        } else {
            System.err.println("Jemaat with ID " + jemaat.getId() + " not found.");
        }
    }

    @Override
    public void deleteById(int id) {
        List<Jemaat> jemaats = findAll();
        List<Jemaat> updatedJemaats = new ArrayList<>();
        boolean found = false;

        for (Jemaat j : jemaats) {
            if (j.getId() != id) {
                updatedJemaats.add(j);
            } else {
                found = true;
            }
        }

        if (found) {
            try (FileWriter writer = new FileWriter(FILE_PATH)) {
                for (Jemaat j : updatedJemaats) {
                    writer.write(j.getId() + "," + j.getNama() + "," + j.getAlamat() + "," + j.getPerpuluhan() + "\n");
                }
            } catch (IOException e) {
                System.err.println("Error deleting jemaat with ID " + id + ": " + e.getMessage());
            }
        } else {
            System.err.println("Jemaat with ID " + id + " not found.");
        }
    }

    @Override
    public Jemaat findById(int id) {
        List<Jemaat> jemaats = findAll();
        for (Jemaat jemaat : jemaats) {
            if (jemaat.getId() == id) {
                return jemaat;
            }
        }
        return null;
    }

    @Override
    public List<Jemaat> findAll() {
        List<Jemaat> jemaats = new ArrayList<>();
        ensureFileExists();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        String nama = parts[1];
                        String alamat = parts[2];
                        double perpuluhan = Double.parseDouble(parts[3]);
                        jemaats.add(new Jemaat(id, nama, alamat, perpuluhan));
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping invalid line: " + line);
                    }
                } else {
                    System.err.println("Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading jemaat data from file: " + e.getMessage());
        }
        return jemaats;
    }

    @Override
    public Jemaat findByName(String namaCari) {
        List<Jemaat> jemaats = findAll();
        for (Jemaat jemaat : jemaats) {
            if (jemaat.getNama().equalsIgnoreCase(namaCari)) {
                return jemaat;
            }
        }
        return null;
    }

    @Override
    public void deleteByName(String namaHapus) {
        List<Jemaat> jemaats = findAll();
        List<Jemaat> updatedJemaats = new ArrayList<>();
        boolean found = false;

        for (Jemaat j : jemaats) {
            if (!j.getNama().equalsIgnoreCase(namaHapus)) {
                updatedJemaats.add(j);
            } else {
                found = true;
            }
        }

        if (found) {
            try (FileWriter writer = new FileWriter(FILE_PATH)) {
                for (Jemaat j : updatedJemaats) {
                    writer.write(j.getId() + "," + j.getNama() + "," + j.getAlamat() + "," + j.getPerpuluhan() + "\n");
                }
            } catch (IOException e) {
                System.err.println("Error deleting jemaat with name " + namaHapus + ": " + e.getMessage());
            }
        } else {
            System.err.println("Jemaat with name " + namaHapus + " not found.");
        }
    }
}
