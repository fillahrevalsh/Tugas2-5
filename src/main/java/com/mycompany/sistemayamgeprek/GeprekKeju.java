package com.mycompany.sistemayamgeprek;

public class GeprekKeju extends AyamGeprek
        implements BisaDipesan, BisaDiberiTopping {

    private String jenisKeju;

    public GeprekKeju(String nama, double harga, String jenisKeju) {
        super(nama, harga);
        this.jenisKeju = jenisKeju;
    }

    @Override
    public void deskripsiKhusus() {
        System.out.println("Jenis: Ayam Geprek Keju");
        System.out.println("Jenis Keju: " + jenisKeju);
    }

    @Override
    public void prosesPemesanan() {
        System.out.println("Ayam geprek keju sedang diproses.");
    }

    @Override
    public void tambahTopping() {
        System.out.println("Topping " + jenisKeju + " berhasil ditambahkan.");
    }
}
