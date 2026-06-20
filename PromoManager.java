// ════════════════════════════════════════════════════════════════════════════════════
// 📌 FILE: PromoManager.java
// 📝 FUNGSI: Mengelola kode promo dan diskon yang tersedia untuk user
// 🔧 DAPAT DIUBAH: Tambah promo baru, ubah diskon, ubah kode promo
// ════════════════════════════════════════════════════════════════════════════════════

package model;

import java.util.*;

// ════════════════════════════════════════════════════════════════════════════════════
// 📌 CLASS: PromoManager
// 📝 FUNGSI: Class utility untuk mengelola semua kode promo
// 🔧 DAPAT DIUBAH: Tambah promo baru, ubah diskon, ubah kode promo
// ════════════════════════════════════════════════════════════════════════════════════
public class PromoManager {
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 DATABASE PROMO (Static)
    // ──────────────────────────────────────────────────────────────────────────────
    private static List<Promo> activePromos = new ArrayList<>();
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 STATIC BLOCK - INISIALISASI DATA PROMO
    // ════════════════════════════════════════════════════════════════════════════════
    static {
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 PROMO 1: WEEKEND20
        // 📝 Diskon 20% semua game (percent)
        // 🔧 DAPAT DIUBAH: Ubah diskon (20) menjadi angka lain
        // 💡 Contoh: 20 = 20%, 30 = 30%, 50 = 50%
        // ────────────────────────────────────────────────────────────────────────────
        activePromos.add(new Promo("WEEKEND20", "Diskon 20% semua game", 20, "percent"));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 PROMO 2: FREEADMIN
        // 📝 Gratis biaya admin (free_admin)
        // 💡 value = 0 (tidak ada diskon persen)
        // ────────────────────────────────────────────────────────────────────────────
        activePromos.add(new Promo("FREEADMIN", "Gratis biaya admin", 0, "free_admin"));
        
        // ────────────────────────────────────────────────────────────────────────────
        // ➕ [TAMBAH] - Cara menambahkan promo baru:
        // ────────────────────────────────────────────────────────────────────────────
        // activePromos.add(new Promo("FLASH50", "Diskon 50% flash sale", 50, "percent"));
        // activePromos.add(new Promo("MERDEKA", "Diskon 45% Hari Kemerdekaan", 45, "percent"));
        // ────────────────────────────────────────────────────────────────────────────
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: getActivePromos()
    // 📝 FUNGSI: Mengambil semua daftar promo yang aktif
    // ════════════════════════════════════════════════════════════════════════════════
    public static List<Promo> getActivePromos() {
        return activePromos;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: applyPromo()
    // 📝 FUNGSI: Menerapkan promo ke total harga
    // ════════════════════════════════════════════════════════════════════════════════
    public static int applyPromo(String promoCode, int amount, int adminFee) {
        for (Promo p : activePromos) {
            if (p.code.equalsIgnoreCase(promoCode)) {
                switch (p.type) {
                    case "percent":
                        // 📌 Diskon persentase dari total harga
                        int diskon = amount * p.value / 100;
                        return amount - diskon;
                    case "free_admin":
                        // 📌 GRATIS BIAYA ADMIN - mengembalikan amount tanpa admin
                        return amount;
                    default:
                        return amount;
                }
            }
        }
        return amount;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: applyPromoWithAdmin()
    // 📝 FUNGSI: Menerapkan promo dengan mempertimbangkan biaya admin
    // ════════════════════════════════════════════════════════════════════════════════
    public static int applyPromoWithAdmin(String promoCode, int amount, int adminFee) {
        for (Promo p : activePromos) {
            if (p.code.equalsIgnoreCase(promoCode)) {
                switch (p.type) {
                    case "percent":
                        // 📌 Diskon persentase dari total (amount + adminFee)
                        int total = amount + adminFee;
                        int diskon = total * p.value / 100;
                        return total - diskon;
                        
                    case "free_admin":
                        // 📌 GRATIS BIAYA ADMIN - hanya bayar amount tanpa admin
                        return amount;
                        
                    default:
                        return amount + adminFee;
                }
            }
        }
        return amount + adminFee;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: isValidPromo()
    // 📝 FUNGSI: Mengecek apakah kode promo valid
    // ════════════════════════════════════════════════════════════════════════════════
    public static boolean isValidPromo(String promoCode) {
        for (Promo p : activePromos) {
            if (p.code.equalsIgnoreCase(promoCode)) {
                return true;
            }
        }
        return false;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: getPromoType()
    // 📝 FUNGSI: Mengambil tipe promo berdasarkan kode
    // ════════════════════════════════════════════════════════════════════════════════
    public static String getPromoType(String promoCode) {
        for (Promo p : activePromos) {
            if (p.code.equalsIgnoreCase(promoCode)) {
                return p.type;
            }
        }
        return null;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: getPromoDescription()
    // 📝 FUNGSI: Mengambil deskripsi/nama promo berdasarkan kode
    // ════════════════════════════════════════════════════════════════════════════════
    public static String getPromoDescription(String promoCode) {
        for (Promo p : activePromos) {
            if (p.code.equalsIgnoreCase(promoCode)) {
                return p.name;
            }
        }
        return null;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: getPromoValue()
    // 📝 FUNGSI: Mengambil nilai diskon promo berdasarkan kode
    // ════════════════════════════════════════════════════════════════════════════════
    public static int getPromoValue(String promoCode) {
        for (Promo p : activePromos) {
            if (p.code.equalsIgnoreCase(promoCode)) {
                return p.value;
            }
        }
        return 0;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: isFreeAdminPromo()
    // 📝 FUNGSI: Mengecek apakah promo adalah tipe gratis admin
    // ════════════════════════════════════════════════════════════════════════════════
    public static boolean isFreeAdminPromo(String promoCode) {
        for (Promo p : activePromos) {
            if (p.code.equalsIgnoreCase(promoCode) && p.type.equals("free_admin")) {
                return true;
            }
        }
        return false;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 INNER CLASS: Promo
    // ────────────────────────────────────────────────────────────────────────────────
    static class Promo {
        String code, name, type;
        int value;
        
        Promo(String code, String name, int value, String type) {
            this.code = code;
            this.name = name;
            this.value = value;
            this.type = type;
        }
    }
}