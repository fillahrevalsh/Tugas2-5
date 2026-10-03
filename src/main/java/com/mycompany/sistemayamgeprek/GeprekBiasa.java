package com.mycompany.sistemayamgeprek;

public class GeprekBiasa extends AyamGeprek {

    private int levelPedas;

    public GeprekBiasa(
            String nama,
            double harga,
            int levelPedas) {

        super(nama, harga);
        this.levelPedas = levelPedas;
    }

    public int getLevelPedas() {
        return this.levelPedas;
    }

    public void setLevelPedas(int levelPedas) {
        if (levelPedas >= 1 && levelPedas <= 5) {
            this.levelPedas = levelPedas;
        } else {
            System.out.println(
                    "Level pedas harus antara 1 sampai 5!"
            );
        }
    }

    @Override
    public void tampilkanInfo() {

        System.out.printf(
                "[Geprek Biasa] Nama: %-15s | Harga: Rp%.0f | Level: %d%n",
                this.getNama(),
                this.getHarga(),
                this.levelPedas
        );
    }

    @Override
    public void caraPesan() {

        System.out.println(
                "-> Geprek biasa dipesan dengan memilih level pedas 1-5."
        );
    }
}