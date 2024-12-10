package entities;

public class Jemaat {
    private String nama;
    private String alamat;
    private double perpuluhan;

    public Jemaat(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
        this.perpuluhan = 0.0; // Default nilai perpuluhan
    }

    public Jemaat(String nama, String alamat, double perpuluhan) {
        this.nama = nama;
        this.alamat = alamat;
        this.perpuluhan = perpuluhan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public double getPerpuluhan() {
        return perpuluhan;
    }

    public void setPerpuluhan(double gaji) {
        this.perpuluhan = gaji * 0.1; // Menghitung perpuluhan secara langsung
    }

    @Override
    public String toString() {
        return nama + "," + alamat + "," + perpuluhan;
    }
}
