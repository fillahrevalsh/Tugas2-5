
package com.mycompany.sistemayamgeprek;

public class GeprekKeju extends AyamGeprek
        implements BisaDipesan, BisaDiberiTopping {

    private String jenisKeju;

    public GeprekKeju(
            String nama, double harga, String jenisKeju) {

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
                "Geprek keju dipesan dengan memilih jenis keju."
        );
    }

    @Override
    public void deskripsiKhusus() {
        System.out.println(
                "Jenis: ayam geprek dengan tambahan keju."
        );
    }

    @Override
    public void prosesPemesanan() {
        System.out.println(
                "Geprek keju sedang disiapkan dengan keju pilihan."
        );
    }

    @Override
    public void tambahTopping() {
        System.out.println(
                "Topping keju " + this.jenisKeju
                + " ditambahkan ke pesanan."
        );
    }
}
