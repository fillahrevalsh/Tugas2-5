
package com.mycompany.sistemayamgeprek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemAyamGeprek {

    public static void cariPesanan(
            String nama,
            ArrayList<AyamGeprek> daftarPesanan)
            throws PesananTidakDitemukanException {

        boolean ditemukan = false;

        for (int i = 0; i < daftarPesanan.size(); i++) {
            if (daftarPesanan.get(i).getNama()
                    .equalsIgnoreCase(nama)) {

                daftarPesanan.get(i).tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            throw new PesananTidakDitemukanException(
                    "Pesanan dengan nama " + nama + " tidak ditemukan!"
            );
        }
    }

    // Mencari pesanan berdasarkan harga (Overloading)
    public static void cariPesanan(
            double harga,
            ArrayList<AyamGeprek> daftarPesanan)
            throws PesananTidakDitemukanException {

        boolean ditemukan = false;

        for (int i = 0; i < daftarPesanan.size(); i++) {
            if (daftarPesanan.get(i).getHarga() == harga) {

                daftarPesanan.get(i).tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            throw new PesananTidakDitemukanException(
                    "Pesanan dengan harga Rp" + harga
                    + " tidak ditemukan!"
            );
        }
    }

    // Validasi input pesanan menggunakan throw dan throws
    public static void validasiPesanan(
            String nama, double harga) {

        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Nama pesanan tidak boleh kosong!"
            );
        }

        if (harga <= 0) {
            throw new IllegalArgumentException(
                    "Harga harus lebih dari nol!"
            );
        }
    }

    // Proses pesanan menggunakan superclass
    public static void prosesPesanan(AyamGeprek pesanan) {

        System.out.println("\n--- Proses Pesanan ---");

        pesanan.deskripsiKhusus();

        if (pesanan instanceof BisaDipesan) {
            BisaDipesan dataPesanan = (BisaDipesan) pesanan;
            dataPesanan.prosesPemesanan();
        }

        if (pesanan instanceof BisaDiberiTopping) {
            BisaDiberiTopping dataTopping =
                    (BisaDiberiTopping) pesanan;

            dataTopping.tambahTopping();
        }

        System.out.println("Proses pesanan selesai.");
    }

    public static void menuPelanggan(
            Scanner scanner,
            HashMap<String, String> dataPelanggan) {

        System.out.println("\n--- Menu Data Pelanggan ---");
        System.out.println("1. Tambah Pelanggan");
        System.out.println("2. Cari Pelanggan");
        System.out.println("3. Tampilkan Semua Pelanggan");
        System.out.print("Pilih: ");

        int pilihan = scanner.nextInt();
        scanner.nextLine();

        if (pilihan == 1) {

            System.out.print("Masukkan ID Pelanggan: ");
            String id = scanner.nextLine();

            System.out.print("Masukkan Nama Pelanggan: ");
            String nama = scanner.nextLine();

            if (id.trim().isEmpty() || nama.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "ID dan nama pelanggan tidak boleh kosong!"
                );
            }

            dataPelanggan.put(id, nama);

            System.out.println("Pelanggan berhasil ditambahkan.");

        } else if (pilihan == 2) {

            System.out.print("Masukkan ID Pelanggan: ");
            String id = scanner.nextLine();

            if (dataPelanggan.containsKey(id)) {
                System.out.println(
                        "Nama Pelanggan: " + dataPelanggan.get(id)
                );
            } else {
                System.out.println("Pelanggan tidak ditemukan.");
            }

        } else if (pilihan == 3) {

            if (dataPelanggan.isEmpty()) {
                System.out.println("Belum ada data pelanggan.");
            } else {
                System.out.println(
                        "Jumlah Pelanggan: " + dataPelanggan.size()
                );

                for (String id : dataPelanggan.keySet()) {
                    System.out.println(
                            "ID: " + id
                            + " | Nama: " + dataPelanggan.get(id)
                    );
                }
            }

        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<AyamGeprek> daftarPesanan =
                new ArrayList<>();

        HashMap<String, String> dataPelanggan =
                new HashMap<>();

        daftarPesanan.add(
                new GeprekBiasa("Geprek Original", 15000, 2)
        );

        daftarPesanan.add(
                new PaketGeprek(
                        "Paket Hemat", 20000,
                        "Ayam + Nasi + Es Teh")
        );

        daftarPesanan.add(
                new GeprekKeju(
                        "Geprek Mozarella", 22000, "Mozarella")
        );

        daftarPesanan.add(
                new GeprekBiasa("Geprek Pedas", 17000, 5)
        );

        daftarPesanan.add(
                new GeprekKeju(
                        "Geprek Cheddar", 21000, "Cheddar")
        );

        dataPelanggan.put("P001", "Budi");
        dataPelanggan.put("P002", "Siti");
        dataPelanggan.put("P003", "Andi");

        boolean isRunning = true;

        System.out.println("========================================");
        System.out.println("       SISTEM PENJUALAN AYAM GEPREK");
        System.out.println("========================================");

        while (isRunning) {

            try {

                System.out.println("\nMenu Utama:");
                System.out.println("1. Tambah Pesanan");
                System.out.println("2. Lihat Daftar Pesanan");
                System.out.println("3. Cari Pesanan");
                System.out.println("4. Simulasi Proses Pesanan");
                System.out.println("5. Kelola Data Pelanggan");
                System.out.println("6. Keluar");
                System.out.print("Pilih Menu: ");

                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {

                    case 1 -> {

                        System.out.println("\nJenis Pesanan:");
                        System.out.println("1. Geprek Biasa");
                        System.out.println("2. Paket Geprek");
                        System.out.println("3. Geprek Keju");
                        System.out.print("Pilih jenis: ");

                        int jenis = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Nama Pesanan: ");
                        String nama = scanner.nextLine();

                        System.out.print("Harga: ");
                        double harga = scanner.nextDouble();
                        scanner.nextLine();

                        validasiPesanan(nama, harga);

                        if (jenis == 1) {

                            System.out.print("Level Pedas (1-5): ");
                            int level = scanner.nextInt();
                            scanner.nextLine();

                            if (level < 1 || level > 5) {
                                throw new IllegalArgumentException(
                                        "Level pedas harus 1 sampai 5!"
                                );
                            }

                            daftarPesanan.add(
                                    new GeprekBiasa(nama, harga, level)
                            );

                        } else if (jenis == 2) {

                            System.out.print("Isi Paket: ");
                            String isi = scanner.nextLine();

                            if (isi.trim().isEmpty()) {
                                throw new IllegalArgumentException(
                                        "Isi paket tidak boleh kosong!"
                                );
                            }

                            daftarPesanan.add(
                                    new PaketGeprek(nama, harga, isi)
                            );

                        } else if (jenis == 3) {

                            System.out.print("Jenis Keju: ");
                            String keju = scanner.nextLine();

                            if (keju.trim().isEmpty()) {
                                throw new IllegalArgumentException(
                                        "Jenis keju tidak boleh kosong!"
                                );
                            }

                            daftarPesanan.add(
                                    new GeprekKeju(nama, harga, keju)
                            );

                        } else {
                            System.out.println(
                                    "Jenis pesanan tidak valid."
                            );
                        }

                        System.out.println(
                                "Jumlah pesanan sekarang: "
                                + daftarPesanan.size()
                        );
                    }

                    case 2 -> {

                        System.out.println("\n--- Daftar Pesanan ---");

                        if (daftarPesanan.isEmpty()) {
                            System.out.println(
                                    "Belum ada pesanan."
                            );
                        } else {

                            for (int i = 0;
                                    i < daftarPesanan.size(); i++) {

                                System.out.print((i + 1) + ". ");

                                daftarPesanan.get(i).tampilkanInfo();
                                daftarPesanan.get(i).deskripsiKhusus();
                                System.out.println();
                            }

                            System.out.println(
                                    "Total Pesanan: "
                                    + daftarPesanan.size()
                            );
                        }
                    }

                    case 3 -> {

                        System.out.println("\nCari Pesanan:");
                        System.out.println("1. Berdasarkan Nama");
                        System.out.println("2. Berdasarkan Harga");
                        System.out.print("Pilih: ");

                        int mode = scanner.nextInt();
                        scanner.nextLine();

                        if (mode == 1) {

                            System.out.print("Nama Pesanan: ");
                            String nama = scanner.nextLine();

                            cariPesanan(nama, daftarPesanan);

                        } else if (mode == 2) {

                            System.out.print("Harga: ");
                            double harga = scanner.nextDouble();
                            scanner.nextLine();

                            cariPesanan(harga, daftarPesanan);

                        } else {
                            System.out.println(
                                    "Pilihan tidak valid."
                            );
                        }
                    }

                    case 4 -> {

                        if (daftarPesanan.isEmpty()) {
                            System.out.println(
                                    "Belum ada pesanan."
                            );
                        } else {

                            for (int i = 0;
                                    i < daftarPesanan.size(); i++) {

                                System.out.print((i + 1) + ". ");
                                daftarPesanan.get(i).tampilkanInfo();
                            }

                            System.out.print("Pilih nomor pesanan: ");
                            int nomor = scanner.nextInt();
                            scanner.nextLine();

                            if (nomor >= 1
                                    && nomor <= daftarPesanan.size()) {

                                prosesPesanan(
                                        daftarPesanan.get(nomor - 1)
                                );

                            } else {
                                System.out.println(
                                        "Nomor pesanan tidak valid."
                                );
                            }
                        }
                    }

                    case 5 -> {
                        menuPelanggan(scanner, dataPelanggan);
                    }

                    case 6 -> {
                        System.out.println(
                                "Terima kasih telah menggunakan "
                                + "Sistem Ayam Geprek."
                        );

                        isRunning = false;
                    }

                    default -> {
                        System.out.println(
                                "Pilihan tidak valid. Masukkan 1-6."
                        );
                    }
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "Error: Input harus sesuai dengan tipe data!"
                );

                scanner.nextLine();

            } catch (PesananTidakDitemukanException e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );

            } finally {

                System.out.println(
                        "Blok finally: Proses menu telah selesai."
                );
            }

            if (isRunning) {
                System.out.print(
                        "Tekan Enter untuk melanjutkan..."
                );
                scanner.nextLine();
            }
        }

        scanner.close();
    }
}
