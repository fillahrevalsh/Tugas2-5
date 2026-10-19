
package com.mycompany.sistemayamgeprek;

import java.util.Scanner;

public class SistemAyamGeprek {

    // Compile-Time Polymorphism: mencari berdasarkan nama
    public static void cariPesanan(
            String nama,
            AyamGeprek[] daftarPesanan,
            int jumlahPesanan) {

        boolean ditemukan = false;

        for (int i = 0; i < jumlahPesanan; i++) {
            if (daftarPesanan[i].getNama()
                    .equalsIgnoreCase(nama)) {

                daftarPesanan[i].tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Pesanan tidak ditemukan.");
        }
    }

    // Compile-Time Polymorphism: mencari berdasarkan harga
    public static void cariPesanan(
            double harga,
            AyamGeprek[] daftarPesanan,
            int jumlahPesanan) {

        boolean ditemukan = false;

        for (int i = 0; i < jumlahPesanan; i++) {
            if (daftarPesanan[i].getHarga() == harga) {
                daftarPesanan[i].tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Pesanan tidak ditemukan.");
        }
    }

    // Parameter bertipe abstract superclass
    public static void prosesPesanan(AyamGeprek pesanan) {

        System.out.println("\n--- Proses Pesanan ---");

        // Abstract method yang sudah di-override subclass
        pesanan.deskripsiKhusus();

        // Pengecekan interface sebelum pemanggilan method
        if (pesanan instanceof BisaDipesan) {
            BisaDipesan dataPesanan = (BisaDipesan) pesanan;
            dataPesanan.prosesPemesanan();
        }

        if (pesanan instanceof BisaDiberiTopping) {
            BisaDiberiTopping dataTopping =
                    (BisaDiberiTopping) pesanan;

            dataTopping.tambahTopping();
        }

        System.out.println("Simulasi selesai.");
    }

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            // Array bertipe superclass abstract
            AyamGeprek[] daftarPesanan = new AyamGeprek[10];

            int jumlahPesanan = 0;
            boolean isRunning = true;

            // Lima data awal mencakup semua subclass
            daftarPesanan[jumlahPesanan++] =
                    new GeprekBiasa("Geprek Original", 15000, 2);

            daftarPesanan[jumlahPesanan++] =
                    new PaketGeprek(
                            "Paket Hemat", 20000,
                            "Ayam + Nasi + Es Teh");

            daftarPesanan[jumlahPesanan++] =
                    new GeprekKeju(
                            "Geprek Mozarella", 22000, "Mozarella");

            daftarPesanan[jumlahPesanan++] =
                    new GeprekBiasa("Geprek Pedas", 17000, 5);

            daftarPesanan[jumlahPesanan++] =
                    new GeprekKeju(
                            "Geprek Cheddar", 21000, "Cheddar");

            while (isRunning) {

                System.out.println("\n========================================");
                System.out.println("       SISTEM PENJUALAN AYAM GEPREK");
                System.out.println("========================================");
                System.out.println("1. Tambah Pesanan");
                System.out.println("2. Lihat Daftar Pesanan");
                System.out.println("3. Cari Pesanan");
                System.out.println("4. Simulasi Abstract dan Interface");
                System.out.println("5. Keluar");
                System.out.print("Pilih menu: ");

                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {

                    case 1 -> {
                        if (jumlahPesanan < daftarPesanan.length) {

                            System.out.println("1. Geprek Biasa");
                            System.out.println("2. Paket Geprek");
                            System.out.println("3. Geprek Keju");
                            System.out.print("Pilih jenis: ");

                            int jenis = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Nama pesanan: ");
                            String nama = scanner.nextLine();

                            System.out.print("Harga: ");
                            double harga = scanner.nextDouble();
                            scanner.nextLine();

                            if (jenis == 1) {

                                System.out.print("Level pedas (1-5): ");
                                int level = scanner.nextInt();
                                scanner.nextLine();

                                daftarPesanan[jumlahPesanan] =
                                        new GeprekBiasa(
                                                nama, harga, level);

                                jumlahPesanan++;

                            } else if (jenis == 2) {

                                System.out.print("Isi paket: ");
                                String isi = scanner.nextLine();

                                daftarPesanan[jumlahPesanan] =
                                        new PaketGeprek(
                                                nama, harga, isi);

                                jumlahPesanan++;

                            } else if (jenis == 3) {

                                System.out.print("Jenis keju: ");
                                String keju = scanner.nextLine();

                                daftarPesanan[jumlahPesanan] =
                                        new GeprekKeju(
                                                nama, harga, keju);

                                jumlahPesanan++;

                            } else {
                                System.out.println(
                                        "Jenis pesanan tidak valid.");
                            }

                        } else {
                            System.out.println("Kapasitas pesanan penuh!");
                        }
                    }

                    case 2 -> {
                        System.out.println("\n--- Daftar Pesanan ---");

                        for (int i = 0; i < jumlahPesanan; i++) {
                            System.out.print((i + 1) + ". ");

                            // Runtime polymorphism / dynamic binding
                            daftarPesanan[i].tampilkanInfo();

                            // Abstract method: perilaku subclass
                            daftarPesanan[i].deskripsiKhusus();
                            System.out.println();
                        }

                        System.out.println(
                                "Total objek dibuat: "
                                + AyamGeprek.totalPesananBerhasilDibuat);
                    }

                    case 3 -> {
                        System.out.println("1. Cari berdasarkan nama");
                        System.out.println("2. Cari berdasarkan harga");
                        System.out.print("Pilih: ");

                        int mode = scanner.nextInt();
                        scanner.nextLine();

                        if (mode == 1) {
                            System.out.print("Nama: ");
                            String nama = scanner.nextLine();

                            cariPesanan(
                                    nama, daftarPesanan, jumlahPesanan);

                        } else if (mode == 2) {
                            System.out.print("Harga: ");
                            double harga = scanner.nextDouble();
                            scanner.nextLine();

                            cariPesanan(
                                    harga, daftarPesanan, jumlahPesanan);

                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                    }

                    case 4 -> {
                        System.out.println("\n--- Pilih Pesanan ---");

                        for (int i = 0; i < jumlahPesanan; i++) {
                            System.out.print((i + 1) + ". ");
                            daftarPesanan[i].tampilkanInfo();
                        }

                        System.out.print("Nomor pesanan: ");
                        int nomor = scanner.nextInt();
                        scanner.nextLine();

                        if (nomor >= 1 && nomor <= jumlahPesanan) {

                            // Objek subclass dikirim sebagai superclass
                            prosesPesanan(daftarPesanan[nomor - 1]);

                        } else {
                            System.out.println(
                                    "Nomor pesanan tidak valid.");
                        }
                    }

                    case 5 -> {
                        System.out.println(
                                "Terima kasih. Program selesai.");
                        isRunning = false;
                    }

                    default -> System.out.println(
                            "Pilihan tidak valid. Masukkan angka 1-5.");
                }

                if (isRunning) {
                    System.out.print(
                            "\nTekan Enter untuk melanjutkan...");
                    scanner.nextLine();
                }
            }
        }
    }
}
