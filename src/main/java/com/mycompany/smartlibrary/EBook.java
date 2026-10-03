package com.mycompany.smartlibrary;

import com.mycompany.smartlibrary.Koleksi;

public class EBook extends Koleksi {
    private int ukuranFileMB;
    
    public EBook(String judul, String pengarang, int tahunTerbit, int ukuranFileMB) {
        super(judul, pengarang, tahunTerbit);
        this.ukuranFileMB = ukuranFileMB;
    }
    
    @Override
    public void tampilkanInfo() {
        System.out.printf("[E-Book]     Judul: %-15s | Pengarang: %-10s | Tahun: %d | Ukuran: %d MB%n", 
                          this.judul, this.pengarang, this.tahunTerbit, this.ukuranFileMB);
    }
    @Override
    public void caraPinjam() {
        System.out.println("-> Info Pinjam: E-Book dipinjam dengan cara di-download melalui aplikasi/situs web.");
    }
}
