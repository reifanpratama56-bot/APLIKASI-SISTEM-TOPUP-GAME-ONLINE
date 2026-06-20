// ════════════════════════════════════════════════════════════════════════════════════
// 📌 FILE: ImageLoader.java
// 📝 FUNGSI: Utility class untuk memuat gambar dari folder resources
// 🔧 DAPAT DIUBAH: Ukuran gambar, warna placeholder, path folder
// ════════════════════════════════════════════════════════════════════════════════════

// 📦 Package: utils - tempat menyimpan class-class utility
package utils;

// 📦 Mengimport library yang dibutuhkan
import javax.swing.*;           // 📌 Untuk ImageIcon (menampilkan gambar di Swing)
import java.awt.*;              // 📌 Untuk Graphics2D, Color, Font, GradientPaint
import java.awt.image.BufferedImage;  // 📌 Untuk membuat gambar placeholder (jika gambar tidak ditemukan)
import java.net.URL;            // 📌 Untuk mengakses file dari resources

// ════════════════════════════════════════════════════════════════════════════════════
// 📌 CLASS: ImageLoader
// 📝 FUNGSI: Utility untuk memuat dan menskalakan gambar dari folder resources
// 🔧 DAPAT DIUBAH: Ukuran default, warna placeholder, path folder
// ════════════════════════════════════════════════════════════════════════════════════
public class ImageLoader {
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 METHOD: loadGameIcon()
    // 📝 FUNGSI: Memuat logo game dari folder resources/images/games/
    // 🔧 DAPAT DIUBAH: Ukuran gambar (width, height), warna placeholder
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 Parameter:
    //   - fileName: Nama file gambar (contoh: "mlbb.png")
    //   - width: Lebar gambar yang diinginkan (contoh: 55)
    //   - height: Tinggi gambar yang diinginkan (contoh: 55)
    // 📌 Return: ImageIcon (gambar yang sudah di-scale)
    // ──────────────────────────────────────────────────────────────────────────────
    public static ImageIcon loadGameIcon(String fileName, int width, int height) {
        try {
            // 📌 Mencari gambar di folder resources/images/games/
            // 📌 getResource() akan mencari file di dalam classpath
            // 📌 "/images/games/" + fileName → path relatif dari folder resources
            URL imgURL = ImageLoader.class.getResource("/images/games/" + fileName);
            
            // 📌 Jika gambar ditemukan (URL != null)
            if (imgURL != null) {
                // 📌 Membuat ImageIcon dari URL
                ImageIcon originalIcon = new ImageIcon(imgURL);
                
                // 📌 Menskalakan gambar ke ukuran yang diinginkan
                // 📌 Image.SCALE_SMOOTH → kualitas gambar lebih halus
                Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                
                // 📌 Mengembalikan ImageIcon yang sudah di-scale
                return new ImageIcon(scaledImage);
            } else {
                // 📌 Jika gambar tidak ditemukan, cetak log dan buat placeholder
                System.out.println("⚠️ Game image not found: " + fileName);
                return createPlaceholderIcon(fileName, width, height, new Color(99, 102, 241));
            }
        } catch (Exception e) {
            // 📌 Jika terjadi error (misal: file corrupt), cetak error dan buat placeholder
            System.out.println("❌ Error loading game: " + fileName);
            return createPlaceholderIcon(fileName, width, height, new Color(99, 102, 241));
        }
    }
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 METHOD: loadPaymentIcon()
    // 📝 FUNGSI: Memuat logo metode pembayaran dari folder resources/images/payments/
    // 🔧 DAPAT DIUBAH: Ukuran gambar (width, height), warna placeholder
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 Parameter:
    //   - fileName: Nama file gambar (contoh: "dana.png")
    //   - width: Lebar gambar yang diinginkan (contoh: 40)
    //   - height: Tinggi gambar yang diinginkan (contoh: 40)
    // 📌 Return: ImageIcon (gambar yang sudah di-scale)
    // ──────────────────────────────────────────────────────────────────────────────
    public static ImageIcon loadPaymentIcon(String fileName, int width, int height) {
        try {
            // 📌 Mencari gambar di folder resources/images/payments/
            URL imgURL = ImageLoader.class.getResource("/images/payments/" + fileName);
            
            // 📌 Jika gambar ditemukan (URL != null)
            if (imgURL != null) {
                // 📌 Membuat ImageIcon dari URL
                ImageIcon originalIcon = new ImageIcon(imgURL);
                
                // 📌 Menskalakan gambar ke ukuran yang diinginkan
                Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                
                // 📌 Cetak log sukses
                System.out.println("✅ Load payment: " + fileName);
                
                // 📌 Mengembalikan ImageIcon yang sudah di-scale
                return new ImageIcon(scaledImage);
            } else {
                // 📌 Jika gambar tidak ditemukan, cetak log dan buat placeholder
                System.out.println("⚠️ Payment image not found: " + fileName);
                return createPlaceholderIcon(fileName, width, height, new Color(16, 185, 129));
            }
        } catch (Exception e) {
            // 📌 Jika terjadi error, cetak error dan buat placeholder
            System.out.println("❌ Error loading payment: " + fileName);
            return createPlaceholderIcon(fileName, width, height, new Color(16, 185, 129));
        }
    }
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 METHOD: createPlaceholderIcon()
    // 📝 FUNGSI: Membuat gambar placeholder jika gambar asli tidak ditemukan
    // 🔧 DAPAT DIUBAH: Warna, font, bentuk placeholder
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 Parameter:
    //   - fileName: Nama file (digunakan untuk mengambil inisial)
    //   - width: Lebar placeholder
    //   - height: Tinggi placeholder
    //   - color: Warna dasar placeholder (biru untuk game, hijau untuk payment)
    // 📌 Return: ImageIcon (placeholder dengan inisial)
    // ──────────────────────────────────────────────────────────────────────────────
    private static ImageIcon createPlaceholderIcon(String fileName, int width, int height, Color color) {
        // 📌 Membuat BufferedImage (gambar kosong) dengan latar belakang transparan
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        // 📌 Membuat Graphics2D untuk menggambar
        Graphics2D g2d = image.createGraphics();
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 ANTI-ALIASING
        // 📝 FUNGSI: Membuat hasil gambar lebih halus (tidak bergerigi)
        // ────────────────────────────────────────────────────────────────────────────
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 BACKGROUND GRADIENT
        // 📝 FUNGSI: Membuat background dengan gradasi warna
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 Gradient dari warna utama ke warna yang lebih gelap
        GradientPaint gp = new GradientPaint(0, 0, color, width, height, color.darker());
        g2d.setPaint(gp);
        
        // 📌 Menggambar rounded rectangle (persegi panjang dengan sudut melengkung)
        // 📌 15 = radius sudut melengkung
        g2d.fillRoundRect(0, 0, width, height, 15, 15);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 TEKS INISIAL
        // 📝 FUNGSI: Menampilkan 2 huruf pertama dari nama file sebagai inisial
        // ────────────────────────────────────────────────────────────────────────────
        g2d.setColor(Color.WHITE);  // 📌 Warna teks putih
        
        // 📌 Menentukan ukuran font (1/3 dari lebar placeholder)
        g2d.setFont(new Font("Dialog", Font.BOLD, width / 3));
        
        // 📌 Mengambil 2 huruf pertama dari nama file (contoh: "mlbb.png" → "ml")
        String text = fileName.substring(0, Math.min(2, fileName.length())).toUpperCase();
        
        // 📌 Menghitung lebar dan tinggi teks untuk posisi center
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);    // 📌 Lebar teks
        int textHeight = fm.getAscent();          // 📌 Tinggi teks
        
        // 📌 Menggambar teks di tengah placeholder
        // 📌 (width - textWidth) / 2 → center horizontal
        // 📌 (height + textHeight) / 2 - 4 → center vertical (dikurangi 4 untuk penyesuaian)
        g2d.drawString(text, (width - textWidth) / 2, (height + textHeight) / 2 - 4);
        
        // 📌 Membersihkan resource Graphics2D
        g2d.dispose();
        
        // 📌 Mengembalikan ImageIcon dari BufferedImage yang sudah dibuat
        return new ImageIcon(image);
    }
}