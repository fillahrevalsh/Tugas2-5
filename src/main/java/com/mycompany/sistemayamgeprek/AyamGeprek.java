package com.mycompany.sistemayamgeprek;

import java.io.Serializable;

public abstract class AyamGeprek implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nama;
    private double harga;
    private static int jumlahPesanan = 0;

    public AyamGeprek(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
        jumlahPesanan++;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public static int getJumlahPesanan() {
        return jumlahPesanan;
    }

    public void tampilkanInfo() {
        System.out.println("Nama Pesanan: " + nama);
        System.out.println("Harga: Rp" + String.format("%.0f", harga));
    }

    public void caraPesan() {
        System.out.println("Pesanan diproses oleh sistem.");
    }

    public abstract void deskripsiKhusus();
}
