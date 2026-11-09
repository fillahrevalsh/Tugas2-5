package com.mycompany.sistemayamgeprek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemAyamGeprek {

    // AGGREGATION: sistem mengelola objek pesanan dan pelanggan
    private ArrayList<AyamGeprek> daftarPesanan;
    private HashMap<String, Pelanggan> dataPelanggan;

    // COMPOSITION: objek Dapur dibuat langsung di dalam constructor
    private Dapur dapur;

    public SistemAyamGeprek() {
        daftarPesanan = new ArrayList<>();
        dataPelanggan = new HashMap<>();

        dapur = new Dapur("Dapur Utama Ayam Geprek", 50);
    }

    // Method untuk menambahkan pesanan
    public void tambahPesanan(AyamGeprek pesanan) {
        daftarPesanan.add(pesanan);
        System.out.println("Pesanan berhasil ditambahkan!");
    }

    // Method untuk menampilkan semua pesanan
    public void tampilkanDaftarPesanan() {
        if (daftarPesanan.isEmpty()) {
            System.out.println("Belum ada pesanan.");
            return;
        }

        System.out.println("\n=== DAFTAR PESANAN ===");

        for (int i = 0; i < daftarPesanan.size(); i++) {
            System.out.println("\nNomor Pesanan: " + (i + 1));
            daftarPesanan.get(i).tampilkanInfo();
        }

        System.out.println("\nJumlah Pesanan: " + daftarPesanan.size());
    }

    // Pencarian berdasarkan nama
    public AyamGeprek cariPesanan(String nama)
            throws PesananTidakDitemukanException {

        for (int i = 0; i < daftarPesanan.size(); i++) {
            if (daftarPesanan.get(i).getNama().equalsIgnoreCase(nama)) {
                return daftarPesanan.get(i);
            }
        }

        throw new PesananTidakDitemukanException(
                "Pesanan dengan nama " + nama + " tidak ditemukan!"
        );
    }

    // Method overloading: pencarian berdasarkan harga
    public AyamGeprek cariPesanan(double harga)
            throws PesananTidakDitemukanException {

        for (int i = 0; i < daftarPesanan.size(); i++) {
            if (daftarPesanan.get(i).getHarga() == harga) {
                return daftarPesanan.get(i);
            }
        }

        throw new PesananTidakDitemukanException(
                "Pesanan dengan harga tersebut tidak ditemukan!"
        );
    }

    // Method untuk menambahkan pelanggan
    public void tambahPelanggan(Pelanggan pelanggan) {
        if (dataPelanggan.containsKey(pelanggan.getId())) {
            System.out.println("ID pelanggan sudah digunakan!");
        } else {
            dataPelanggan.put(pelanggan.getId(), pelanggan);
            System.out.println("Pelanggan berhasil ditambahkan!");
        }
    }

    // Method untuk mencari pelanggan
    public Pelanggan cariPelanggan(String id) {
        if (dataPelanggan.containsKey(id)) {
            return dataPelanggan.get(id);
        }

        return null;
    }

    // Method untuk menampilkan seluruh pelanggan
    public void tampilkanDaftarPelanggan() {
        if (dataPelanggan.isEmpty()) {
            System.out.println("Belum ada data pelanggan.");
            return;
        }

        System.out.println("\n=== DAFTAR PELANGGAN ===");

        for (String id : dataPelanggan.keySet()) {
            dataPelanggan.get(id).tampilkanInfo();
            System.out.println("--------------------");
        }
    }

    // Menampilkan informasi komponen Composition
    public void tampilkanInfoDapur() {
        System.out.println("\n=== INFORMASI COMPOSITION ===");
        dapur.tampilkanInfo();
    }

    // Memproses pesanan dan interface dari Modul 7
    public void prosesPesanan(AyamGeprek pesanan) {
        pesanan.deskripsiKhusus();

        if (pesanan instanceof BisaDipesan) {
            BisaDipesan dataPesanan = (BisaDipesan) pesanan;
            dataPesanan.prosesPemesanan();
        }

        if (pesanan instanceof BisaDiberiTopping) {
            BisaDiberiTopping dataTopping = (BisaDiberiTopping) pesanan;
            dataTopping.tambahTopping();
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        SistemAyamGeprek sistem = new SistemAyamGeprek();

        // Objek pesanan dibuat di luar class pengelola,
        // kemudian dimasukkan melalui method tambahPesanan().
        sistem.tambahPesanan(new GeprekBiasa("Geprek Original", 15000, 2));
        sistem.tambahPesanan(new PaketGeprek("Paket Hemat", 20000,
                "Ayam, Nasi, dan Es Teh"));
        sistem.tambahPesanan(new GeprekKeju("Geprek Mozarella", 22000,
                "Mozarella"));
        sistem.tambahPesanan(new GeprekBiasa("Geprek Pedas", 17000, 5));
        sistem.tambahPesanan(new GeprekKeju("Geprek Cheddar", 21000,
                "Cheddar"));

        // Objek pelanggan dibuat di luar class pengelola,
        // kemudian dimasukkan melalui method tambahPelanggan().
        Pelanggan p1 = new Pelanggan("P001", "Budi");
        Pelanggan p2 = new Pelanggan("P002", "Siti");
        Pelanggan p3 = new Pelanggan("P003", "Andi");

        sistem.tambahPelanggan(p1);
        sistem.tambahPelanggan(p2);
        sistem.tambahPelanggan(p3);

        // Objek transaksi untuk menerapkan Association
        TransaksiPenjualan transaksi = new TransaksiPenjualan();

        boolean berjalan = true;

        while (berjalan) {
            try {
                System.out.println("\n===== SISTEM PENJUALAN AYAM GEPREK =====");
                System.out.println("1. Tambah Pesanan");
                System.out.println("2. Tampilkan Daftar Pesanan");
                System.out.println("3. Cari Pesanan");
                System.out.println("4. Kelola Data Pelanggan");
                System.out.println("5. Informasi Dapur");
                System.out.println("6. Proses Transaksi");
                System.out.println("7. Keluar");
                System.out.print("Pilih menu: ");

                int pilihan = input.nextInt();
                input.nextLine();

                switch (pilihan) {
                    case 1:
                        System.out.print("Masukkan nama pesanan: ");
                        String nama = input.nextLine();

                        System.out.print("Masukkan harga: ");
                        double harga = input.nextDouble();
                        input.nextLine();

                        if (nama.isEmpty() || harga <= 0) {
                            throw new IllegalArgumentException(
                                    "Nama atau harga pesanan tidak valid!"
                            );
                        }

                        System.out.println("Pilih jenis pesanan:");
                        System.out.println("1. Geprek Biasa");
                        System.out.println("2. Paket Geprek");
                        System.out.println("3. Geprek Keju");
                        System.out.print("Pilihan: ");

                        int jenis = input.nextInt();
                        input.nextLine();

                        if (jenis == 1) {
                            System.out.print("Level pedas (1-5): ");
                            int level = input.nextInt();
                            input.nextLine();

                            sistem.tambahPesanan(
                                    new GeprekBiasa(nama, harga, level)
                            );
                        } else if (jenis == 2) {
                            System.out.print("Isi paket: ");
                            String isi = input.nextLine();

                            sistem.tambahPesanan(
                                    new PaketGeprek(nama, harga, isi)
                            );
                        } else if (jenis == 3) {
                            System.out.print("Jenis keju: ");
                            String keju = input.nextLine();

                            sistem.tambahPesanan(
                                    new GeprekKeju(nama, harga, keju)
                            );
                        } else {
                            System.out.println("Jenis pesanan tidak tersedia.");
                        }
                        break;

                    case 2:
                        sistem.tampilkanDaftarPesanan();
                        break;

                    case 3:
                        System.out.println("1. Cari berdasarkan nama");
                        System.out.println("2. Cari berdasarkan harga");
                        System.out.print("Pilih pencarian: ");

                        int cari = input.nextInt();
                        input.nextLine();

                        if (cari == 1) {
                            System.out.print("Masukkan nama pesanan: ");
                            String kataKunci = input.nextLine();

                            AyamGeprek hasil = sistem.cariPesanan(kataKunci);
                            hasil.tampilkanInfo();

                        } else if (cari == 2) {
                            System.out.print("Masukkan harga: ");
                            double hargaCari = input.nextDouble();
                            input.nextLine();

                            AyamGeprek hasil = sistem.cariPesanan(hargaCari);
                            hasil.tampilkanInfo();
                        } else {
                            System.out.println("Pilihan pencarian tidak valid.");
                        }
                        break;

                    case 4:
                        System.out.println("\n=== KELOLA PELANGGAN ===");
                        System.out.println("1. Tambah Pelanggan");
                        System.out.println("2. Cari Pelanggan");
                        System.out.println("3. Tampilkan Semua Pelanggan");
                        System.out.print("Pilih menu: ");

                        int menuPelanggan = input.nextInt();
                        input.nextLine();

                        if (menuPelanggan == 1) {
                            System.out.print("Masukkan ID pelanggan: ");
                            String id = input.nextLine();

                            System.out.print("Masukkan nama pelanggan: ");
                            String namaPelanggan = input.nextLine();

                            if (id.isEmpty() || namaPelanggan.isEmpty()) {
                                throw new IllegalArgumentException(
                                        "ID dan nama pelanggan tidak boleh kosong!"
                                );
                            }

                            Pelanggan pelangganBaru =
                                    new Pelanggan(id, namaPelanggan);

                            sistem.tambahPelanggan(pelangganBaru);

                        } else if (menuPelanggan == 2) {
                            System.out.print("Masukkan ID pelanggan: ");
                            String id = input.nextLine();

                            Pelanggan hasil = sistem.cariPelanggan(id);

                            if (hasil != null) {
                                hasil.tampilkanInfo();
                            } else {
                                System.out.println("Pelanggan tidak ditemukan!");
                            }

                        } else if (menuPelanggan == 3) {
                            sistem.tampilkanDaftarPelanggan();

                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                        break;

                    case 5:
                        sistem.tampilkanInfoDapur();
                        break;

                    case 6:
                        sistem.tampilkanDaftarPelanggan();
                        System.out.print("\nMasukkan ID pelanggan: ");
                        String idTransaksi = input.nextLine();

                        Pelanggan pelangganTransaksi =
                                sistem.cariPelanggan(idTransaksi);

                        if (pelangganTransaksi == null) {
                            System.out.println("Pelanggan tidak ditemukan!");
                            break;
                        }

                        sistem.tampilkanDaftarPesanan();
                        System.out.print("Pilih nomor pesanan: ");
                        int nomor = input.nextInt();
                        input.nextLine();

                        if (nomor < 1 || nomor > sistem.daftarPesanan.size()) {
                            System.out.println("Nomor pesanan tidak valid!");
                            break;
                        }

                        AyamGeprek pesananTransaksi =
                                sistem.daftarPesanan.get(nomor - 1);

                        sistem.prosesPesanan(pesananTransaksi);

                        transaksi.prosesTransaksi(
                                pelangganTransaksi,
                                pesananTransaksi
                        );
                        break;

                    case 7:
                        berjalan = false;
                        System.out.println("Terima kasih telah menggunakan program!");
                        break;

                    default:
                        System.out.println("Pilihan menu tidak tersedia.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Kesalahan: Input harus berupa angka!");
                input.nextLine();

            } catch (PesananTidakDitemukanException e) {
                System.out.println("Kesalahan: " + e.getMessage());

            } catch (IllegalArgumentException e) {
                System.out.println("Kesalahan: " + e.getMessage());

            } finally {
                System.out.println("Blok finally: Proses menu telah selesai.");
            }
        }

        input.close();
    }
}