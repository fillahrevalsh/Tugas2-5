package com.mycompany.sistemayamgeprek;

public class PaketGeprek extends AyamGeprek {

    private String isiPaket;

    public PaketGeprek(
            String nama,
            double harga,
            String isiPaket) {

        super(nama, harga);
        this.isiPaket = isiPaket;
    }

    public String getIsiPaket() {
        return this.isiPaket;
    }

    public void setIsiPaket(String isiPaket) {
        if (isiPaket != null && !isiPaket.isEmpty()) {
            this.isiPaket = isiPaket;
        } else {
            System.out.println(
                    "Isi paket tidak boleh kosong!"
            );
        }
    }

    @Override
    public void tampilkanInfo() {

        System.out.printf(
                "[Paket Geprek] Nama: %-15s | Harga: Rp%.0f | Isi: %s%n",
                this.getNama(),
                this.getHarga(),
                this.isiPaket
        );
    }

    @Override
    public void caraPesan() {

        System.out.println(
                "-> Paket geprek berisi ayam, nasi, dan tambahan sesuai paket."
        );
    }
}