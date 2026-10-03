 package com.mycompany.smartlibrary;

public class Majalah extends Koleksi {
    private String edisi;

    public Majalah(String judul, String pengarang, int tahunTerbit, String edisi) {
        super(judul, pengarang, tahunTerbit);
        this.edisi = edisi;
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("[Majalah]   Judul: %-15s | Penerbit: %-10s | Tahun: %d | Edisi: %s%n", 
                          this.judul, this.pengarang, this.tahunTerbit, this.edisi);
    }

    @Override
    public void caraPinjam() {
        System.out.println("-> Info Pinjam: Majalah terbitan terbaru hanya dapat dibaca di ruang baca, tidak untuk dibawa pulang.");
    }
}
