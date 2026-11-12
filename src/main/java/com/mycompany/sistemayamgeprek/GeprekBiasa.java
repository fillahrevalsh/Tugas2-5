package com.mycompany.sistemayamgeprek;

public class GeprekBiasa extends AyamGeprek implements BisaDipesan {

    private String levelPedas;

    public GeprekBiasa(String nama, double harga, String levelPedas) {
        super(nama, harga);
        this.levelPedas = levelPedas;
    }

    @Override
    public void deskripsiKhusus() {
        System.out.println("Jenis: Ayam Geprek Biasa");
        System.out.println("Level Pedas: " + levelPedas);
    }

    @Override
    public void prosesPemesanan() {
        System.out.println("Pesanan ayam geprek biasa sedang diproses.");
    }
}
