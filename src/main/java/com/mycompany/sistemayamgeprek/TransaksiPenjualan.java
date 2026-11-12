package com.mycompany.sistemayamgeprek;

import java.io.FileWriter;
import java.io.IOException;

public class TransaksiPenjualan {

    public void prosesTransaksi(Pelanggan pelanggan, AyamGeprek pesanan) {

        System.out.println("\n=== TRANSAKSI PENJUALAN ===");

        System.out.println("\nData Pelanggan:");
        pelanggan.tampilkanInfo();

        System.out.println("\nData Pesanan:");
        pesanan.tampilkanInfo();
        pesanan.deskripsiKhusus();

        System.out.println("\nTotal Pembayaran: Rp"
                + String.format("%.0f", pesanan.getHarga()));

        System.out.println("Status: Transaksi berhasil!");

        simpanLogTransaksi(pelanggan, pesanan);
    }

    public void simpanLogTransaksi(Pelanggan pelanggan,
            AyamGeprek pesanan) {

        try {
            FileWriter pipaTulis =
                    new FileWriter("log_transaksi.txt", true);

            pipaTulis.write("TRANSAKSI PENJUALAN\n");
            pipaTulis.write("ID Pelanggan: " + pelanggan.getId() + "\n");
            pipaTulis.write("Nama Pelanggan: " + pelanggan.getNama() + "\n");
            pipaTulis.write("Pesanan: " + pesanan.getNama() + "\n");
            pipaTulis.write("Total: Rp"
                    + String.format("%.0f", pesanan.getHarga()) + "\n");
            pipaTulis.write("Status: Berhasil\n");
            pipaTulis.write("--------------------------\n");

            pipaTulis.close();

            System.out.println("Riwayat transaksi berhasil disimpan.");

        } catch (IOException e) {
            System.out.println("Gagal menyimpan log: " + e.getMessage());
        }
    }
}
