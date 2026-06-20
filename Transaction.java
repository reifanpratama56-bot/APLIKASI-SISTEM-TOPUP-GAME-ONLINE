// ════════════════════════════════════════════════════════════════════════════════════
// 📌 FILE: Transaction.java
// 📝 FUNGSI: Menyimpan data transaksi topup yang dilakukan oleh user
// 🔧 DAPAT DIUBAH: Format kode transaksi, format tanggal, status transaksi
// ════════════════════════════════════════════════════════════════════════════════════

// 📦 Package: model - tempat menyimpan class-model/data
package model;

// 📦 Mengimport library yang dibutuhkan
import java.text.SimpleDateFormat;   // 📌 Untuk memformat tanggal (dd/MM/yyyy HH:mm:ss)
import java.util.Date;               // 📌 Untuk mendapatkan waktu saat ini
import java.util.HashMap;            // 📌 Map untuk menyimpan data transaksi
import java.util.Map;                // 📌 Interface Map (key-value pair)
import java.util.UUID;               // 📌 Untuk generate ID unik (random 4 karakter)

// ════════════════════════════════════════════════════════════════════════════════════
// 📌 CLASS: Transaction
// 📝 FUNGSI: Blueprint untuk objek transaksi topup
// 🔧 DAPAT DIUBAH: Menambah atribut baru (misal: catatan, rating, dll)
// ════════════════════════════════════════════════════════════════════════════════════
public class Transaction {
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 ATRIBUT / PROPERTIES TRANSAKSI
    // ──────────────────────────────────────────────────────────────────────────────
    
    // 📌 kodeTransaksi: Kode unik transaksi (Primary Key)
    // 💡 Format: TRX + timestamp + 4 karakter random
    // 💡 Contoh: TRX1781837395843A7B
    private String kodeTransaksi;
    
    // 📌 gameName: Nama game yang di-topup
    private String gameName;
    
    // 📌 nominalItem: Nama nominal item yang dibeli (contoh: "86 Diamonds")
    private String nominalItem;
    
    // 📌 hargaAwal: Harga sebelum ditambah biaya admin dan diskon
    private int hargaAwal;
    
    // 📌 metodeBayar: Metode pembayaran yang digunakan (contoh: "Dana")
    private String metodeBayar;
    
    // 📌 biayaAdmin: Biaya admin yang dikenakan (dalam Rupiah)
    private int biayaAdmin;
    
    // 📌 diskonPersen: Diskon dalam persen (contoh: 5 = 5%)
    private double diskonPersen;
    
    // 📌 hargaFinal: Total harga yang harus dibayar (hargaAwal + biayaAdmin - diskon)
    private int hargaFinal;
    
    // 📌 userId: ID Game user (contoh: "1234567890")
    private String userId;
    
    // 📌 email: Email user untuk konfirmasi
    private String email;
    
    // 📌 tanggal: Tanggal dan waktu transaksi dibuat
    // 💡 Format: dd/MM/yyyy HH:mm:ss (contoh: 19/06/2026 10:30:45)
    private String tanggal;
    
    // 📌 status: Status transaksi
    // 💡 Nilai: WAITING_PAYMENT, PROCESSING, PAYMENT_SUCCESS, PAYMENT_FAILED
    private String status;
    
    // 📌 vaNumber: Nomor Virtual Account (untuk pembayaran)
    // 💡 Format: 8810 + timestamp (contoh: 88101781837395)
    private String vaNumber;
    
    // 📌 username: Username user yang melakukan transaksi (untuk filtering)
    private String username;
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 DATABASE TRANSAKSI (Static)
    // 📝 FUNGSI: Menyimpan semua data transaksi dalam bentuk HashMap
    // 🔧 DAPAT DIUBAH: HashMap → LinkedHashMap jika ingin menjaga urutan
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 KEY: Kode transaksi (String) → VALUE: Objek Transaction
    // 📌 Static: Data ini hanya dibuat SEKALI dan SHARED oleh semua instance
    private static Map<String, Transaction> dataTransaksi = new HashMap<>();
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 CONSTRUCTOR 1 (Tanpa username)
    // 📝 FUNGSI: Membuat objek Transaction baru (akan memanggil constructor 2)
    // 🔧 DAPAT DIUBAH: Tambah parameter baru
    // ════════════════════════════════════════════════════════════════════════════════
    public Transaction(String gameName, String nominalItem, int hargaAwal, 
                       String metodeBayar, int biayaAdmin, double diskonPersen, 
                       int hargaFinal, String userId, String email) {
        // 📌 Memanggil constructor dengan username = null
        this(gameName, nominalItem, hargaAwal, metodeBayar, biayaAdmin, diskonPersen, 
             hargaFinal, userId, email, null);
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 CONSTRUCTOR 2 (Dengan username)
    // 📝 FUNGSI: Membuat objek Transaction baru dengan semua data
    // 🔧 DAPAT DIUBAH: Tambah parameter baru
    // ════════════════════════════════════════════════════════════════════════════════
    public Transaction(String gameName, String nominalItem, int hargaAwal, 
                       String metodeBayar, int biayaAdmin, double diskonPersen, 
                       int hargaFinal, String userId, String email, String username) {
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 GENERATE KODE TRANSAKSI (UNIK)
        // 💡 Format: TRX + timestamp + 4 karakter random
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 System.currentTimeMillis() → Waktu saat ini dalam milidetik
        // 📌 UUID.randomUUID() → Generate ID unik
        // 📌 substring(0, 4) → Ambil 4 karakter pertama
        // 📌 toUpperCase() → Ubah ke huruf kapital
        // 💡 Contoh: TRX1781837395843A7B
        // ────────────────────────────────────────────────────────────────────────────
        this.kodeTransaksi = "TRX" + System.currentTimeMillis() + 
                             UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        
        // 📌 Mengisi semua atribut
        this.gameName = gameName;                      // 📌 Nama game
        this.nominalItem = nominalItem;                // 📌 Nominal item
        this.hargaAwal = hargaAwal;                    // 📌 Harga awal
        this.metodeBayar = metodeBayar;                // 📌 Metode pembayaran
        this.biayaAdmin = biayaAdmin;                  // 📌 Biaya admin
        this.diskonPersen = diskonPersen;              // 📌 Diskon (%)
        this.hargaFinal = hargaFinal;                  // 📌 Harga final
        this.userId = userId;                          // 📌 ID Game
        this.email = email;                            // 📌 Email
        this.username = username;                      // 📌 Username user
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 FORMAT TANGGAL
        // 💡 Format: dd/MM/yyyy HH:mm:ss (contoh: 19/06/2026 10:30:45)
        // ────────────────────────────────────────────────────────────────────────────
        this.tanggal = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 STATUS AWAL
        // 💡 WAITING_PAYMENT = Menunggu pembayaran
        // ────────────────────────────────────────────────────────────────────────────
        this.status = "WAITING_PAYMENT";
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 GENERATE VIRTUAL ACCOUNT NUMBER
        // 💡 Format: 8810 + timestamp
        // ────────────────────────────────────────────────────────────────────────────
        this.vaNumber = generateVANumber();
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: generateVANumber()
    // 📝 FUNGSI: Menghasilkan nomor Virtual Account (VA) unik
    // 🔧 DAPAT DIUBAH: Prefix VA (8810 → bisa diubah)
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 VA Number terdiri dari:
    // - Prefix: 4 digit (8810)
    // - Timestamp: 10 digit (System.currentTimeMillis() % 10000000000L)
    // 💡 Contoh: 88101781837395
    // ════════════════════════════════════════════════════════════════════════════════
    private String generateVANumber() {
        // 📌 Prefix "8810" + timestamp (10 digit)
        return "8810" + System.currentTimeMillis() % 10000000000L;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 SETTER STATUS
    // 📝 FUNGSI: Mengubah status transaksi
    // 🔧 DAPAT DIUBAH: Tambah status baru (misal: REFUNDED, CANCELLED)
    // ════════════════════════════════════════════════════════════════════════════════
    
    // 📌 setStatusLunas() → Ubah status menjadi PAYMENT_SUCCESS (Pembayaran sukses)
    public void setStatusLunas() { 
        this.status = "PAYMENT_SUCCESS"; 
    }
    
    // 📌 setStatusGagal() → Ubah status menjadi PAYMENT_FAILED (Pembayaran gagal)
    public void setStatusGagal() { 
        this.status = "PAYMENT_FAILED"; 
    }
    
    // 📌 setStatusDiproses() → Ubah status menjadi PROCESSING (Sedang diproses)
    public void setStatusDiproses() { 
        this.status = "PROCESSING"; 
    }
    
    // 📌 setStatusWaiting() → Ubah status menjadi WAITING_PAYMENT (Menunggu bayar)
    public void setStatusWaiting() { 
        this.status = "WAITING_PAYMENT"; 
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 GETTER METHODS
    // 📝 FUNGSI: Mengambil nilai dari atribut private
    // 🔧 DAPAT DIUBAH: Tambah getter untuk atribut baru
    // ════════════════════════════════════════════════════════════════════════════════
    public String getKodeTransaksi() { return kodeTransaksi; }  // 📌 Kode transaksi
    public String getGameName() { return gameName; }            // 📌 Nama game
    public String getNominalItem() { return nominalItem; }      // 📌 Nominal item
    public int getHargaAwal() { return hargaAwal; }             // 📌 Harga awal
    public String getMetodeBayar() { return metodeBayar; }      // 📌 Metode bayar
    public int getBiayaAdmin() { return biayaAdmin; }           // 📌 Biaya admin
    public double getDiskonPersen() { return diskonPersen; }    // 📌 Diskon (%)
    public int getHargaFinal() { return hargaFinal; }           // 📌 Harga final
    public String getUserId() { return userId; }                // 📌 ID Game
    public String getEmail() { return email; }                  // 📌 Email
    public String getTanggal() { return tanggal; }              // 📌 Tanggal
    public String getStatus() { return status; }                // 📌 Status
    public String getVaNumber() { return vaNumber; }            // 📌 Nomor VA
    public String getUsername() { return username; }            // 📌 Username
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 STATIC METHODS - MANAJEMEN DATA TRANSAKSI
    // ════════════════════════════════════════════════════════════════════════════════
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 simpanTransaksi()
    // 📝 FUNGSI: Menyimpan transaksi ke database
    // ──────────────────────────────────────────────────────────────────────────────
    public static void simpanTransaksi(Transaction transaksi) {
        // 📌 Menyimpan dengan key = kode transaksi
        dataTransaksi.put(transaksi.getKodeTransaksi(), transaksi);
    }
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 updateStatusTransaksi()
    // 📝 FUNGSI: Mengupdate status transaksi berdasarkan kode transaksi
    // 🔧 DAPAT DIUBAH: Tambah status baru (REFUNDED, CANCELLED)
    // ──────────────────────────────────────────────────────────────────────────────
    public static void updateStatusTransaksi(String kodeTransaksi, String statusBaru) {
        // 📌 Mencari transaksi berdasarkan kode
        Transaction transaksi = dataTransaksi.get(kodeTransaksi);
        
        if (transaksi != null) {
            // 📌 Mengubah status sesuai parameter
            if (statusBaru.equals("PAYMENT_SUCCESS")) {
                transaksi.setStatusLunas();
            } else if (statusBaru.equals("PAYMENT_FAILED")) {
                transaksi.setStatusGagal();
            } else if (statusBaru.equals("PROCESSING")) {
                transaksi.setStatusDiproses();
            }
            // 📌 Cetak log ke console (untuk debugging)
            System.out.println("Status transaksi " + kodeTransaksi + " diupdate menjadi: " + statusBaru);
        }
    }
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 getAllTransaksi()
    // 📝 FUNGSI: Mengambil semua data transaksi
    // ──────────────────────────────────────────────────────────────────────────────
    public static Map<String, Transaction> getAllTransaksi() { 
        return dataTransaksi; 
    }
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 getTransaksiByUsername()
    // 📝 FUNGSI: Mengambil semua transaksi milik user tertentu
    // ──────────────────────────────────────────────────────────────────────────────
    public static Map<String, Transaction> getTransaksiByUsername(String username) {
        // 📌 Membuat Map baru untuk hasil
        Map<String, Transaction> result = new HashMap<>();
        
        // 📌 Looping semua transaksi
        for (Map.Entry<String, Transaction> entry : dataTransaksi.entrySet()) {
            // 📌 Jika username cocok, tambahkan ke result
            if (entry.getValue().getUsername() != null && 
                entry.getValue().getUsername().equals(username)) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }
}