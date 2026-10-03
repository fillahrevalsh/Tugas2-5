public class Pesanan {
    private int id;
    private String nama;
    private double harga;

    private static int jumlahPesanan = 0;

    public Pesanan(int id, String nama, double harga) {
        this.id = id;
        this.nama = nama;
        setHarga(harga);
        jumlahPesanan++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama != null && !nama.trim().isEmpty()) {
            this.nama = nama;
        }
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        if (harga > 0) {
            this.harga = harga;
        }
    }

    public static int getJumlahPesanan() {
        return jumlahPesanan;
    }

    public void tampilkanInfo() {
        System.out.printf(
            "ID: %d | Nama: %s | Harga: Rp%.0f%n",
            id, nama, harga
        );
    }
}