// ════════════════════════════════════════════════════════════════════════════════════
// 📌 FILE: FormHistory.java
// 📝 FUNGSI: Menampilkan riwayat transaksi topup user
// 🔧 DAPAT DIUBAH: Warna tema, kolom tabel, status transaksi
// ════════════════════════════════════════════════════════════════════════════════════

// 📦 Package: form - tempat menyimpan class GUI/Form
package form;

// 📦 Mengimport library yang dibutuhkan
import model.User;              // 📌 Mengambil data user dari package model
import model.Transaction;       // 📌 Mengambil data transaksi dari package model
import javax.swing.*;           // 📌 Komponen GUI Swing (JFrame, JTable, JPanel, dll)
import javax.swing.table.*;     // 📌 Untuk mengatur tabel (DefaultTableModel, TableColumn)
import java.awt.*;              // 📌 Untuk warna, layout, graphics
import java.util.*;             // 📌 Untuk ArrayList, Map, Collections

// ════════════════════════════════════════════════════════════════════════════════════
// 📌 CLASS: FormHistory
// 📝 FUNGSI: Membuat halaman riwayat transaksi user
// 🔧 DAPAT DIUBAH: Warna tema, kolom tabel, status transaksi
// ════════════════════════════════════════════════════════════════════════════════════
public class FormHistory extends JFrame {
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 🎨 [DESIGN] - WARNA TEMA HISTORY
    // 📌 GANTI: Ubah nilai RGB di sini untuk mengganti tema warna
    // ──────────────────────────────────────────────────────────────────────────────
    
    // 🔵 COLOR_BG: Warna background utama (gradient atas)
    private final Color COLOR_BG = new Color(0, 102, 204);          // 🔵 Biru utama
    
    // 🔵 COLOR_BG_DARK: Warna background gradient bawah
    private final Color COLOR_BG_DARK = new Color(0, 51, 102);      // 🔵 Biru tua
    
    // 🔵 HEADER_BG: Warna background header tabel
    private final Color HEADER_BG = new Color(0, 51, 102);          // 🔵 Biru tua
    
    // ⬜ TABLE_ROW_EVEN: Warna baris genap tabel
    private final Color TABLE_ROW_EVEN = new Color(255, 255, 255);  // ⬜ Putih
    
    // 🔵 TABLE_ROW_ODD: Warna baris ganjil tabel (selang-seling)
    private final Color TABLE_ROW_ODD = new Color(240, 248, 255);   // 🔵 Biru sangat muda
    
    // ⬛ TEXT_COLOR: Warna teks utama
    private final Color TEXT_COLOR = new Color(0, 0, 0);            // ⬛ Hitam
    
    // ⬛ HEADER_TEXT_COLOR: Warna teks header tabel
    private final Color HEADER_TEXT_COLOR = new Color(0, 0, 0);     // ⬛ Hitam
    
    // 🔘 TEXT_MUTED: Warna teks sekunder (untuk subtitle)
    private final Color TEXT_MUTED = new Color(80, 80, 80);         // 🔘 Abu-abu gelap
    
    // 🔵 BORDER_COLOR: Warna garis tepi/border
    private final Color BORDER_COLOR = new Color(100, 149, 237);    // 🔵 Cornflower Blue
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 🎨 [DESIGN] - WARNA STATUS TRANSAKSI
    // 📌 GANTI: Ubah warna status di sini
    // ──────────────────────────────────────────────────────────────────────────────
    
    // 🟢 STATUS_SUCCESS: Warna untuk status "Success" (Hijau)
    private final Color STATUS_SUCCESS = new Color(16, 185, 129);   // 🟢 Hijau
    
    // 🟠 STATUS_PENDING: Warna untuk status "Pending" (Orange)
    private final Color STATUS_PENDING = new Color(245, 158, 11);   // 🟠 Orange
    
    // 🔵 STATUS_PROCESSING: Warna untuk status "Processing" (Biru)
    private final Color STATUS_PROCESSING = new Color(59, 130, 246); // 🔵 Biru
    
    // 🔴 STATUS_FAILED: Warna untuk status "Failed" (Merah)
    private final Color STATUS_FAILED = new Color(239, 68, 68);      // 🔴 Merah
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 DEKLARASI KOMPONEN GUI
    // ──────────────────────────────────────────────────────────────────────────────
    
    // 📊 table: Komponen tabel untuk menampilkan data riwayat
    private JTable table;
    
    // 📋 model: Model data tabel (menyimpan data dalam bentuk baris & kolom)
    private DefaultTableModel model;
    
    // 👤 loggedInUser: Menyimpan data user yang sedang login
    private User loggedInUser;
    
    // 📝 lblTotalTransaksi: Label untuk menampilkan jumlah total transaksi
    private JLabel lblTotalTransaksi;
    
    // 💰 lblTotalNominal: Label untuk menampilkan total nominal topup
    private JLabel lblTotalNominal;
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 CONSTRUCTOR
    // 📝 FUNGSI: Dipanggil saat objek FormHistory dibuat
    // 🔧 DAPAT DIUBAH: Parameter (User user) → bisa ditambah parameter lain
    // ════════════════════════════════════════════════════════════════════════════════
    public FormHistory(User user) {
        // 📌 Menyimpan data user yang login
        this.loggedInUser = user;
        
        // 📌 Memanggil method untuk membuat komponen UI
        initComponents();
        
        // 📌 Memanggil method untuk mengisi data riwayat
        loadData();
        
        // 📌 Mengatur frame menjadi fullscreen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // 📌 Menampilkan frame
        setVisible(true);
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: initComponents()
    // 📝 FUNGSI: Membuat dan mengatur semua komponen UI di halaman history
    // 🔧 DAPAT DIUBAH: Ukuran, warna, padding, layout
    // ════════════════════════════════════════════════════════════════════════════════
    private void initComponents() {
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 PENGATURAN FRAME (Jendela Utama)
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 GANTI: Judul yang muncul di title bar
        setTitle("Riwayat Topup - " + loggedInUser.getUsername());
        
        // 📌 GANTI: Perilaku saat tombol close ditekan
        // DISPOSE_ON_CLOSE → hanya close frame ini (tidak menghentikan aplikasi)
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // 📌 Meletakkan frame di tengah layar
        setLocationRelativeTo(null);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🎨 [DESIGN] - MAIN PANEL DENGAN GRADIENT BACKGROUND
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 Membuat panel utama dengan gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 📌 Menggunakan Graphics2D untuk kualitas gambar lebih baik
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                // 📌 Membuat gradient dari atas ke bawah
                GradientPaint gp = new GradientPaint(0, 0, COLOR_BG, getWidth(), getHeight(), COLOR_BG_DARK);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        // 📌 Padding panel (atas, kiri, bawah, kanan)
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 CONTENT PANEL (Panel putih dengan border)
        // ────────────────────────────────────────────────────────────────────────────
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        // 📌 Border dengan warna dan padding
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 212, 220), 1),
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        // 📌 Layout dengan jarak vertikal 15px
        contentPanel.setLayout(new BorderLayout(0, 15));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 HEADER PANEL - Judul & Subtitle
        // ────────────────────────────────────────────────────────────────────────────
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        // 📌 Judul utama
        JLabel titleLabel = new JLabel("📋 RIWAYAT TOPUP");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 51, 102));
        
        // 📌 Subtitle
        JLabel subtitleLabel = new JLabel("Daftar semua transaksi topup Anda");
        subtitleLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        subtitleLabel.setForeground(TEXT_MUTED);
        
        // 📌 Panel judul (2 baris)
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(Color.WHITE);
        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);
        headerPanel.add(titlePanel, BorderLayout.WEST);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📊 STATISTIK PANEL - Total Transaksi & Total Nominal
        // ────────────────────────────────────────────────────────────────────────────
        JPanel statsPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // 📌 Kartu Total Transaksi
        JPanel cardTotalTrans = createStatCard("📊 TOTAL TRANSAKSI", new Color(16, 185, 129));
        lblTotalTransaksi = new JLabel("0");
        lblTotalTransaksi.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTotalTransaksi.setForeground(new Color(16, 185, 129));
        cardTotalTrans.add(lblTotalTransaksi);
        
        // 📌 Kartu Total Nominal
        JPanel cardTotalNominal = createStatCard("💰 TOTAL NOMINAL", new Color(0, 102, 204));
        lblTotalNominal = new JLabel("Rp 0");
        lblTotalNominal.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTotalNominal.setForeground(new Color(0, 102, 204));
        cardTotalNominal.add(lblTotalNominal);
        
        statsPanel.add(cardTotalTrans);
        statsPanel.add(cardTotalNominal);
        headerPanel.add(statsPanel, BorderLayout.EAST);
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📊 TABEL RIWAYAT
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 Definisi kolom tabel
        // 🔧 GANTI: Tambah/kurang kolom di sini
        String[] columns = {"No", "Tanggal", "Game", "Nominal", "Total Bayar", "Metode", "Status"};
        // ➕ [TAMBAH] - Tambah kolom "Kode Transaksi":
        // String[] columns = {"No", "Kode Transaksi", "Tanggal", "Game", "Nominal", "Total Bayar", "Metode", "Status"};
        
        // 📌 Model tabel (data tidak bisa diedit langsung)
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // 📌 Semua sel tidak bisa diedit
            }
        };
        
        // 📌 Membuat tabel
        table = new JTable(model);
        // 📌 Tinggi baris
        table.setRowHeight(45);
        // 📌 Font tabel
        table.setFont(new Font("Dialog", Font.PLAIN, 13));
        table.setForeground(TEXT_COLOR);
        table.setBackground(Color.WHITE);
        // 📌 Warna saat baris dipilih
        table.setSelectionBackground(new Color(200, 225, 255));
        table.setSelectionForeground(TEXT_COLOR);
        // 📌 Menampilkan grid
        table.setShowGrid(true);
        table.setGridColor(new Color(200, 212, 220));
        table.setIntercellSpacing(new Dimension(10, 8));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 HEADER TABEL
        // ────────────────────────────────────────────────────────────────────────────
        JTableHeader tableHeader = table.getTableHeader();
        // 🔧 GANTI: Font header
        tableHeader.setFont(new Font("Dialog", Font.BOLD, 14));
        // 🔧 GANTI: Warna teks header
        tableHeader.setForeground(HEADER_TEXT_COLOR);
        // 🔧 GANTI: Warna background header
        tableHeader.setBackground(HEADER_BG);
        // 📌 Tinggi header
        tableHeader.setPreferredSize(new Dimension(0, 45));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 LEBAR KOLOM
        // 🔧 GANTI: Ubah angka untuk mengatur lebar kolom
        // ────────────────────────────────────────────────────────────────────────────
        table.getColumnModel().getColumn(0).setPreferredWidth(60);    // No
        table.getColumnModel().getColumn(1).setPreferredWidth(160);   // Tanggal
        table.getColumnModel().getColumn(2).setPreferredWidth(180);   // Game
        table.getColumnModel().getColumn(3).setPreferredWidth(140);   // Nominal
        table.getColumnModel().getColumn(4).setPreferredWidth(150);   // Total Bayar
        table.getColumnModel().getColumn(5).setPreferredWidth(130);   // Metode
        table.getColumnModel().getColumn(6).setPreferredWidth(120);   // Status
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 RENDERER WARNA BARIS (selang-seling)
        // ────────────────────────────────────────────────────────────────────────────
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(new Font("Dialog", Font.PLAIN, 13));
                
                if (!isSelected) {
                    // 📌 Baris genap = putih, baris ganjil = biru muda
                    if (row % 2 == 0) {
                        c.setBackground(TABLE_ROW_EVEN);
                    } else {
                        c.setBackground(TABLE_ROW_ODD);
                    }
                } else {
                    c.setBackground(new Color(200, 225, 255));
                }
                c.setForeground(TEXT_COLOR);
                setHorizontalAlignment(SwingConstants.CENTER);  // Teks di tengah
                return c;
            }
        });
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 SCROLL PANE (agar tabel bisa di-scroll)
        // ────────────────────────────────────────────────────────────────────────────
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 212, 220)));
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 BOTTOM PANEL - Tombol Refresh & Back
        // ────────────────────────────────────────────────────────────────────────────
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🔄 TOMBOL REFRESH
        // ────────────────────────────────────────────────────────────────────────────
        JButton btnRefresh = new JButton("🔄 Refresh");
        btnRefresh.setFont(new Font("Dialog", Font.BOLD, 13));
        btnRefresh.setBackground(new Color(224, 240, 255));
        btnRefresh.setForeground(new Color(0, 51, 102));
        btnRefresh.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));
        btnRefresh.setFocusPainted(false);
        btnRefresh.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRefresh.addActionListener(e -> loadData());  // 📌 Refresh data
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🔙 TOMBOL KEMBALI
        // ────────────────────────────────────────────────────────────────────────────
        JButton btnBack = new JButton("← Kembali ke Dashboard");
        btnBack.setFont(new Font("Dialog", Font.PLAIN, 12));
        btnBack.setBackground(Color.WHITE);
        btnBack.setForeground(new Color(0, 51, 102));
        btnBack.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(e -> {
            dispose();  // 📌 Tutup halaman history
            new FormDashboard(loggedInUser);  // 📌 Buka dashboard
        });
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 PANEL TOMBOL (kanan)
        // ────────────────────────────────────────────────────────────────────────────
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnBack);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 INFO LABEL (kiri)
        // ────────────────────────────────────────────────────────────────────────────
        JLabel infoLabel = new JLabel("💡 Transaksi yang sudah sukses akan masuk ke akun dalam 1-5 menit");
        infoLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
        infoLabel.setForeground(TEXT_MUTED);
        bottomPanel.add(infoLabel, BorderLayout.WEST);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 MENAMBAHKAN KE FRAME
        // ────────────────────────────────────────────────────────────────────────────
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // 📌 Scroll pane utama
        JScrollPane mainScroll = new JScrollPane(mainPanel);
        mainScroll.setBorder(null);
        add(mainScroll);
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: createStatCard()
    // 📝 FUNGSI: Membuat kartu statistik (Total Transaksi & Total Nominal)
    // 🔧 DAPAT DIUBAH: Warna, ukuran, padding
    // ════════════════════════════════════════════════════════════════════════════════
    private JPanel createStatCard(String title, Color accentColor) {
        // 📌 Membuat panel card
        JPanel card = new JPanel();
        card.setBackground(new Color(248, 250, 252));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 212, 220), 1),
            BorderFactory.createEmptyBorder(15, 25, 15, 25)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        
        // 📌 Judul kartu
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 13));
        titleLabel.setForeground(accentColor);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));  // Jarak 10px
        
        return card;  // 📌 Kembalikan card (value akan ditambahkan di luar)
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: loadData()
    // 📝 FUNGSI: Mengisi data riwayat transaksi ke tabel
    // 🔧 DAPAT DIUBAH: Status transaksi, format tanggal, format nominal
    // ════════════════════════════════════════════════════════════════════════════════
    private void loadData() {
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 1. KOSONGKAN TABEL
        // ────────────────────────────────────────────────────────────────────────────
        model.setRowCount(0);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 2. AMBIL DATA TRANSAKSI USER
        // ────────────────────────────────────────────────────────────────────────────
        Map<String, Transaction> transaksiUser = Transaction.getTransaksiByUsername(loggedInUser.getUsername());
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 3. CEK APAKAH ADA TRANSAKSI
        // ────────────────────────────────────────────────────────────────────────────
        if (transaksiUser.isEmpty()) {
            // 📌 Jika tidak ada transaksi, tampilkan pesan
            model.addRow(new Object[]{"-", "-", "📭 Belum ada transaksi", "-", "-", "-", "⏳"});
            lblTotalTransaksi.setText("0");
            lblTotalNominal.setText("Rp 0");
            table.setRowHeight(60);  // 📌 Tinggi baris lebih besar untuk pesan
            return;
        }
        
        // 📌 Reset tinggi baris ke normal
        table.setRowHeight(45);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 4. URUTKAN TRANSAKSI (Terbaru → Terlama)
        // ────────────────────────────────────────────────────────────────────────────
        java.util.List<Transaction> transactionList = new ArrayList<>(transaksiUser.values());
        transactionList.sort((a, b) -> {
            String dateA = a.getTanggal();
            String dateB = b.getTanggal();
            if (dateA == null || dateB == null) return 0;
            return dateB.compareTo(dateA);  // 📌 Descending (terbaru di atas)
        });
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 5. LOOPING TRANSAKSI
        // ────────────────────────────────────────────────────────────────────────────
        int no = 1;
        int totalTransaksi = 0;
        int totalNominal = 0;
        
        for (Transaction t : transactionList) {
            // 📌 Hitung total
            totalTransaksi++;
            totalNominal += t.getHargaFinal();
            
            // ────────────────────────────────────────────────────────────────────────
            // 🎯 [PENTING] - MENENTUKAN STATUS TRANSAKSI
            // 📌 GANTI: Teks status di sini
            // ────────────────────────────────────────────────────────────────────────
            String statusText;
            Color statusColor;
            
            if (t.getStatus().equals("PAYMENT_SUCCESS") || t.getStatus().equals("SUCCESS")) {
                statusText = "✅ Success";
                statusColor = STATUS_SUCCESS;
            } else if (t.getStatus().equals("WAITING_PAYMENT")) {
                // ⚠️ PERHATIAN: Di sini ada bug! WAITING_PAYMENT seharusnya "Pending" bukan "Success"
                // 🔧 PERBAIKAN: Ubah statusText = "⏳ Pending" (lihat di bawah)
                statusText = "✅ Success";  // ❌ SALAH! Seharusnya "⏳ Pending"
                statusColor = STATUS_PENDING;
            } else if (t.getStatus().equals("PROCESSING")) {
                statusText = "🔄 Processing";
                statusColor = STATUS_PROCESSING;
            } else {
                statusText = "❌ Failed";
                statusColor = STATUS_FAILED;
            }
            // 🔧 [PERBAIKAN] - Ganti baris WAITING_PAYMENT menjadi:
            // } else if (t.getStatus().equals("WAITING_PAYMENT")) {
            //     statusText = "⏳ Pending";
            //     statusColor = STATUS_PENDING;
            // ────────────────────────────────────────────────────────────────────────
            
            // 📌 Format data
            String nominalItem = t.getNominalItem() != null ? t.getNominalItem() : "-";
            String tanggal = t.getTanggal() != null ? t.getTanggal() : "-";
            
            // 📌 Tambahkan baris ke tabel
            model.addRow(new Object[]{
                no++,
                tanggal,
                t.getGameName() != null ? t.getGameName() : "-",
                nominalItem,
                "Rp " + String.format("%,d", t.getHargaFinal()).replace(",", "."),
                t.getMetodeBayar() != null ? t.getMetodeBayar() : "-",
                statusText
            });
        }
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 6. UPDATE STATISTIK
        // ────────────────────────────────────────────────────────────────────────────
        lblTotalTransaksi.setText(String.valueOf(totalTransaksi));
        lblTotalNominal.setText("Rp " + String.format("%,d", totalNominal).replace(",", "."));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 7. RENDERER WARNA STATUS
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 Mengatur warna teks pada kolom status (kolom index 6)
        // ────────────────────────────────────────────────────────────────────────────
        table.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(new Font("Dialog", Font.BOLD, 12));
                String status = (String) value;
                if (status != null) {
                    // 📌 Warna berdasarkan status
                    if (status.contains("Success")) {
                        c.setForeground(STATUS_SUCCESS);      // 🟢 Hijau
                    } else if (status.contains("Pending")) {
                        c.setForeground(STATUS_PENDING);      // 🟠 Orange
                    } else if (status.contains("Processing")) {
                        c.setForeground(STATUS_PROCESSING);    // 🔵 Biru
                    } else if (status.contains("Failed")) {
                        c.setForeground(STATUS_FAILED);        // 🔴 Merah
                    } else {
                        c.setForeground(Color.BLACK);
                    }
                }
                setHorizontalAlignment(SwingConstants.CENTER);
                
                // 📌 Background baris (selang-seling)
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(TABLE_ROW_EVEN);
                    } else {
                        c.setBackground(TABLE_ROW_ODD);
                    }
                }
                return c;
            }
        });
    }
}