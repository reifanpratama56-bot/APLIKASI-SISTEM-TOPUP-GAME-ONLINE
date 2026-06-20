// ════════════════════════════════════════════════════════════════════════════════════
// 📌 FILE: FormDashboard.java
// 📝 FUNGSI: Halaman utama (Dashboard) yang muncul setelah user login
// 🔧 DAPAT DIUBAH: Warna tema, teks, promo, tombol navigasi
// ════════════════════════════════════════════════════════════════════════════════════

// 📦 Package: form - tempat menyimpan class GUI/Form
package form;

// 📦 Mengimport library yang dibutuhkan
import model.*;                  // 📌 Mengambil semua class dari package model
import javax.swing.*;            // 📌 Komponen GUI Swing
import javax.swing.border.*;     // 📌 Untuk membuat border/garis tepi
import java.awt.*;               // 📌 Untuk warna, layout, graphics
import java.awt.event.*;         // 📌 Untuk menangani event (klik, hover, dll)

// ════════════════════════════════════════════════════════════════════════════════════
// 📌 CLASS: FormDashboard
// 📝 FUNGSI: Membuat halaman dashboard utama aplikasi
// 🔧 DAPAT DIUBAH: Warna tema, teks, promo, tombol navigasi
// ════════════════════════════════════════════════════════════════════════════════════
public class FormDashboard extends JFrame {
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 🎨 [DESIGN] - WARNA TEMA DASHBOARD
    // 📌 GANTI: Ubah nilai RGB di sini untuk mengganti tema warna seluruh dashboard
    // ──────────────────────────────────────────────────────────────────────────────
    
    // 🔵 COLOR_BG: Warna background utama (gradient atas)
    // 📌 GANTI: new Color(0, 102, 204) → new Color(99, 102, 241) untuk indigo
    private final Color COLOR_BG = new Color(0, 102, 204);          // 🔵 Biru utama (RGB: 0,102,204)
    
    // 🔵 COLOR_BG_DARK: Warna background gradient bawah
    // 📌 GANTI: new Color(0, 51, 102) → new Color(79, 70, 229) untuk indigo tua
    private final Color COLOR_BG_DARK = new Color(0, 51, 102);      // 🔵 Biru tua (RGB: 0,51,102)
    
    // 🔵 CARD_BG: Warna background untuk card/panel putih
    // 📌 GANTI: new Color(224, 240, 255) → Color.WHITE untuk putih polos
    private final Color CARD_BG = new Color(224, 240, 255);         // 🔵 Biru cerah (RGB: 224,240,255)
    
    // 🔵 CARD_BG_HEADER: Warna background untuk header card
    // 📌 GANTI: new Color(200, 225, 255) → new Color(238, 242, 255) untuk lebih terang
    private final Color CARD_BG_HEADER = new Color(200, 225, 255);  // 🔵 Biru lebih cerah (RGB: 200,225,255)
    
    // 🔵 TEXT_PRIMARY: Warna teks utama
    // 📌 GANTI: new Color(0, 51, 102) → Color.BLACK untuk teks hitam
    private final Color TEXT_PRIMARY = new Color(0, 51, 102);       // 🔵 Biru tua teks (RGB: 0,51,102)
    
    // 🔵 TEXT_MUTED: Warna teks sekunder (untuk subtitle)
    // 📌 GANTI: new Color(70, 130, 200) → new Color(107, 114, 128) untuk abu-abu
    private final Color TEXT_MUTED = new Color(70, 130, 200);       // 🔵 Steel Blue (RGB: 70,130,200)
    
    // 🔵 BORDER_COLOR: Warna garis tepi/border
    // 📌 GANTI: new Color(100, 149, 237) → new Color(209, 213, 219) untuk abu-abu
    private final Color BORDER_COLOR = new Color(100, 149, 237);    // 🔵 Cornflower Blue (RGB: 100,149,237)
    
    // 🟡 ACCENT_POIN: Warna aksen untuk kartu Poin
    // 📌 GANTI: new Color(245, 158, 11) → new Color(251, 191, 36) untuk kuning lebih cerah
    private final Color ACCENT_POIN = new Color(245, 158, 11);      // 🟡 Orange (RGB: 245,158,11)
    
    // 🟢 ACCENT_TOTAL: Warna aksen untuk kartu Total Topup
    // 📌 GANTI: new Color(16, 185, 129) → new Color(52, 211, 153) untuk hijau lebih cerah
    private final Color ACCENT_TOTAL = new Color(16, 185, 129);     // 🟢 Hijau (RGB: 16,185,129)
    
    // 🔵 ACCENT_FAVORITE: Warna aksen untuk kartu Game Favorit
    // 📌 GANTI: new Color(0, 102, 204) → new Color(99, 102, 241) untuk indigo
    private final Color ACCENT_FAVORITE = new Color(0, 102, 204);   // 🔵 Biru (RGB: 0,102,204)
    
    // 🔴 ACCENT_PROMO: Warna aksen untuk kartu Promo
    // 📌 GANTI: new Color(239, 68, 68) → new Color(248, 113, 113) untuk merah lebih cerah
    private final Color ACCENT_PROMO = new Color(239, 68, 68);      // 🔴 Merah (RGB: 239,68,68)
    
    // 🔵 BUTTON_BG: Warna background tombol navigasi
    // 📌 GANTI: new Color(224, 240, 255) → Color.WHITE untuk tombol putih
    private final Color BUTTON_BG = new Color(224, 240, 255);       // 🔵 Biru cerah tombol (RGB: 224,240,255)
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 DEKLARASI KOMPONEN GUI
    // ──────────────────────────────────────────────────────────────────────────────
    
    // 👤 loggedInUser: Menyimpan data user yang sedang login
    private User loggedInUser;               // Objek User dari package model
    
    // 📝 Label-label untuk menampilkan data dinamis
    private JLabel lblWelcome;               // Label sapaan "Halo, [username]"
    private JLabel lblPoin;                  // Label jumlah poin user
    private JLabel lblTotalTopup;            // Label total topup user
    private JLabel lblFavoriteGame;          // Label game favorit user
    
    // 📦 Panel untuk menampilkan daftar promo
    private JPanel promoPanel;               // Panel berisi daftar promo aktif
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 CONSTRUCTOR
    // 📝 FUNGSI: Dipanggil saat objek FormDashboard dibuat
    // 🔧 DAPAT DIUBAH: Parameter (User user) → bisa ditambah parameter lain
    // ════════════════════════════════════════════════════════════════════════════════
    public FormDashboard(User user) {
        // 📌 Menyimpan data user yang login ke variable global
        this.loggedInUser = user;
        
        // 📌 Memanggil method untuk membuat komponen UI
        initComponents();
        
        // 📌 Memanggil method untuk mengisi data (poin, promo, dll)
        loadData();
        
        // 📌 Mengatur frame menjadi fullscreen (MAXIMIZED_BOTH = fullscreen)
        // 🔧 GANTI: Bisa diubah ke setSize(width, height) untuk ukuran tertentu
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // 📌 Menampilkan frame (setVisible true agar terlihat)
        setVisible(true);
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: initComponents()
    // 📝 FUNGSI: Membuat dan mengatur semua komponen UI di dashboard
    // 🔧 DAPAT DIUBAH: Layout, ukuran, padding, warna, teks
    // ════════════════════════════════════════════════════════════════════════════════
    private void initComponents() {
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 PENGATURAN FRAME (Jendela Utama)
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 GANTI: Judul yang muncul di title bar
        setTitle("Dashboard - Topup Game Store");
        
        // 📌 GANTI: Perilaku saat tombol close ditekan
        // EXIT_ON_CLOSE → aplikasi berhenti
        // DISPOSE_ON_CLOSE → hanya close frame ini
        // DO_NOTHING_ON_CLOSE → tidak melakukan apa-apa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 📌 Meletakkan frame di tengah layar
        setLocationRelativeTo(null);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🎨 [DESIGN] - MAIN PANEL DENGAN GRADIENT BACKGROUND
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 Membuat panel utama dengan gradient background
        // 🔧 GANTI: Warna gradient di method paintComponent
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 📌 Menggunakan Graphics2D untuk kualitas gambar lebih baik
                Graphics2D g2d = (Graphics2D) g;
                // 📌 Anti-aliasing untuk hasil lebih halus
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                // 📌 Membuat gradient dari atas ke bawah
                // 🔧 GANTI: Ubah COLOR_BG dan COLOR_BG_DARK untuk warna gradient
                GradientPaint gp = new GradientPaint(0, 0, COLOR_BG, getWidth(), getHeight(), COLOR_BG_DARK);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        // 📌 Layout utama menggunakan BorderLayout (utara, selatan, timur, barat, tengah)
        mainPanel.setLayout(new BorderLayout());
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 CONTENT PANEL (Panel tengah dengan padding)
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 Menggunakan GridBagLayout agar konten bisa diatur di tengah
        JPanel contentPanel = new JPanel(new GridBagLayout());
        // 📌 Background semi-transparan (alpha 240 = hampir solid)
        // 🔧 GANTI: Ubah warna dan alpha
        contentPanel.setBackground(new Color(0, 102, 204, 240));
        // 📌 Padding panel (atas, kiri, bawah, kanan)
        // 🔧 GANTI: Ubah angka padding
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        // 📌 GridBagConstraints untuk mengatur posisi komponen
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;          // Kolom ke-0
        gbc.gridy = 0;          // Baris ke-0
        gbc.weightx = 1.0;      // Lebar proporsional (1 = full)
        gbc.weighty = 1.0;      // Tinggi proporsional (1 = full)
        gbc.anchor = GridBagConstraints.CENTER;  // Posisi di tengah
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 CENTER PANEL (Panel utama berisi semua konten)
        // ────────────────────────────────────────────────────────────────────────────
        JPanel centerPanel = new JPanel();
        // 🔧 GANTI: Warna background center panel
        centerPanel.setBackground(new Color(224, 240, 255));
        // 📌 Layout vertikal (BoxLayout.Y_AXIS)
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        // 📌 Padding panel (atas, kiri, bawah, kanan)
        // 🔧 GANTI: Ubah padding
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        // ════════════════════════════════════════════════════════════════════════════
        // 📌 HEADER PANEL - Avatar, Nama, dan Status Member
        // ════════════════════════════════════════════════════════════════════════════
        JPanel headerPanel = new JPanel(new BorderLayout());
        // 🔧 GANTI: Warna background header
        headerPanel.setBackground(CARD_BG_HEADER);
        // 📌 Border dengan warna dan ketebalan
        // 🔧 GANTI: Warna border (BORDER_COLOR) dan ketebalan (2)
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 2),
            BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));
        // 📌 Lebar maksimum (Integer.MAX_VALUE = tidak terbatas)
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 👤 AVATAR (Icon user)
        // ────────────────────────────────────────────────────────────────────────────
        // 🔧 GANTI: Emoji avatar ("👤", "🧑", "👨", "👩", "🦸")
        JLabel avatar = new JLabel("👤");
        // 🔧 GANTI: Ukuran font avatar
        avatar.setFont(new Font("Dialog", Font.PLAIN, 55));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📝 TEXT PANEL (Welcome & Member status)
        // ────────────────────────────────────────────────────────────────────────────
        JPanel textPanel = new JPanel(new GridLayout(2, 1));  // 2 baris, 1 kolom
        textPanel.setBackground(CARD_BG_HEADER);
        
        // 📌 Label sapaan
        // 🔧 GANTI: Format teks ("Halo, " + username)
        lblWelcome = new JLabel("Halo, " + loggedInUser.getUsername());
        // 🔧 GANTI: Font dan ukuran
        lblWelcome.setFont(new Font("Dialog", Font.BOLD, 24));
        // 🔧 GANTI: Warna teks
        lblWelcome.setForeground(TEXT_PRIMARY);
        
        // 📌 Label status member
        // 🔧 GANTI: Teks status member
        JLabel lblMember = new JLabel("Member sejak 2026");
        // 🔧 GANTI: Font dan ukuran
        lblMember.setFont(new Font("Dialog", Font.PLAIN, 14));
        // 🔧 GANTI: Warna teks
        lblMember.setForeground(TEXT_MUTED);
        
        textPanel.add(lblWelcome);
        textPanel.add(lblMember);
        
        // 📌 Menambahkan avatar di kiri dan text di tengah
        headerPanel.add(avatar, BorderLayout.WEST);
        headerPanel.add(textPanel, BorderLayout.CENTER);
        
        // 📌 Menambahkan header panel ke center panel
        centerPanel.add(headerPanel);
        centerPanel.add(Box.createVerticalStrut(20));  // Jarak vertikal 20px
        
        // ════════════════════════════════════════════════════════════════════════════
        // 📊 KARTU STATISTIK (3 kartu dalam 1 baris)
        // ════════════════════════════════════════════════════════════════════════════
        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 20, 0));  // 1 baris, 3 kolom, gap 20px
        // 🔧 GANTI: Warna background stats panel
        statsPanel.setBackground(CARD_BG);
        statsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        
        // 📌 Membuat 3 kartu statistik dengan data dari user
        // 🔧 GANTI: Teks, warna, dan ukuran font di method createStatCard()
        statsPanel.add(createStatCard("⭐ POIN SAYA", ACCENT_POIN, 
                                      loggedInUser.getPoin() + " Poin", 32));
        statsPanel.add(createStatCard("💰 TOTAL TOPUP", ACCENT_TOTAL, 
                                      "Rp " + formatRupiah(loggedInUser.getTotalTopup()), 32));
        statsPanel.add(createStatCard("🎮 GAME FAVORIT", ACCENT_FAVORITE, 
                                      "Mobile Legends", 18));
        
        centerPanel.add(statsPanel);
        centerPanel.add(Box.createVerticalStrut(20));
        
        // ════════════════════════════════════════════════════════════════════════════
        // 🎉 PROMO HARI INI
        // ════════════════════════════════════════════════════════════════════════════
        JPanel promoCard = new JPanel();
        // 🔧 GANTI: Warna background promo card
        promoCard.setBackground(CARD_BG);
        // 🔧 GANTI: Warna dan ketebalan border
        promoCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 2),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        promoCard.setLayout(new BoxLayout(promoCard, BoxLayout.Y_AXIS));
        promoCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
        
        // 📌 Judul promo
        // 🔧 GANTI: Teks judul
        JLabel promoTitle = new JLabel("🎉 PROMO HARI INI");
        // 🔧 GANTI: Font dan ukuran
        promoTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        // 🔧 GANTI: Warna teks
        promoTitle.setForeground(ACCENT_PROMO);
        promoTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        promoCard.add(promoTitle);
        promoCard.add(Box.createVerticalStrut(15));
        
        // 📌 Panel untuk daftar promo (akan diisi di method loadData())
        promoPanel = new JPanel();
        promoPanel.setBackground(CARD_BG);
        promoPanel.setLayout(new GridLayout(3, 1, 0, 8));  // 3 baris, 1 kolom, gap 8px
        promoCard.add(promoPanel);
        
        centerPanel.add(promoCard);
        centerPanel.add(Box.createVerticalStrut(25));
        
        // ════════════════════════════════════════════════════════════════════════════
        // 🎮 TOMBOL NAVIGASI
        // ════════════════════════════════════════════════════════════════════════════
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 15, 15));  // 2x2 grid, gap 15px
        // 🔧 GANTI: Warna background button panel
        buttonPanel.setBackground(CARD_BG);
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🎮 Tombol 1: TOPUP
        // ────────────────────────────────────────────────────────────────────────────
        buttonPanel.add(createNavButton("🎮", "TOPUP", ACCENT_FAVORITE, () -> {
            dispose();                              // Tutup dashboard
            new FormTopup(loggedInUser);            // Buka FormTopup
        }));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📋 Tombol 2: HISTORY
        // ────────────────────────────────────────────────────────────────────────────
        buttonPanel.add(createNavButton("📋", "HISTORY", ACCENT_TOTAL, () -> {
            new FormHistory(loggedInUser);          // Buka FormHistory
        }));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 👤 Tombol 3: PROFILE
        // ────────────────────────────────────────────────────────────────────────────
        buttonPanel.add(createNavButton("👤", "PROFILE", ACCENT_POIN, this::showProfile));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🚪 Tombol 4: LOGOUT
        // ────────────────────────────────────────────────────────────────────────────
        buttonPanel.add(createNavButton("🚪", "LOGOUT", ACCENT_PROMO, () -> {
            // 📌 Konfirmasi sebelum logout
            int confirm = JOptionPane.showConfirmDialog(
                this, 
                "Yakin ingin logout?",    // Pesan konfirmasi
                "Konfirmasi",             // Judul dialog
                JOptionPane.YES_NO_OPTION // Opsi Yes/No
            );
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();                  // Tutup dashboard
                new FormLogin().setVisible(true);  // Buka halaman login
            }
        }));
        
        // ➕ [TAMBAH] - Cara menambah tombol navigasi baru:
        // buttonPanel.add(createNavButton("⭐", "REWARD", new Color(245, 158, 11), () -> {
        //     JOptionPane.showMessageDialog(this, "Fitur Reward");
        // }));
        
        centerPanel.add(buttonPanel);
        centerPanel.add(Box.createVerticalStrut(20));
        
        // ════════════════════════════════════════════════════════════════════════════
        // 📌 FOOTER
        // ════════════════════════════════════════════════════════════════════════════
        // 🔧 GANTI: Teks footer
        JLabel footer = new JLabel("© 2026 Topup Game Store | Topup Diamond Termurah dan Terpercaya");
        // 🔧 GANTI: Font footer
        footer.setFont(new Font("Dialog", Font.PLAIN, 11));
        // 🔧 GANTI: Warna footer
        footer.setForeground(TEXT_MUTED);
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(footer);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 MENAMBAHKAN PANEL KE FRAME
        // ────────────────────────────────────────────────────────────────────────────
        contentPanel.add(centerPanel, gbc);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // 📌 Membuat scroll pane agar konten bisa di-scroll jika terlalu panjang
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);  // Menghilangkan border scroll pane
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);  // Kecepatan scroll
        
        // 📌 Menambahkan scroll pane ke frame
        add(scrollPane);
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: createStatCard()
    // 📝 FUNGSI: Membuat kartu statistik (Poin, Total Topup, Game Favorit)
    // 🔧 DAPAT DIUBAH: Warna, ukuran, padding, font
    // ════════════════════════════════════════════════════════════════════════════════
    private JPanel createStatCard(String title, Color accentColor, String value, int fontSize) {
        
        // 📌 Membuat panel card
        JPanel card = new JPanel();
        // 🔧 GANTI: Warna background card
        card.setBackground(CARD_BG);
        // 📌 Border dengan warna dan padding
        // 🔧 GANTI: Warna border (BORDER_COLOR) dan ketebalan (2)
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        // 📌 Layout vertikal (BoxLayout.Y_AXIS)
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 JUDUL KARTU
        // ────────────────────────────────────────────────────────────────────────────
        JLabel titleLabel = new JLabel(title);
        // 🔧 GANTI: Font dan ukuran judul
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        // 🔧 GANTI: Warna judul (menggunakan accentColor)
        titleLabel.setForeground(accentColor);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(12));  // Jarak 12px
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 VALUE KARTU (angka/nilai)
        // ────────────────────────────────────────────────────────────────────────────
        JLabel valueLabel = new JLabel(value);
        // 🔧 GANTI: Font dan ukuran value (menggunakan parameter fontSize)
        valueLabel.setFont(new Font("Dialog", Font.BOLD, fontSize));
        // 🔧 GANTI: Warna value
        valueLabel.setForeground(TEXT_PRIMARY);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(valueLabel);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 MENYIMPAN REFERENCE UNTUK UPDATE DATA NANTI
        // ────────────────────────────────────────────────────────────────────────────
        if (title.equals("⭐ POIN SAYA")) {
            lblPoin = valueLabel;              // Simpan reference label poin
        } else if (title.equals("💰 TOTAL TOPUP")) {
            lblTotalTopup = valueLabel;        // Simpan reference label total topup
        } else if (title.equals("🎮 GAME FAVORIT")) {
            lblFavoriteGame = valueLabel;      // Simpan reference label game favorit
        }
        
        return card;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: createNavButton()
    // 📝 FUNGSI: Membuat tombol navigasi dengan icon dan teks
    // 🔧 DAPAT DIUBAH: Icon, teks, warna, ukuran, padding
    // ════════════════════════════════════════════════════════════════════════════════
    private JButton createNavButton(String icon, String text, Color color, Runnable action) {
        
        // 📌 Membuat tombol tanpa teks bawaan (akan diisi custom)
        JButton btn = new JButton();
        // 📌 Layout tombol vertikal (icon di atas, teks di bawah)
        btn.setLayout(new BoxLayout(btn, BoxLayout.Y_AXIS));
        // 🔧 GANTI: Warna background tombol
        btn.setBackground(BUTTON_BG);
        // 🔧 GANTI: Warna teks (menggunakan parameter color)
        btn.setForeground(color);
        btn.setFocusPainted(false);  // Menghilangkan border focus
        // 📌 Border dengan warna dan padding
        // 🔧 GANTI: Warna border (BORDER_COLOR), ketebalan (2), padding
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        // 📌 Mengubah kursor menjadi hand saat di-hover
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🖼️ ICON
        // ────────────────────────────────────────────────────────────────────────────
        JLabel iconLabel = new JLabel(icon);
        // 🔧 GANTI: Ukuran font icon
        iconLabel.setFont(new Font("Dialog", Font.PLAIN, 32));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📝 TEKS
        // ────────────────────────────────────────────────────────────────────────────
        JLabel textLabel = new JLabel(text);
        // 🔧 GANTI: Font dan ukuran teks
        textLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        // 🔧 GANTI: Warna teks (menggunakan parameter color)
        textLabel.setForeground(color);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // 📌 Menambahkan komponen ke tombol
        btn.add(iconLabel);
        btn.add(Box.createVerticalStrut(8));  // Jarak antara icon dan teks
        btn.add(textLabel);
        
        // 📌 Menambahkan action listener (event klik)
        btn.addActionListener(e -> action.run());
        
        return btn;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: showProfile()
    // 📝 FUNGSI: Menampilkan dialog profil user
    // 🔧 DAPAT DIUBAH: Data yang ditampilkan, layout, warna
    // ════════════════════════════════════════════════════════════════════════════════
    private void showProfile() {
        
        // 📌 Membuat panel profil
        JPanel profilePanel = new JPanel();
        // 📌 Layout vertikal
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        // 📌 Padding panel
        profilePanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        // 🔧 GANTI: Warna background
        profilePanel.setBackground(CARD_BG);
        
        // 📌 Menambahkan baris-baris data profil
        // 🔧 GANTI: Label dan data yang ditampilkan
        profilePanel.add(createProfileRow("👤 Username", loggedInUser.getUsername()));
        profilePanel.add(Box.createVerticalStrut(12));
        profilePanel.add(createProfileRow("📧 Email", loggedInUser.getEmail()));
        profilePanel.add(Box.createVerticalStrut(12));
        profilePanel.add(createProfileRow("📱 No HP", loggedInUser.getNoHp()));
        profilePanel.add(Box.createVerticalStrut(12));
        profilePanel.add(createProfileRow("⭐ Poin", loggedInUser.getPoin() + " Poin"));
        profilePanel.add(Box.createVerticalStrut(12));
        profilePanel.add(createProfileRow("💰 Total Topup", "Rp " + formatRupiah(loggedInUser.getTotalTopup())));
        
        // ➕ [TAMBAH] - Tambah data profil baru:
        // profilePanel.add(Box.createVerticalStrut(12));
        // profilePanel.add(createProfileRow("📅 Tanggal Lahir", "01/01/2000"));
        
        // 📌 Menampilkan dialog dengan JOptionPane
        JOptionPane.showMessageDialog(
            this,                  // Parent component
            profilePanel,          // Panel yang ditampilkan
            "📋 Profil Saya",      // Judul dialog
            JOptionPane.INFORMATION_MESSAGE  // Tipe icon (informasi)
        );
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: createProfileRow()
    // 📝 FUNGSI: Membuat satu baris data profil (label : value)
    // 🔧 DAPAT DIUBAH: Font, warna, spacing
    // ════════════════════════════════════════════════════════════════════════════════
    private JPanel createProfileRow(String label, String value) {
        
        // 📌 Panel dengan BorderLayout (label di kiri, value di kanan)
        JPanel row = new JPanel(new BorderLayout(15, 0));  // gap horizontal 15px
        // 🔧 GANTI: Warna background
        row.setBackground(CARD_BG);
        
        // 📌 Label kiri (nama field)
        JLabel lblLabel = new JLabel(label);
        // 🔧 GANTI: Font label
        lblLabel.setFont(new Font("Dialog", Font.BOLD, 13));
        // 🔧 GANTI: Warna label
        lblLabel.setForeground(TEXT_PRIMARY);
        
        // 📌 Label kanan (value)
        JLabel lblValue = new JLabel(value);
        // 🔧 GANTI: Font value
        lblValue.setFont(new Font("Dialog", Font.PLAIN, 13));
        // 🔧 GANTI: Warna value
        lblValue.setForeground(TEXT_MUTED);
        
        // 📌 Menambahkan ke panel
        row.add(lblLabel, BorderLayout.WEST);
        row.add(lblValue, BorderLayout.EAST);
        
        return row;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: loadData()
    // 📝 FUNGSI: Mengisi data dashboard (promo, game favorit, poin)
    // 🔧 DAPAT DIUBAH: Daftar promo, cara menghitung game favorit
    // ════════════════════════════════════════════════════════════════════════════════
    private void loadData() {
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📊 LOAD PROMO
        // ────────────────────────────────────────────────────────────────────────────
        promoPanel.removeAll();  // Membersihkan panel promo
        
        // 📌 Daftar promo yang akan ditampilkan
        // 🔧 GANTI: Tambah/hapus promo di sini
        String[] promos = {
            "🎁 WEEKEND20 - Diskon 20% semua game!",
            "🎉 FREEADMIN - Gratis biaya admin!",
            
        };
        
        // ➕ [TAMBAH] - Cara menambah promo baru:
        // "🔥 FLASHSALE - Diskon 50% (12:00-13:00)"
        // "🎊 NEWYEAR - Diskon 30% Tahun Baru!"
        
        // 📌 Looping untuk menampilkan setiap promo
        for (String promo : promos) {
            // 📌 Membuat baris promo dengan FlowLayout center
            JPanel promoRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
            // 🔧 GANTI: Warna background baris promo
            promoRow.setBackground(CARD_BG);
            
            // 📌 Label promo
            JLabel promoLabel = new JLabel("📢 " + promo);
            // 🔧 GANTI: Font dan ukuran promo
            promoLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
            // 🔧 GANTI: Warna teks promo
            promoLabel.setForeground(TEXT_MUTED);
            
            promoRow.add(promoLabel);
            promoPanel.add(promoRow);
        }
        
        // 📌 Refresh panel promo
        promoPanel.revalidate();
        promoPanel.repaint();
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📊 HITUNG GAME FAVORIT
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 Menghitung game yang paling sering di-topup oleh user
        java.util.Map<String, Integer> gameCount = new java.util.HashMap<>();
        
        // 📌 Looping semua transaksi user
        for (Transaction t : Transaction.getTransaksiByUsername(loggedInUser.getUsername()).values()) {
            // 📌 Menambah count game (jika belum ada, default 0)
            gameCount.put(t.getGameName(), gameCount.getOrDefault(t.getGameName(), 0) + 1);
        }
        
        // 📌 Mencari game dengan count tertinggi
        String favorite = gameCount.entrySet().stream()
            .max(java.util.Map.Entry.comparingByValue())  // Mencari max value
            .map(java.util.Map.Entry::getKey)              // Mengambil key (nama game)
            .orElse("Belum ada topup");                     // Default jika belum ada
        
        // 📌 Update label game favorit
        if (lblFavoriteGame != null) {
            lblFavoriteGame.setText(favorite);
        }
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📊 UPDATE DATA USER TERBARU
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 Mengambil data user terbaru dari database
        User updatedUser = User.getUserByUsername(loggedInUser.getUsername());
        
        if (updatedUser != null) {
            loggedInUser = updatedUser;  // Update objek user
            
            // 📌 Update label poin dan total topup
            if (lblPoin != null) {
                lblPoin.setText(updatedUser.getPoin() + " Poin");
            }
            if (lblTotalTopup != null) {
                lblTotalTopup.setText("Rp " + formatRupiah(updatedUser.getTotalTopup()));
            }
        }
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: formatRupiah()
    // 📝 FUNGSI: Memformat angka menjadi format Rupiah (contoh: 1.000.000)
    // 🔧 DAPAT DIUBAH: Format pemisah ("," atau "."), simbol mata uang
    // ════════════════════════════════════════════════════════════════════════════════
    private String formatRupiah(int amount) {
        // 📌 String.format("%,d", amount) → menambahkan pemisah ribuan
        // 📌 replace(",", ".") → mengganti koma dengan titik (format Indonesia)
        // 🔧 GANTI: Ubah "," menjadi "." atau sebaliknya
        return String.format("%,d", amount).replace(",", ".");
    }
}