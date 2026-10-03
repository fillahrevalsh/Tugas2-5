 package com.mycompany.smartlibrary;

public class Koleksi {
    protected String judul;
    protected String pengarang;
    protected int tahunTerbit;
    
    public static int totalKoleksiBerhasilDibuat = 0;
    
    public Koleksi(String judul, String pengarang, int tahunTerbit) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
        totalKoleksiBerhasilDibuat++;
    }
    
    public String getJudul() { return this.judul; }
    public int getTahunTerbit() { return this.tahunTerbit; }
    
    public void tampilkanInfo() {
        System.out.printf("Judul: %-20s | Pengarang: %-15s | Tahun: %d%n", 
                          this.judul, this.pengarang, this.tahunTerbit);
    }
    
    public void caraPinjam() {
        System.out.println("Barang dipinjam secara fisik ke meja administrasi.");
    }
}
