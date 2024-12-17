package repository;

import config.Database;
import entity.Entities.Jemaat;
import entity.Entities.Persembahan;
import entity.Entities.Perbendaharaan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JemaatRepositoryImpl implements JemaatRepository {

    private final Database database;

    public JemaatRepositoryImpl(Database database) {
        this.database = database;
    }

    @Override
    public List<Jemaat> getAllJemaat() {
        List<Jemaat> jemaatList = new ArrayList<>();
        String query = "SELECT * FROM jemaat";

        try (Connection connection = database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                jemaatList.add(new Jemaat(
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getString("alamat"),
                        rs.getDouble("perpuluhan")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all jemaat: " + e.getMessage());
        }
        return jemaatList;
    }

    @Override
    public Optional<Jemaat> findJemaatById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<Jemaat> findJemaatById(int id) {
        String query = "SELECT * FROM jemaat WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Jemaat(
                            rs.getInt("id"),
                            rs.getString("nama"),
                            rs.getString("alamat"),
                            rs.getDouble("perpuluhan")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching jemaat by ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Jemaat> findJemaatByName(String nama) {
        String query = "SELECT * FROM jemaat WHERE nama = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, nama);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Jemaat(
                            rs.getInt("id"),
                            rs.getString("nama"),
                            rs.getString("alamat"),
                            rs.getDouble("perpuluhan")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching jemaat by name: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void addJemaat(Jemaat jemaat) {
        String query = "INSERT INTO jemaat (nama, alamat, perpuluhan) VALUES (?, ?, ?)";
        try (Connection connection = database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, jemaat.getNama());
            ps.setString(2, jemaat.getAlamat());
            ps.setDouble(3, jemaat.getPerpuluhan());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error adding jemaat: " + e.getMessage());
        }
    }

    @Override
    public void updateJemaat(Jemaat jemaat) {
        String query = "UPDATE jemaat SET nama = ?, alamat = ?, perpuluhan = ? WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, jemaat.getNama());
            ps.setString(2, jemaat.getAlamat());
            ps.setDouble(3, jemaat.getPerpuluhan());
            ps.setInt(4, jemaat.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error updating jemaat: " + e.getMessage());
        }
    }

    @Override
    public void deleteJemaat(int id) {
        String query = "DELETE FROM jemaat WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error deleting jemaat: " + e.getMessage());
        }
    }

    @Override
    public void deleteJemaatByName(String nama) {
        String query = "DELETE FROM jemaat WHERE nama = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, nama);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error deleting jemaat by name: " + e.getMessage());
        }
    }

    public void calculateAndUpdatePerpuluhan(int id, double percentage) {
        String query = "SELECT perpuluhan FROM jemaat WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    double currentPerpuluhan = rs.getDouble("perpuluhan");
                    double newPerpuluhan = currentPerpuluhan + (currentPerpuluhan * percentage);
                    updatePerpuluhan(id, newPerpuluhan);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error calculating perpuluhan: " + e.getMessage());
        }
    }

    private void updatePerpuluhan(int id, double newPerpuluhan) {
        String query = "UPDATE jemaat SET perpuluhan = ? WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setDouble(1, newPerpuluhan);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating perpuluhan: " + e.getMessage());
        }
    }

    @Override
    public void addPersembahan(Persembahan persembahan) {
        String query = "INSERT INTO persembahan (jumlah) VALUES (?)";
        try (Connection connection = database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setDouble(1, persembahan.getJumlah());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error adding persembahan: " + e.getMessage());
        }
    }

    @Override
    public double getTotalPersembahan() {
        String query = "SELECT SUM(jumlah) AS total FROM persembahan";
        try (Connection connection = database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching total persembahan: " + e.getMessage());
        }
        return 0.0;
    }

    @Override
    public void addPerbendaharaan(Perbendaharaan perbendaharaan) {
        String query = "INSERT INTO perbendaharaan (kas_sebelumnya, total_kas, pengeluaran) VALUES (?, ?, ?)";
        try (Connection connection = database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setDouble(1, perbendaharaan.getKasSebelumnya());
            ps.setDouble(2, perbendaharaan.getTotalKas());
            ps.setDouble(3, perbendaharaan.getPengeluaran());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error adding perbendaharaan: " + e.getMessage());
        }
    }

    @Override
    public List<Perbendaharaan> getAllPerbendaharaan() {
        List<Perbendaharaan> perbendaharaanList = new ArrayList<>();
        String query = "SELECT * FROM perbendaharaan";

        try (Connection connection = database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                perbendaharaanList.add(new Perbendaharaan(
                        rs.getInt("id"),
                        rs.getDouble("kas_sebelumnya"),
                        rs.getDouble("total_kas"),
                        rs.getDouble("pengeluaran")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all perbendaharaan: " + e.getMessage());
        }
        return perbendaharaanList;
    }
}
