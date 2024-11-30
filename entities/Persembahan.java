package entities;

public class Persembahan {
    private double jumlah;

    public Persembahan(double jumlah) {
        this.jumlah = jumlah;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    @Override
    public String toString() {
        return String.valueOf(jumlah);
    }
}
