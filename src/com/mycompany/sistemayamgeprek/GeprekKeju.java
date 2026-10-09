package com.mycompany.sistemayamgeprek;

public class GeprekKeju extends AyamGeprek {

    private String jenisKeju;

    public GeprekKeju(String nama, double harga, String jenisKeju) {
        super(nama, harga);
        setJenisKeju(jenisKeju);
    }

    public String getJenisKeju() {
        return this.jenisKeju;
    }

    public void setJenisKeju(String jenisKeju) {
        if (jenisKeju != null && !jenisKeju.isEmpty()) {
            this.jenisKeju = jenisKeju;
        } else {
            System.out.println("Jenis keju tidak boleh kosong!");
        }
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf(
                "[Geprek Keju] Nama: %-15s | Harga: Rp%.0f | Keju: %s%n",
                this.getNama(),
                this.getHarga(),
                this.jenisKeju
        );
    }

    @Override
    public void caraPesan() {
        System.out.println(
                "-> Geprek keju dipesan dengan memilih jenis keju."
        );
    }
}