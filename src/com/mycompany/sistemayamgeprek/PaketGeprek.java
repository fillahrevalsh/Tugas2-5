
package com.mycompany.sistemayamgeprek;

public class PaketGeprek extends AyamGeprek
        implements BisaDipesan {

    private String isiPaket;

    public PaketGeprek(
            String nama, double harga, String isiPaket) {

        super(nama, harga);
        setIsiPaket(isiPaket);
    }

    public String getIsiPaket() {
        return this.isiPaket;
    }

    public void setIsiPaket(String isiPaket) {
        if (isiPaket != null && !isiPaket.isEmpty()) {
            this.isiPaket = isiPaket;
        } else {
            System.out.println("Isi paket tidak boleh kosong!");
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
                "Paket geprek berisi ayam, nasi, dan tambahan sesuai paket."
        );
    }

    @Override
    public void deskripsiKhusus() {
        System.out.println(
                "Jenis: paket ayam geprek dengan beberapa menu pelengkap."
        );
    }

    @Override
    public void prosesPemesanan() {
        System.out.println(
                "Paket geprek sedang disiapkan lengkap dengan isi paket."
        );
    }
}
