package com.mycompany.sistemayamgeprek;

import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class SistemAyamGeprek {

    private ArrayList<AyamGeprek> daftarPesanan;
    private HashMap<String, Pelanggan> dataPelanggan;
    private Dapur dapur;

    private Scanner input;

    public SistemAyamGeprek() {
        daftarPesanan = new ArrayList<>();
        dataPelanggan = new HashMap<>();
        dapur = new Dapur("Dapur Utama Ayam Geprek", 50);
        input = new Scanner(System.in);
    }

    // =========================
    // AGGREGATION: PESANAN
    // =========================

    public void tambahPesanan(AyamGeprek pesanan) {
        daftarPesanan.add(pesanan);
        System.out.println("Pesanan berhasil ditambahkan!");
    }

    public void tampilkanPesanan() {

        System.out.println("\n=== DAFTAR PESANAN AYAM GEPREK ===");

        if (daftarPesanan.isEmpty()) {
            System.out.println("Belum ada pesanan.");
            return;
        }

        for (int i = 0; i < daftarPesanan.size(); i++) {
            System.out.println("\nNomor Pesanan: " + (i + 1));
            daftarPesanan.get(i).tampilkanInfo();
            daftarPesanan.get(i).deskripsiKhusus();
        }
    }

    // =========================
    // PENCARIAN PESANAN
    // =========================

    public AyamGeprek cariPesanan(String nama)
            throws PesananTidakDitemukanException {

        for (AyamGeprek pesanan : daftarPesanan) {
            if (pesanan.getNama().equalsIgnoreCase(nama)) {
                return pesanan;
            }
        }

        throw new PesananTidakDitemukanException(
                "Pesanan dengan nama " + nama + " tidak ditemukan.");
    }

    public AyamGeprek cariPesanan(double harga)
            throws PesananTidakDitemukanException {

        for (AyamGeprek pesanan : daftarPesanan) {
            if (pesanan.getHarga() == harga) {
                return pesanan;
            }
        }

        throw new PesananTidakDitemukanException(
                "Pesanan dengan harga tersebut tidak ditemukan.");
    }

    // =========================
    // AGGREGATION: PELANGGAN
    // =========================

    public void tambahPelanggan(Pelanggan pelanggan) {

        if (dataPelanggan.containsKey(pelanggan.getId())) {
            System.out.println("ID pelanggan sudah digunakan.");
        } else {
            dataPelanggan.put(pelanggan.getId(), pelanggan);
            System.out.println("Pelanggan berhasil ditambahkan!");
        }
    }

    public Pelanggan cariPelanggan(String id) {

        if (dataPelanggan.containsKey(id)) {
            return dataPelanggan.get(id);
        }

        return null;
    }

    public void tampilkanPelanggan() {

        System.out.println("\n=== DAFTAR PELANGGAN ===");

        if (dataPelanggan.isEmpty()) {
            System.out.println("Belum ada pelanggan.");
            return;
        }

        for (Pelanggan pelanggan : dataPelanggan.values()) {
            pelanggan.tampilkanInfo();
            System.out.println("----------------------");
        }
    }

    // =========================
    // COMPOSITION: DAPUR
    // =========================

    public void tampilkanDapur() {
        System.out.println("\n=== INFORMASI DAPUR ===");
        dapur.tampilkanInfo();
    }

    // =========================
    // POLYMORPHISM DAN INTERFACE
    // =========================

    public void prosesPesanan(AyamGeprek pesanan) {

        System.out.println("\n=== PROSES PEMESANAN ===");

        pesanan.tampilkanInfo();
        pesanan.caraPesan();

        if (pesanan instanceof BisaDipesan) {
            BisaDipesan dapatDipesan = (BisaDipesan) pesanan;
            dapatDipesan.prosesPemesanan();
        }

        if (pesanan instanceof BisaDiberiTopping) {
            BisaDiberiTopping dapatTopping =
                    (BisaDiberiTopping) pesanan;

            dapatTopping.tambahTopping();
        }

        System.out.println("Proses pemesanan selesai.");
    }

    // =========================
    // SERIALIZATION
    // =========================

    public void simpanDataKeFile() {

        try {
            FileOutputStream fileKeluar =
                    new FileOutputStream("data_ayamgeprek.dat");

            ObjectOutputStream mesinPembeku =
                    new ObjectOutputStream(fileKeluar);

            mesinPembeku.writeObject(daftarPesanan);
            mesinPembeku.writeObject(dataPelanggan);

            mesinPembeku.close();

            System.out.println("\nData pesanan dan pelanggan berhasil disimpan!");
            System.out.println("File: data_ayamgeprek.dat");

        } catch (IOException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }

    // =========================
    // DESERIALIZATION
    // =========================

    @SuppressWarnings("unchecked")
    public void muatDataDariFile() {

        try {
            FileInputStream fileMasuk =
                    new FileInputStream("data_ayamgeprek.dat");

            ObjectInputStream mesinPembangkit =
                    new ObjectInputStream(fileMasuk);

            daftarPesanan =
                    (ArrayList<AyamGeprek>) mesinPembangkit.readObject();

            dataPelanggan =
                    (HashMap<String, Pelanggan>) mesinPembangkit.readObject();

            mesinPembangkit.close();

            System.out.println("\nData pesanan berhasil dimuat!");
            System.out.println("Data pelanggan berhasil dimuat!");

        } catch (FileNotFoundException e) {
            System.out.println("File data belum tersedia.");

        } catch (IOException e) {
            System.out.println("Gagal membaca data: " + e.getMessage());

        } catch (ClassNotFoundException e) {
            System.out.println("Class tidak ditemukan: " + e.getMessage());
        }
    }

    // =========================
    // MEMBACA LOG TRANSAKSI
    // =========================

    public void bacaLogTransaksi() {

        try {
            File fileLog = new File("log_transaksi.txt");
            Scanner mesinBaca = new Scanner(fileLog);

            System.out.println("\n=== RIWAYAT TRANSAKSI ===");

            while (mesinBaca.hasNextLine()) {
                String barisTeks = mesinBaca.nextLine();
                System.out.println(barisTeks);
            }

            mesinBaca.close();

        } catch (FileNotFoundException e) {
            System.out.println("File log transaksi belum ditemukan.");
        }
    }

    // =========================
    // DATA AWAL
    // =========================

    public void isiDataAwal() {

        tambahPesanan(
                new GeprekBiasa("Geprek Original", 15000, "Sedang"));

        tambahPesanan(
                new PaketGeprek("Paket Hemat", 20000, "Nasi dan Es Teh"));

        tambahPesanan(
                new GeprekKeju("Geprek Keju", 22000, "Mozzarella"));

        tambahPesanan(
                new GeprekBiasa("Geprek Pedas", 17000, "Pedas"));

        tambahPesanan(
                new PaketGeprek("Paket Komplit", 25000, "Nasi dan Minuman"));

        tambahPelanggan(new Pelanggan("P001", "Budi"));
        tambahPelanggan(new Pelanggan("P002", "Siti"));
        tambahPelanggan(new Pelanggan("P003", "Andi"));
    }

    // =========================
    // MENU PROGRAM
    // =========================

    public void tampilkanMenu() {

        System.out.println("\n==================================");
        System.out.println("   SISTEM PENJUALAN AYAM GEPREK");
        System.out.println("==================================");
        System.out.println("1. Tambah Pesanan");
        System.out.println("2. Tampilkan Daftar Pesanan");
        System.out.println("3. Cari Pesanan");
        System.out.println("4. Tampilkan Daftar Pelanggan");
        System.out.println("5. Tambah Pelanggan");
        System.out.println("6. Tampilkan Informasi Dapur");
        System.out.println("7. Proses Transaksi Penjualan");
        System.out.println("8. Simpan Data (Serialization)");
        System.out.println("9. Muat Data (Deserialization)");
        System.out.println("10. Lihat Log Transaksi");
        System.out.println("11. Keluar");
        System.out.println("==================================");
        System.out.print("Pilih menu: ");
    }

    public void jalankanMenu() {

        int pilihan = 0;
        boolean berjalan = true;

        while (berjalan) {

            try {
                tampilkanMenu();

                pilihan = input.nextInt();
                input.nextLine();

                switch (pilihan) {

                    case 1:
                        tambahPesananDariMenu();
                        break;

                    case 2:
                        tampilkanPesanan();
                        break;

                    case 3:
                        menuCariPesanan();
                        break;

                    case 4:
                        tampilkanPelanggan();
                        break;

                    case 5:
                        tambahPelangganDariMenu();
                        break;

                    case 6:
                        tampilkanDapur();
                        break;

                    case 7:
                        menuTransaksi();
                        break;

                    case 8:
                        simpanDataKeFile();
                        break;

                    case 9:
                        muatDataDariFile();
                        break;

                    case 10:
                        bacaLogTransaksi();
                        break;

                    case 11:
                        simpanDataKeFile();
                        System.out.println("Terima kasih!");
                        berjalan = false;
                        break;

                    default:
                        System.out.println("Pilihan menu tidak tersedia.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                input.nextLine();

            } catch (PesananTidakDitemukanException e) {
                System.out.println("Peringatan: " + e.getMessage());

            } catch (IllegalArgumentException e) {
                System.out.println("Input tidak valid: " + e.getMessage());

            } finally {
                System.out.println("\nBlok finally: Proses menu telah selesai.");
            }
        }
    }

    // =========================
    // MENU TAMBAH PESANAN
    // =========================

    public void tambahPesananDariMenu() {

        System.out.println("\n=== TAMBAH PESANAN ===");
        System.out.println("1. Geprek Biasa");
        System.out.println("2. Paket Geprek");
        System.out.println("3. Geprek Keju");
        System.out.print("Pilih jenis: ");

        int jenis = input.nextInt();
        input.nextLine();

        System.out.print("Nama pesanan: ");
        String nama = input.nextLine();

        System.out.print("Harga: ");
        double harga = input.nextDouble();
        input.nextLine();

        if (harga <= 0) {
            throw new IllegalArgumentException("Harga harus lebih dari 0.");
        }

        switch (jenis) {

            case 1:
                System.out.print("Level pedas: ");
                String level = input.nextLine();

                tambahPesanan(new GeprekBiasa(nama, harga, level));
                break;

            case 2:
                System.out.print("Isi paket: ");
                String isi = input.nextLine();

                tambahPesanan(new PaketGeprek(nama, harga, isi));
                break;

            case 3:
                System.out.print("Jenis keju: ");
                String keju = input.nextLine();

                tambahPesanan(new GeprekKeju(nama, harga, keju));
                break;

            default:
                System.out.println("Jenis pesanan tidak tersedia.");
        }
    }

    // =========================
    // MENU CARI PESANAN
    // =========================

    public void menuCariPesanan()
            throws PesananTidakDitemukanException {

        System.out.println("\n=== CARI PESANAN ===");
        System.out.println("1. Cari berdasarkan nama");
        System.out.println("2. Cari berdasarkan harga");
        System.out.print("Pilih: ");

        int jenis = input.nextInt();
        input.nextLine();

        AyamGeprek hasil;

        if (jenis == 1) {
            System.out.print("Masukkan nama pesanan: ");
            String nama = input.nextLine();

            hasil = cariPesanan(nama);

        } else if (jenis == 2) {
            System.out.print("Masukkan harga: ");
            double harga = input.nextDouble();
            input.nextLine();

            hasil = cariPesanan(harga);

        } else {
            throw new IllegalArgumentException("Pilihan pencarian salah.");
        }

        System.out.println("\nPesanan ditemukan!");
        hasil.tampilkanInfo();
        hasil.deskripsiKhusus();
    }

    // =========================
    // MENU TAMBAH PELANGGAN
    // =========================

    public void tambahPelangganDariMenu() {

        System.out.println("\n=== TAMBAH PELANGGAN ===");

        System.out.print("ID Pelanggan: ");
        String id = input.nextLine();

        System.out.print("Nama Pelanggan: ");
        String nama = input.nextLine();

        tambahPelanggan(new Pelanggan(id, nama));
    }

    // =========================
    // ASSOCIATION: TRANSAKSI
    // =========================

    public void menuTransaksi()
            throws PesananTidakDitemukanException {

        System.out.println("\n=== TRANSAKSI PENJUALAN ===");

        System.out.print("Masukkan ID pelanggan: ");
        String id = input.nextLine();

        Pelanggan pelanggan = cariPelanggan(id);

        if (pelanggan == null) {
            System.out.println("Pelanggan tidak ditemukan.");
            return;
        }

        tampilkanPesanan();

        System.out.print("\nMasukkan nomor pesanan: ");
        int nomor = input.nextInt();
        input.nextLine();

        if (nomor < 1 || nomor > daftarPesanan.size()) {
            throw new IllegalArgumentException(
                    "Nomor pesanan tidak tersedia.");
        }

        AyamGeprek pesanan = daftarPesanan.get(nomor - 1);

        prosesPesanan(pesanan);

        TransaksiPenjualan transaksi = new TransaksiPenjualan();

        transaksi.prosesTransaksi(pelanggan, pesanan);
    }

    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        SistemAyamGeprek sistem = new SistemAyamGeprek();

        sistem.muatDataDariFile();

        if (sistem.daftarPesanan.isEmpty()
                && sistem.dataPelanggan.isEmpty()) {

            System.out.println("\nMemasukkan data awal...");
            sistem.isiDataAwal();
        }

        sistem.jalankanMenu();
    }
}
