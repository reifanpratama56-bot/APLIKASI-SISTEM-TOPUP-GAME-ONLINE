// ════════════════════════════════════════════════════════════════════════════════════
// 📌 FILE: SplashScreen.java
// 📝 FUNGSI: Menampilkan halaman loading / splash screen saat aplikasi pertama dibuka
// 🔧 DAPAT DIUBAH: Ukuran, warna, logo, kecepatan loading, teks
// ════════════════════════════════════════════════════════════════════════════════════

// 📦 Package: form - tempat menyimpan class GUI/Form
package form;

// 📦 Mengimport library yang dibutuhkan
import javax.swing.*;        // 📌 Komponen GUI Swing (JFrame, JLabel, JProgressBar, dll)
import java.awt.*;           // 📌 Untuk warna, layout, graphics

// ════════════════════════════════════════════════════════════════════════════════════
// 📌 CLASS: SplashScreen
// 📝 FUNGSI: Membuat halaman loading yang muncul pertama kali
// 🔧 DAPAT DIUBAH: Ukuran, warna, logo, kecepatan loading, teks
// ════════════════════════════════════════════════════════════════════════════════════
public class SplashScreen extends JFrame {
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 DEKLARASI KOMPONEN
    // ──────────────────────────────────────────────────────────────────────────────
    
    // 📊 progressBar: Progress bar untuk animasi loading (0% → 100%)
    // 📌 GANTI: Warna, ukuran, font progress bar di initComponents()
    private JProgressBar progressBar;
    
    // 📝 lblLoading: Label teks loading yang menampilkan persentase
    // 📌 GANTI: Teks, font, warna di initComponents()
    private JLabel lblLoading;
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 CONSTRUCTOR
    // 📝 FUNGSI: Dipanggil saat objek SplashScreen dibuat
    // 🔧 DAPAT DIUBAH: Bisa ditambah parameter (misal: logoPath, warnaTheme)
    // ════════════════════════════════════════════════════════════════════════════════
    public SplashScreen() {
        // 📌 Memanggil method untuk membuat komponen UI
        initComponents();
        
        // 📌 Memanggil method untuk memulai animasi loading
        startLoading();
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: initComponents()
    // 📝 FUNGSI: Membuat dan mengatur semua komponen UI di splash screen
    // 🔧 DAPAT DIUBAH: Ukuran, warna, logo, font, padding
    // ════════════════════════════════════════════════════════════════════════════════
    private void initComponents() {
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 PENGATURAN FRAME (Jendela Utama)
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 GANTI: Judul aplikasi yang muncul di title bar
        setTitle("Topup Game Store");
        
        // 📌 GANTI: Ukuran splash screen (width x height)
        // 💡 Saat ini: 700 x 450 pixel
        setSize(1000, 500);
        
        // 📌 Perilaku saat tombol close ditekan (EXIT_ON_CLOSE = aplikasi berhenti)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 📌 Meletakkan frame di tengah layar
        setLocationRelativeTo(null);
        
        // 📌 Menghilangkan border/dekorasi window (fullscreen effect)
        setUndecorated(true);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🎨 [DESIGN] - WARNA BACKGROUND
        // 📌 GANTI: Ubah warna background utama di sini
        // 💡 Saat ini: Indigo (99, 102, 241)
        // 💡 Contoh warna lain: 
        //   - new Color(0, 102, 204) → Biru
        //   - new Color(16, 185, 129) → Hijau
        //   - new Color(139, 92, 246) → Ungu
        //   - new Color(236, 72, 153) → Pink
        // ────────────────────────────────────────────────────────────────────────────
        getContentPane().setBackground(new Color(236, 72, 153));
        
        // 📌 Layout utama menggunakan BorderLayout
        setLayout(new BorderLayout());
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🎨 [DESIGN] - MAIN PANEL
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 Panel utama dengan warna yang sama dengan background
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(236, 72, 153));
        mainPanel.setLayout(new BorderLayout());
        
        // 📌 Padding panel (atas, kiri, bawah, kanan)
        // 🔧 GANTI: Ubah angka padding
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🎨 [DESIGN] - CENTER PANEL (Logo & Judul)
        // ────────────────────────────────────────────────────────────────────────────
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(236, 72, 153));
        // 📌 Layout vertikal (BoxLayout.Y_AXIS) → komponen disusun dari atas ke bawah
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🎨 [DESIGN] - LOGO
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 GANTI: Emoji logo di sini
        // 💡 Saat ini: "🎮" (Game controller)
        // 💡 Contoh lain: "🕹️", "⭐", "🔥", "💎", "🚀"
        JLabel lblLogo = new JLabel("🎮");
        
        // 📌 GANTI: Ukuran font logo
        // 💡 Saat ini: 100px
        lblLogo.setFont(new Font("Dialog", Font.PLAIN, 100));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(lblLogo);
        
        // 📌 Jarak vertikal 15px antara logo dan judul
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🎨 [DESIGN] - JUDUL
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 GANTI: Teks judul aplikasi
        JLabel lblTitle = new JLabel("TOPUP GAME STORE");
        
        // 📌 GANTI: Font judul (BOLD, ukuran)
        // 💡 Saat ini: Dialog, Bold, 32px
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 32));
        
        // 📌 GANTI: Warna judul
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(lblTitle);
        
        // 📌 Jarak vertikal 10px antara judul dan subtitle
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🎨 [DESIGN] - SUBTITLE
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 GANTI: Teks subtitle
        JLabel lblSubtitle = new JLabel("Topup Diamond & Item Game Favoritmu");
        
        // 📌 GANTI: Font subtitle
        // 💡 Saat ini: Dialog, Plain, 14px
        lblSubtitle.setFont(new Font("Dialog", Font.PLAIN, 14));
        
        // 📌 GANTI: Warna subtitle
        // 💡 Saat ini: (220, 220, 255) - Biru sangat muda
        lblSubtitle.setForeground(new Color(236, 72, 153));
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(lblSubtitle);
        
        // 📌 Menambahkan centerPanel ke mainPanel di posisi CENTER
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🎨 [DESIGN] - SOUTH PANEL (Progress Bar & Footer)
        // ────────────────────────────────────────────────────────────────────────────
        JPanel southPanel = new JPanel();
        southPanel.setBackground(new Color(236, 72, 153));
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        
        // 📌 Padding panel bawah (atas, kiri, bawah, kanan)
        // 🔧 GANTI: Ubah padding
        southPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 30, 30));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📊 [DESIGN] - PROGRESS BAR
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 Progress bar dengan range 0 - 100
        progressBar = new JProgressBar(0, 100);
        
        // 📌 Menampilkan teks persentase di dalam progress bar
        progressBar.setStringPainted(true);
        
        // 📌 GANTI: Warna isi progress bar
        progressBar.setForeground(Color.GREEN);
        
        // 📌 GANTI: Warna background progress bar
        progressBar.setBackground(new Color(236, 72, 153));
        
        // 📌 GANTI: Font teks progress bar
        progressBar.setFont(new Font("Dialog", Font.BOLD, 13));
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // 📌 GANTI: Ukuran progress bar (width x height)
        // 💡 Saat ini: 400 x 25 pixel
        progressBar.setPreferredSize(new Dimension(400, 25));
        southPanel.add(progressBar);
        
        // 📌 Jarak vertikal 12px antara progress bar dan teks loading
        southPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📝 [DESIGN] - TEKS LOADING
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 GANTI: Teks loading (akan berubah jadi "Loading... X%")
        lblLoading = new JLabel("Loading... 0%");
        
        // 📌 GANTI: Font teks loading
        lblLoading.setFont(new Font("Dialog", Font.PLAIN, 13));
        
        // 📌 GANTI: Warna teks loading
        lblLoading.setForeground(new Color(220, 220, 255));
        lblLoading.setAlignmentX(Component.CENTER_ALIGNMENT);
        southPanel.add(lblLoading);
        
        // 📌 Jarak vertikal 20px antara teks loading dan footer
        southPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 [DESIGN] - FOOTER
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 GANTI: Teks footer (tahun dan nama)
        // 💡 Saat ini: "© 2026 Topup Game Store"
        JLabel lblFooter = new JLabel("© 2026 Topup Game Store");
        
        // 📌 GANTI: Font footer
        lblFooter.setFont(new Font("Dialog", Font.PLAIN, 11));
        
        // 📌 GANTI: Warna footer
        lblFooter.setForeground(Color.WHITE);
        lblFooter.setAlignmentX(Component.CENTER_ALIGNMENT);
        southPanel.add(lblFooter);
        
        // 📌 Menambahkan southPanel ke mainPanel di posisi SOUTH
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        
        // 📌 Menambahkan mainPanel ke frame
        add(mainPanel, BorderLayout.CENTER);
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: startLoading()
    // 📝 FUNGSI: Menjalankan animasi progress bar loading
    // 🔧 DAPAT DIUBAH: Kecepatan loading (delay timer)
    // ════════════════════════════════════════════════════════════════════════════════
    private void startLoading() {
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🔧 [KONFIG] - KECEPATAN LOADING
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 Timer akan menjalankan kode setiap interval tertentu (dalam milidetik)
        // 📌 GANTI: Ubah angka 30 untuk mengatur kecepatan loading
        // ────────────────────────────────────────────────────────────────────────────
        // 💡 Contoh kecepatan:
        // - 10 = sangat cepat (~1 detik)
        // - 30 = cepat (~3 detik) → SAAT INI
        // - 50 = sedang (~5 detik)
        // - 100 = lambat (~10 detik)
        // - 200 = sangat lambat (~20 detik)
        // ────────────────────────────────────────────────────────────────────────────
        Timer timer = new Timer(30, e -> {  // 📌 GANTI: Ubah angka 30
            
            // 📌 Mengambil nilai progress bar saat ini
            int value = progressBar.getValue();
            
            if (value < 100) {
                // 📌 Menambah progress 1 setiap interval
                progressBar.setValue(value + 1);
                
                // 📌 Mengupdate teks loading dengan persentase baru
                lblLoading.setText("Loading... " + (value + 1) + "%");
            } else {
                // 📌 Jika progress sudah 100%, hentikan timer
                ((Timer) e.getSource()).stop();
                
                // 📌 Buka halaman login
                openLoginForm();
            }
        });
        
        // 📌 Memulai timer
        timer.start();
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: openLoginForm()
    // 📝 FUNGSI: Menutup splash screen dan membuka halaman login
    // 🔧 DAPAT DIUBAH: Ganti form tujuan setelah loading
    // ════════════════════════════════════════════════════════════════════════════════
    private void openLoginForm() {
        // 📌 Menutup splash screen (dispose = tutup dan bebaskan memory)
        dispose();
        
        // 📌 Membuka halaman login
        // 🔧 GANTI: Bisa diganti dengan form lain
        // 💡 Contoh: new FormDashboard(user) → langsung ke dashboard
        // 💡 Contoh: new FormTopup(user) → langsung ke topup
        new FormLogin().setVisible(true);
    }
}