package entities;

public class Entities {

    public static class Jemaat {
        private String nama;
        private String alamat;

        public Jemaat(String nama, String alamat) {
            this.nama = nama;
            this.alamat = alamat;
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

        @Override
        public String toString() {
            return nama + "," + alamat;
        }
    }

    public static class Perbendaharaan {
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

    public static class Persembahan {
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
}
