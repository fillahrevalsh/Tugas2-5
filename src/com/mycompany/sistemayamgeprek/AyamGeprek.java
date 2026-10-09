package com.mycompany.sistemayamgeprek;

public class AyamGeprek {

    private String nama;
    private double harga;

    public static int totalPesananBerhasilDibuat = 0;

    public AyamGeprek(String nama, double harga) {
        this.nama = nama;
        setHarga(harga);

        totalPesananBerhasilDibuat++;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        if (nama != null && !nama.isEmpty()) {
            this.nama = nama;
        } else {
            System.out.println("Nama tidak boleh kosong!");
        }
    }

    public double getHarga() {
        return this.harga;
    }

    public void setHarga(double harga) {
        if (harga > 0) {
            this.harga = harga;
        } else {
            System.out.println("Harga tidak valid!");
        }
    }

    public void tampilkanInfo() {
        System.out.printf(
                "Nama: %-20s | Harga: Rp%.0f%n",
                this.nama,
                this.harga
        );
    }

    public void caraPesan() {
        System.out.println(
                "-> Pesanan dapat dipesan langsung di kasir."
        );
    }
}