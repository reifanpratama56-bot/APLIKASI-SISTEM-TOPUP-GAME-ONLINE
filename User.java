// ════════════════════════════════════════════════════════════════════════════════════
// 📌 FILE: User.java
// 📝 FUNGSI: Menyimpan data user dan mengelola sistem login, registrasi, & poin
// 🔧 DAPAT DIUBAH: Data user default, sistem poin, role user
// ════════════════════════════════════════════════════════════════════════════════════

// 📦 Package: model - tempat menyimpan class-model/data
package model;

// 📦 Mengimport library yang dibutuhkan
import java.util.HashMap;   // 📌 Map untuk menyimpan data user
import java.util.Map;       // 📌 Interface Map (key-value pair)

// ════════════════════════════════════════════════════════════════════════════════════
// 📌 CLASS: User
// 📝 FUNGSI: Blueprint untuk objek User yang menyimpan data pengguna
// 🔧 DAPAT DIUBAH: Menambah atribut baru (misal: alamat, tanggal lahir, foto profil)
// ════════════════════════════════════════════════════════════════════════════════════
public class User {
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 ATRIBUT / PROPERTIES USER
    // ──────────────────────────────────────────────────────────────────────────────
    
    // 📌 username: Nama pengguna (Unique / Primary Key)
    // 💡 Contoh: "reifan", "linda", "admin"
    private String username;
    
    // 📌 email: Alamat email pengguna
    // 💡 Contoh: "reifan@gmail.com"
    private String email;
    
    // 📌 password: Kata sandi pengguna (plain text - untuk demo)
    // ⚠️ PERHATIAN: Dalam aplikasi nyata, password harus di-hash!
    private String password;
    
    // 📌 noHp: Nomor handphone pengguna
    // 💡 Contoh: "081234567890"
    private String noHp;
    
    // 📌 role: Peran pengguna (member / admin)
    // 💡 Contoh: "member", "admin"
    private String role;
    
    // 📌 poin: Poin loyalty yang dimiliki user
    // 💡 Setiap Rp 10.000 topup = 1 poin
    private int poin;
    
    // 📌 totalTopup: Total uang yang sudah di-topup (dalam Rupiah)
    private int totalTopup;
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 DATABASE USER (Static)
    // 📝 FUNGSI: Menyimpan semua data user dalam bentuk HashMap
    // 🔧 DAPAT DIUBAH: HashMap → LinkedHashMap jika ingin menjaga urutan
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 KEY: Username (String) → VALUE: Objek User
    // 📌 Static: Data ini hanya dibuat SEKALI dan SHARED oleh semua instance
    private static Map<String, User> dataUser = new HashMap<>();
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 STATIC BLOCK - INISIALISASI DATA USER DEFAULT (AKUN DEMO)
    // 📝 FUNGSI: Dijalankan OTOMATIS saat class User pertama kali di-load
    // 🔧 DAPAT DIUBAH: Tambah user baru, ubah data user
    // ════════════════════════════════════════════════════════════════════════════════
    static {
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 AKUN DEMO 1: reifan
        // 👤 Username: reifan | 🔑 Password: reifan123
        // ⭐ Poin: 250 | 💰 Total Topup: Rp 250.000
        // ────────────────────────────────────────────────────────────────────────────
        dataUser.put("reifan", new User("reifan", "reifan@gmail.com", "reifan123", 
                                        "081234567890", "reifan", 0, 0));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 AKUN DEMO 2: linda
        // 👤 Username: linda | 🔑 Password: linda123
        // ⭐ Poin: 1000 | 💰 Total Topup: Rp 500.000
        // ────────────────────────────────────────────────────────────────────────────
        dataUser.put("linda", new User("linda", "linda@gmail.com", "linda123", 
                                       "081234567890", "linda", 0, 0));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 AKUN DEMO 3: fina
        // 👤 Username: fina | 🔑 Password: fina123
        // ⭐ Poin: 50 | 💰 Total Topup: Rp 50.000
        // ────────────────────────────────────────────────────────────────────────────
        dataUser.put("fina", new User("fina", "fina@gmail.com", "fina123", 
                                      "081234567890", "fina", 0, 0));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 AKUN DEMO 4: andi
        // 👤 Username: andi | 🔑 Password: andi123
        // ⭐ Poin: 50 | 💰 Total Topup: Rp 50.000
        // ────────────────────────────────────────────────────────────────────────────
        dataUser.put("andi", new User("andi", "andi@gmail.com", "andi123", 
                                      "081234567890", "andi", 0, 0));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 AKUN DEMO 5: arly
        // 👤 Username: arly | 🔑 Password: arly123
        // ⭐ Poin: 50 | 💰 Total Topup: Rp 50.000
        // ────────────────────────────────────────────────────────────────────────────
        dataUser.put("arly", new User("arly", "arly@gmail.com", "arly123", 
                                      "081234567890", "arly", 0, 0));
        
        
        // ────────────────────────────────────────────────────────────────────────────
        // ➕ [TAMBAH] - Cara menambahkan user baru:
        // ────────────────────────────────────────────────────────────────────────────
        //dataUser.put("usernameBaru", new User("usernameBaru", "email@email.com", 
        //                                       "password123", "081234567890", 
        //                                       "member", 0, 0));
        // ────────────────────────────────────────────────────────────────────────────
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 CONSTRUCTOR 1 (Tanpa totalTopup)
    // 📝 FUNGSI: Membuat objek User baru (memanggil constructor 2 dengan totalTopup = 0)
    // 🔧 DAPAT DIUBAH: Tambah parameter baru
    // ════════════════════════════════════════════════════════════════════════════════
    public User(String username, String email, String password, String noHp, String role, int poin) {
        // 📌 Memanggil constructor dengan totalTopup = 0
        this(username, email, password, noHp, role, poin, 0);
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 CONSTRUCTOR 2 (Dengan totalTopup)
    // 📝 FUNGSI: Membuat objek User baru dengan semua data
    // 🔧 DAPAT DIUBAH: Tambah parameter baru
    // ════════════════════════════════════════════════════════════════════════════════
    public User(String username, String email, String password, String noHp, String role, int poin, int totalTopup) {
        this.username = username;          // 📌 Mengisi username
        this.email = email;                // 📌 Mengisi email
        this.password = password;          // 📌 Mengisi password
        this.noHp = noHp;                  // 📌 Mengisi nomor HP
        this.role = role;                  // 📌 Mengisi role (member/admin)
        this.poin = poin;                  // 📌 Mengisi poin
        this.totalTopup = totalTopup;      // 📌 Mengisi total topup
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 GETTER METHODS
    // 📝 FUNGSI: Mengambil nilai dari atribut private
    // 🔧 DAPAT DIUBAH: Tambah getter untuk atribut baru
    // ════════════════════════════════════════════════════════════════════════════════
    public String getUsername() { return username; }      // 📌 Mengambil username
    public String getEmail() { return email; }            // 📌 Mengambil email
    public String getPassword() { return password; }      // 📌 Mengambil password
    public String getNoHp() { return noHp; }              // 📌 Mengambil nomor HP
    public String getRole() { return role; }              // 📌 Mengambil role
    public int getPoin() { return poin; }                 // 📌 Mengambil poin
    public int getTotalTopup() { return totalTopup; }     // 📌 Mengambil total topup
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 SETTER METHODS
    // 📝 FUNGSI: Mengubah nilai dari atribut private
    // 🔧 DAPAT DIUBAH: Tambah setter untuk atribut baru
    // ════════════════════════════════════════════════════════════════════════════════
    
    // 📌 setPoin() → Mengganti nilai poin
    public void setPoin(int poin) { 
        this.poin = poin; 
    }
    
    // 📌 tambahPoin() → Menambahkan poin (tidak mengganti, tapi menambah)
    public void tambahPoin(int poin) { 
        this.poin += poin; 
    }
    
    // 📌 tambahTotalTopup() → Menambahkan total topup
    public void tambahTotalTopup(int amount) { 
        this.totalTopup += amount; 
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 STATIC METHODS - MANAJEMEN USER
    // ════════════════════════════════════════════════════════════════════════════════
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 login()
    // 📝 FUNGSI: Melakukan proses login user
    // 🔧 DAPAT DIUBAH: Bisa ditambah validasi (misal: akun terblokir)
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 Parameter: username, password
    // 📌 Return: Objek User jika login berhasil, null jika gagal
    // ──────────────────────────────────────────────────────────────────────────────
    public static User login(String username, String password) {
        // 📌 Mencari user berdasarkan username
        User user = dataUser.get(username);
        
        // 📌 Jika user ditemukan DAN password cocok
        if (user != null && user.getPassword().equals(password)) {
            return user;  // ✅ Login berhasil
        }
        return null;      // ❌ Login gagal
    }
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 register()
    // 📝 FUNGSI: Mendaftarkan user baru ke database
    // ──────────────────────────────────────────────────────────────────────────────
    public static void register(User user) {
        // 📌 Menyimpan user dengan key = username
        dataUser.put(user.getUsername(), user);
    }
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 isUsernameExist()
    // 📝 FUNGSI: Mengecek apakah username sudah terdaftar
    // ──────────────────────────────────────────────────────────────────────────────
    public static boolean isUsernameExist(String username) {
        // 📌 Mengembalikan true jika username ada di database
        return dataUser.containsKey(username);
    }
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 getAllUsers()
    // 📝 FUNGSI: Mengambil semua data user
    // ──────────────────────────────────────────────────────────────────────────────
    public static Map<String, User> getAllUsers() { 
        return dataUser; 
    }
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 getUserByUsername()
    // 📝 FUNGSI: Mencari user berdasarkan username
    // ──────────────────────────────────────────────────────────────────────────────
    public static User getUserByUsername(String username) {
        // 📌 Mengambil user dari Map berdasarkan username
        return dataUser.get(username);
    }
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 updateUserAfterTopup()
    // 📝 FUNGSI: Mengupdate poin dan total topup setelah user melakukan transaksi
    // 🔧 DAPAT DIUBAH: Ubah angka 10000 untuk mengubah rate poin
    // ──────────────────────────────────────────────────────────────────────────────
    // 💡 Sistem Poin: Setiap Rp 10.000 = 1 poin
    // ──────────────────────────────────────────────────────────────────────────────
    public static void updateUserAfterTopup(String username, int amount) {
        // 📌 Mencari user berdasarkan username
        User user = dataUser.get(username);
        
        if (user != null) {
            // 📌 Menghitung poin yang didapat (Rp 10.000 = 1 poin)
            // 🔧 GANTI: Ubah 10000 untuk mengubah rate poin
            int poinEarned = amount / 10000;   // 📌 Contoh: Rp 52.000 = 5 poin
            
            // 📌 Menambahkan poin ke user
            user.tambahPoin(poinEarned);
            
            // 📌 Menambahkan total topup
            user.tambahTotalTopup(amount);
            
            // 📌 Mencetak log ke console (untuk debugging)
            System.out.println("✅ User " + username + " mendapat " + poinEarned + 
                               " poin! Total poin: " + user.getPoin());
        }
    }
}