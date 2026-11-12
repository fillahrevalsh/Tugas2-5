package com.mycompany.sistemayamgeprek;

import java.io.Serializable;

public class Pelanggan implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String nama;

    public Pelanggan(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void tampilkanInfo() {
        System.out.println("ID Pelanggan: " + id);
        System.out.println("Nama Pelanggan: " + nama);
    }
}
