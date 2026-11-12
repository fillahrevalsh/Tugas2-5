package com.mycompany.sistemayamgeprek;

public class PaketGeprek extends AyamGeprek implements BisaDipesan {

    private String isiPaket;

    public PaketGeprek(String nama, double harga, String isiPaket) {
        super(nama, harga);
        this.isiPaket = isiPaket;
    }

    @Override
    public void deskripsiKhusus() {
        System.out.println("Jenis: Paket Ayam Geprek");
        System.out.println("Isi Paket: " + isiPaket);
    }

    @Override
    public void prosesPemesanan() {
        System.out.println("Paket ayam geprek sedang diproses.");
    }
}
