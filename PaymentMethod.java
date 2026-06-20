// ════════════════════════════════════════════════════════════════════════════════════
// 📌 FILE: PaymentMethod.java
// 📝 FUNGSI: Menyimpan data metode pembayaran yang tersedia untuk topup
// 🔧 DAPAT DIUBAH: Tambah metode baru, ubah biaya admin, ubah diskon
// ════════════════════════════════════════════════════════════════════════════════════

// 📦 Package: model - tempat menyimpan class-model/data
package model;

// 📦 Mengimport library yang dibutuhkan
import java.util.HashMap;   // 📌 Map untuk menyimpan data metode pembayaran
import java.util.Map;       // 📌 Interface Map (key-value pair)

// ════════════════════════════════════════════════════════════════════════════════════
// 📌 CLASS: PaymentMethod
// 📝 FUNGSI: Blueprint untuk objek metode pembayaran
// 🔧 DAPAT DIUBAH: Menambah atribut baru (misal: minimal topup, maksimal topup, dll)
// ════════════════════════════════════════════════════════════════════════════════════
public class PaymentMethod {
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 ATRIBUT / PROPERTIES METODE PEMBAYARAN
    // ──────────────────────────────────────────────────────────────────────────────
    
    // 📌 kodeMetode: ID unik untuk setiap metode pembayaran (contoh: "DANA")
    private String kodeMetode;
    
    // 📌 namaMetode: Nama metode pembayaran yang ditampilkan ke user (contoh: "Dana")
    private String namaMetode;
    
    // 📌 biayaAdmin: Biaya admin yang ditambahkan ke total pembayaran (dalam Rupiah)
    // 💡 Contoh: Dana = Rp 2.500
    private int biayaAdmin;
    
    // 📌 diskonPersen: Diskon dalam persen (0 = tidak ada diskon)
    // 💡 Contoh: ShopeePay = 5% diskon
    private double diskonPersen;
    
    // 📌 bankName: Nama bank (hanya untuk BANK TRANSFER, null untuk E-WALLET)
    private String bankName;
    
    // 📌 vaNumber: Nomor Virtual Account (hanya untuk BANK TRANSFER)
    private String vaNumber;
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 DATABASE METODE PEMBAYARAN (Static)
    // 📝 FUNGSI: Menyimpan semua metode pembayaran dalam bentuk HashMap
    // 🔧 DAPAT DIUBAH: HashMap → LinkedHashMap jika ingin menjaga urutan
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 KEY: Kode metode (String) → VALUE: Objek PaymentMethod
    // 📌 Static: Data ini hanya dibuat SEKALI dan SHARED oleh semua instance
    private static Map<String, PaymentMethod> daftarMetode = new HashMap<>();
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 STATIC BLOCK - INISIALISASI DATA METODE PEMBAYARAN
    // 📝 FUNGSI: Dijalankan OTOMATIS saat class PaymentMethod pertama kali di-load
    // 🔧 DAPAT DIUBAH: Tambah metode baru, ubah biaya admin, ubah diskon
    // ════════════════════════════════════════════════════════════════════════════════
    static {
        
        // ═══════════════════════════════════════════════════════════════════════════
        // 💳 E-WALLET (Dompet Digital)
        // 📝 Karakteristik: Biaya admin kecil, proses cepat
        // ═══════════════════════════════════════════════════════════════════════════
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 DANA
        // 💰 Biaya Admin: Rp 2.500 | 🔥 Diskon: 0%
        // ────────────────────────────────────────────────────────────────────────────
        daftarMetode.put("DANA", new PaymentMethod("DANA", "Dana", 2500, 0, null, null));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 GOPAY
        // 💰 Biaya Admin: Rp 2.000 | 🔥 Diskon: 0%
        // ────────────────────────────────────────────────────────────────────────────
        daftarMetode.put("GOPAY", new PaymentMethod("GOPAY", "GoPay", 2000, 0, null, null));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 OVO
        // 💰 Biaya Admin: Rp 1.500 | 🔥 Diskon: 0%
        // ────────────────────────────────────────────────────────────────────────────
        daftarMetode.put("OVO", new PaymentMethod("OVO", "Ovo", 1500, 0, null, null));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 SHOPEEPAY
        // 💰 Biaya Admin: Rp 2.000 | 🔥 Diskon: 5%
        // ⚠️ PERHATIAN: ShopeePay memiliki diskon 5%!
        // ────────────────────────────────────────────────────────────────────────────
        daftarMetode.put("SHOPEEPAY", new PaymentMethod("SHOPEEPAY", "ShopeePay", 2000, 5, null, null));
        
        // ────────────────────────────────────────────────────────────────────────────
        // ➕ [TAMBAH] - Cara menambahkan metode pembayaran baru:
        // ────────────────────────────────────────────────────────────────────────────
        // daftarMetode.put("LINKAJA", new PaymentMethod("LINKAJA", "LinkAja", 2000, 0, null, null));
        // daftarMetode.put("GOPAY", new PaymentMethod("GOPAY", "GoPay", 2000, 0, null, null));
        // ────────────────────────────────────────────────────────────────────────────
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 CONSTRUCTOR
    // 📝 FUNGSI: Membuat objek PaymentMethod baru
    // 🔧 DAPAT DIUBAH: Tambah parameter baru (misal: minimalTopup, maksimalTopup)
    // ════════════════════════════════════════════════════════════════════════════════
    public PaymentMethod(String kodeMetode, String namaMetode, int biayaAdmin, double diskonPersen, String bankName, String vaNumber) {
        this.kodeMetode = kodeMetode;          // 📌 Mengisi kode metode
        this.namaMetode = namaMetode;          // 📌 Mengisi nama metode
        this.biayaAdmin = biayaAdmin;          // 📌 Mengisi biaya admin
        this.diskonPersen = diskonPersen;      // 📌 Mengisi diskon dalam persen
        this.bankName = bankName;              // 📌 Mengisi nama bank (null untuk E-WALLET)
        this.vaNumber = vaNumber;              // 📌 Mengisi nomor VA (null untuk E-WALLET)
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 GETTER METHODS
    // 📝 FUNGSI: Mengambil nilai dari atribut private
    // 🔧 DAPAT DIUBAH: Tambah getter untuk atribut baru
    // ════════════════════════════════════════════════════════════════════════════════
    public String getKodeMetode() { return kodeMetode; }        // 📌 Mengambil kode metode
    public String getNamaMetode() { return namaMetode; }        // 📌 Mengambil nama metode
    public int getBiayaAdmin() { return biayaAdmin; }           // 📌 Mengambil biaya admin
    public double getDiskonPersen() { return diskonPersen; }    // 📌 Mengambil diskon (%)
    public String getBankName() { return bankName; }            // 📌 Mengambil nama bank
    public String getVaNumber() { return vaNumber; }            // 📌 Mengambil nomor VA
    public boolean isBank() { return bankName != null; }        // 📌 Cek apakah ini Bank Transfer
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 STATIC METHODS - MANAJEMEN DATA METODE PEMBAYARAN
    // ════════════════════════════════════════════════════════════════════════════════
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 getAllMetode()
    // 📝 FUNGSI: Mengambil semua metode pembayaran yang tersimpan
    // ──────────────────────────────────────────────────────────────────────────────
    public static Map<String, PaymentMethod> getAllMetode() {
        return daftarMetode;  // 📌 Mengembalikan semua metode dalam bentuk Map
    }
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 getMetodeByNama()
    // 📝 FUNGSI: Mencari metode pembayaran berdasarkan nama (case insensitive)
    // 🔧 DAPAT DIUBAH: Bisa ditambah validasi jika nama null
    // ──────────────────────────────────────────────────────────────────────────────
    public static PaymentMethod getMetodeByNama(String nama) {
        // 📌 Looping semua metode yang ada
        for (PaymentMethod pm : daftarMetode.values()) {
            // 📌 Membandingkan nama (ignore case)
            // 💡 Contoh: "dana" sama dengan "Dana"
            if (pm.getNamaMetode().equalsIgnoreCase(nama)) {
                return pm;  // 📌 Metode ditemukan, langsung return
            }
        }
        return null;  // 📌 Metode tidak ditemukan
    }
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 getDaftarNamaMetode()
    // 📝 FUNGSI: Mengambil semua nama metode dalam bentuk array String
    // ──────────────────────────────────────────────────────────────────────────────
    public static String[] getDaftarNamaMetode() {
        // 📌 Membuat array dengan ukuran sesuai jumlah metode
        String[] namaMetode = new String[daftarMetode.size()];
        int i = 0;
        // 📌 Looping semua metode dan mengambil namanya
        for (PaymentMethod pm : daftarMetode.values()) {
            namaMetode[i++] = pm.getNamaMetode();
        }
        return namaMetode;  // 📌 Mengembalikan array nama metode
    }
}