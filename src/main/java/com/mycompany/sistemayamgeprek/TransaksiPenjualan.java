package com.mycompany.sistemayamgeprek;

public class TransaksiPenjualan {

    public void prosesTransaksi(Pelanggan pelanggan, AyamGeprek pesanan) {
        System.out.println("\n=== TRANSAKSI PENJUALAN ===");

        System.out.println("Data Pelanggan:");
        pelanggan.tampilkanInfo();

        System.out.println("\nData Pesanan:");
        pesanan.tampilkanInfo();

        System.out.println("\nTotal Pembayaran: Rp"
                + String.format("%.0f", pesanan.getHarga()));

        System.out.println("Status: Transaksi berhasil!");
    }
}