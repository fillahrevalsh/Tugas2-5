package com.mycompany.smartlibrary;

public class Buku {
    private String judul;
    private String pengarang;
    private int tahunTerbit;
    
    public static int totalBukuBerhasilDibuat = 0;

    public Buku(String judul, String pengarang, int tahunTerbit) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
        
        totalBukuBerhasilDibuat++;
    }

    public String getJudul() {
        return this.judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return this.pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public int getTahunTerbit() {
        return this.tahunTerbit;
    }

    public void setTahunTerbit(int tahunTerbit) {
        if (tahunTerbit > 0) {
            this.tahunTerbit = tahunTerbit;
        } else {
            System.out.println("Tahun terbit tidak valid!");
        }
    }

    public void tampilkanInfoBuku() {
        System.out.printf("Judul: %-20s | Pengarang: %-15s | Tahun: %d%n", 
                          this.judul, this.pengarang, this.tahunTerbit);
    }
}
