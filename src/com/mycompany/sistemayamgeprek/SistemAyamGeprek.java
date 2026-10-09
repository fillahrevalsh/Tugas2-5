package com.mycompany.sistemayamgeprek;
import java.util.Scanner;
public class SistemAyamGeprek {

    // Compile-Time Polymorphism / Method Overloading
    public static void cariPesanan(
            String nama,
            AyamGeprek[] daftarPesanan,
            int jumlahPesanan) {

        System.out.println("Mencari pesanan dengan Nama: " + nama);

        boolean ditemukan = false;

        for (int i = 0; i < jumlahPesanan; i++) {
            if (daftarPesanan[i].getNama().equalsIgnoreCase(nama)) {
                daftarPesanan[i].tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Pesanan tidak ditemukan.");
        }
    }

    // Compile-Time Polymorphism / Method Overloading
    public static void cariPesanan(
            double harga,
            AyamGeprek[] daftarPesanan,
            int jumlahPesanan) {

        System.out.println("Mencari pesanan dengan Harga: Rp" + harga);

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

    // Runtime Polymorphism dan Dynamic Binding
    // Parameter menggunakan tipe Superclass
    public static void prosesPesanan(AyamGeprek pesanan) {

        System.out.println("\n--- Simulasi Proses Pesanan ---");

        pesanan.tampilkanInfo();
        pesanan.caraPesan();

        System.out.println("Pesanan sedang diproses...");
        System.out.println("Pesanan berhasil diproses!");
    }

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            // Reference Management:
            // Array bertipe Superclass
            AyamGeprek[] daftarPesanan = new AyamGeprek[10];

            int jumlahPesanan = 0;
            boolean isRunning = true;

            // Data awal agar langsung memiliki 3-5 objek
            // dari seluruh variasi subclass

            daftarPesanan[jumlahPesanan] =
                    new GeprekBiasa(
                            "Geprek Original",
                            15000,
                            2
                    );
            jumlahPesanan++;

            daftarPesanan[jumlahPesanan] =
                    new PaketGeprek(
                            "Paket Hemat",
                            20000,
                            "Ayam + Nasi + Es Teh"
                    );
            jumlahPesanan++;

            daftarPesanan[jumlahPesanan] =
                    new GeprekKeju(
                            "Geprek Mozarella",
                            22000,
                            "Mozarella"
                    );
            jumlahPesanan++;

            daftarPesanan[jumlahPesanan] =
                    new GeprekBiasa(
                            "Geprek Pedas",
                            17000,
                            5
                    );
            jumlahPesanan++;

            daftarPesanan[jumlahPesanan] =
                    new GeprekKeju(
                            "Geprek Cheddar",
                            21000,
                            "Cheddar"
                    );
            jumlahPesanan++;

            System.out.println("========================================");
            System.out.println("     SELAMAT DATANG DI AYAM GEPREK!");
            System.out.println("========================================");

            while (isRunning) {

                System.out.println("\nMenu Utama:");
                System.out.println("1. Tambah Pesanan");
                System.out.println("2. Lihat Daftar Pesanan");
                System.out.println("3. Cari Pesanan (Fitur Overloading)");
                System.out.println("4. Simulasi Proses Pesanan");
                System.out.println("5. Keluar");
                System.out.print("Pilih Menu: 1-5: ");

                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {

                    case 1 -> {

                        if (jumlahPesanan < daftarPesanan.length) {

                            System.out.println("\n-- Pilih Jenis Pesanan --");
                            System.out.println("1. Ayam Geprek Biasa");
                            System.out.println("2. Paket Geprek");
                            System.out.println("3. Geprek Keju");
                            System.out.print("Pilihan (1/2/3): ");

                            int jenis = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Masukkan Nama Pesanan: ");
                            String namaBaru = scanner.nextLine();

                            System.out.print("Masukkan Harga: ");
                            double hargaBaru = scanner.nextDouble();
                            scanner.nextLine();

                            if (jenis == 1) {

                                System.out.print(
                                        "Masukkan Level Pedas (1-5): "
                                );

                                int level = scanner.nextInt();
                                scanner.nextLine();

                                // Upcasting
                                daftarPesanan[jumlahPesanan] =
                                        new GeprekBiasa(
                                                namaBaru,
                                                hargaBaru,
                                                level
                                        );

                                jumlahPesanan++;

                                System.out.println(
                                        "Sukses! Ayam geprek berhasil ditambahkan."
                                );

                            } else if (jenis == 2) {

                                System.out.print("Masukkan Isi Paket: ");
                                String isiPaket = scanner.nextLine();

                                // Upcasting
                                daftarPesanan[jumlahPesanan] =
                                        new PaketGeprek(
                                                namaBaru,
                                                hargaBaru,
                                                isiPaket
                                        );

                                jumlahPesanan++;

                                System.out.println(
                                        "Sukses! Paket geprek berhasil ditambahkan."
                                );

                            } else if (jenis == 3) {

                                System.out.print("Masukkan Jenis Keju: ");
                                String jenisKeju = scanner.nextLine();

                                // Upcasting
                                daftarPesanan[jumlahPesanan] =
                                        new GeprekKeju(
                                                namaBaru,
                                                hargaBaru,
                                                jenisKeju
                                        );

                                jumlahPesanan++;

                                System.out.println(
                                        "Sukses! Geprek keju berhasil ditambahkan."
                                );

                            } else {

                                System.out.println(
                                        "Jenis pesanan tidak valid."
                                );
                            }

                        } else {

                            System.out.println(
                                    "Maaf, kapasitas pesanan penuh!"
                            );
                        }

                        System.out.print(
                                "Tekan Enter untuk melanjutkan..."
                        );
                        scanner.nextLine();
                    }

                    case 2 -> {

                        System.out.println(
                                "\n--- Daftar Pesanan Ayam Geprek ---"
                        );

                        if (jumlahPesanan == 0) {

                            System.out.println(
                                    "Belum ada pesanan yang tersimpan."
                            );

                        } else {

                            for (int i = 0; i < jumlahPesanan; i++) {

                                System.out.print((i + 1) + ". ");

                                // Runtime Polymorphism
                                // Dynamic Binding terjadi di sini
                                daftarPesanan[i].tampilkanInfo();

                                daftarPesanan[i].caraPesan();

                                System.out.println();
                            }

                            System.out.println(
                                    "* Total Pesanan: "
                                    + AyamGeprek.totalPesananBerhasilDibuat
                            );
                        }

                        System.out.print(
                                "Tekan Enter untuk melanjutkan..."
                        );
                        scanner.nextLine();
                    }

                    case 3 -> {

                        System.out.println(
                                "\n-- Fitur Cari Pesanan --"
                        );

                        System.out.println(
                                "1. Cari berdasarkan Nama (String)"
                        );

                        System.out.println(
                                "2. Cari berdasarkan Harga (Double)"
                        );

                        System.out.print("Pilih (1/2): ");

                        int modeCari = scanner.nextInt();
                        scanner.nextLine();

                        switch (modeCari) {

                            case 1 -> {

                                System.out.print(
                                        "Masukkan Nama Pesanan: "
                                );

                                String kataKunci =
                                        scanner.nextLine();

                                // Memanggil overloaded method String
                                cariPesanan(
                                        kataKunci,
                                        daftarPesanan,
                                        jumlahPesanan
                                );
                            }

                            case 2 -> {

                                System.out.print(
                                        "Masukkan Harga: "
                                );

                                double angkaKunci =
                                        scanner.nextDouble();

                                scanner.nextLine();

                                // Memanggil overloaded method double
                                cariPesanan(
                                        angkaKunci,
                                        daftarPesanan,
                                        jumlahPesanan
                                );
                            }

                            default -> {

                                System.out.println(
                                        "Pilihan tidak valid."
                                );
                            }
                        }

                        System.out.print(
                                "Tekan Enter untuk melanjutkan..."
                        );
                        scanner.nextLine();
                    }

                    case 4 -> {

                        System.out.println(
                                "\n-- Simulasi Proses Pesanan --"
                        );

                        if (jumlahPesanan == 0) {

                            System.out.println(
                                    "Belum ada pesanan."
                            );

                        } else {

                            for (int i = 0; i < jumlahPesanan; i++) {

                                System.out.print(
                                        (i + 1) + ". "
                                );

                                daftarPesanan[i].tampilkanInfo();
                            }

                            System.out.print(
                                    "Pilih nomor pesanan: "
                            );

                            int nomor =
                                    scanner.nextInt();

                            scanner.nextLine();

                            if (nomor >= 1
                                    && nomor <= jumlahPesanan) {

                                // Parameter Superclass
                                prosesPesanan(
                                        daftarPesanan[nomor - 1]
                                );

                            } else {

                                System.out.println(
                                        "Nomor pesanan tidak valid."
                                );
                            }
                        }

                        System.out.print(
                                "\nTekan Enter untuk melanjutkan..."
                        );
                        scanner.nextLine();
                    }

                    case 5 -> {

                        System.out.println(
                                "Terima kasih telah menggunakan "
                                + "Sistem Ayam Geprek!"
                        );

                        isRunning = false;
                    }

                    default -> {

                        System.out.println(
                                "Pilihan tidak valid. "
                                + "Silahkan masukkan angka 1-5."
                        );

                        scanner.nextLine();
                    }
                }
            }
        }
    }
}