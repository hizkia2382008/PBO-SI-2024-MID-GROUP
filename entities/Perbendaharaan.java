package entities;

public class Perbendaharaan {
    private double kasSebelumnya;
    private double pengeluaran;
    private double totalKas;

    public Perbendaharaan(double kasSebelumnya, double pengeluaran) {
        this.kasSebelumnya = kasSebelumnya;
        this.pengeluaran = pengeluaran;
        this.totalKas = kasSebelumnya - pengeluaran;
    }

    public double getKasSebelumnya() {
        return kasSebelumnya;
    }

    public void setKasSebelumnya(double kasSebelumnya) {
        this.kasSebelumnya = kasSebelumnya;
    }

    public double getPengeluaran() {
        return pengeluaran;
    }

    public void setPengeluaran(double pengeluaran) {
        this.pengeluaran = pengeluaran;
    }

    public double getTotalKas() {
        return totalKas;
    }

    public void updateTotalKas(double persembahan) {
        this.totalKas = kasSebelumnya + persembahan - pengeluaran;
    }

    @Override
    public String toString() {
        return "Kas Sebelumnya: " + kasSebelumnya +
                ", Pengeluaran: " + pengeluaran +
                ", Total Kas: " + totalKas;
    }
}
