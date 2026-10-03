package com.mycompany.smartlibrary;

public class BukuCetak extends Koleksi {
    private int jumlahHalaman;
    
    public BukuCetak(String judul, String pengarang, int tahunTerbit, int jumlahHalaman) {
        super(judul, pengarang, tahunTerbit); 
        this.jumlahHalaman = jumlahHalaman;
    }
    
    @Override
    public void tampilkanInfo() {
        System.out.printf("[Buku Cetak] Judul: %-15s | Pengarang: %-10s | Tahun: %d | Halaman: %d Hal%n", 
                          this.judul, this.pengarang, this.tahunTerbit, this.jumlahHalaman);
    }
    @Override
    public void caraPinjam() {
        System.out.println("-> Info Pinjam: Buku cetak wajib diambil fisik bukunya di meja administrasi perpustakaan.");
    }
}