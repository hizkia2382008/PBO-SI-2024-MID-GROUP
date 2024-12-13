package entity;

public class Entities {

    public static class Jemaat {
        private int id;
        private String nama;
        private String alamat;
        private double perpuluhan;

        public Jemaat(String nama, String alamat) {
            this.id = 0;  // Default value for id
            this.nama = nama;
            this.alamat = alamat;
            this.perpuluhan = 0.0;  // Default value for perpuluhan
        }

        public Jemaat(int id, String nama, String alamat, double perpuluhan) {
            this.id = id;
            this.nama = nama;
            this.alamat = alamat;
            this.perpuluhan = perpuluhan;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public void hitungPerpuluhan(double gaji) {
            if (gaji < 0) {
                throw new IllegalArgumentException("Gaji tidak boleh negatif.");
            }
            this.perpuluhan = gaji * 0.1;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Nama: " + nama + ", Alamat: " + alamat + ", Perpuluhan: " + perpuluhan;
        }
    }

    public static class Persembahan {
        private int id;
        private double jumlah;

        public Persembahan(double jumlah) {
            this.jumlah = jumlah;
        }

        public Persembahan(int id, double jumlah) {
            this.id = id;
            this.jumlah = jumlah;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getJumlah() {
            return jumlah;
        }

        public void setJumlah(double jumlah) {
            this.jumlah = jumlah;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Jumlah: " + jumlah;
        }
    }

    public static class Perbendaharaan {
        private int id; // Menyesuaikan dengan tabel database
        private double kasSebelumnya;
        private double totalKas;
        private double pengeluaran;

        public Perbendaharaan(double kasSebelumnya, double totalKas, double pengeluaran) {
            this.kasSebelumnya = kasSebelumnya;
            this.totalKas = totalKas;
            this.pengeluaran = pengeluaran;
        }

        public Perbendaharaan(int id, double kasSebelumnya, double totalKas, double pengeluaran) {
            this.id = id;
            this.kasSebelumnya = kasSebelumnya;
            this.totalKas = totalKas;
            this.pengeluaran = pengeluaran;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getKasSebelumnya() {
            return kasSebelumnya;
        }

        public void setKasSebelumnya(double kasSebelumnya) {
            this.kasSebelumnya = kasSebelumnya;
        }

        public double getTotalKas() {
            return totalKas;
        }

        public void setTotalKas(double totalKas) {
            this.totalKas = totalKas;
        }

        public double getPengeluaran() {
            return pengeluaran;
        }

        public void setPengeluaran(double pengeluaran) {
            this.pengeluaran = pengeluaran;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Kas Sebelumnya: " + kasSebelumnya + ", Total Kas: " + totalKas + ", Pengeluaran: " + pengeluaran;
        }
    }
}
