package com.mycompany.sistemayamgeprek;

public class Dapur {
    private String nama;
    private int kapasitas;

    public Dapur(String nama, int kapasitas) {
        this.nama = nama;
        this.kapasitas = kapasitas;
    }

    public void tampilkanInfo() {
        System.out.println("Nama Dapur: " + nama);
        System.out.println("Kapasitas Dapur: " + kapasitas + " pesanan");
    }
}