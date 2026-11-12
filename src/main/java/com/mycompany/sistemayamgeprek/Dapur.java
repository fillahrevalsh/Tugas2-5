package com.mycompany.sistemayamgeprek;

import java.io.Serializable;

public class Dapur implements Serializable {

    private static final long serialVersionUID = 1L;

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
