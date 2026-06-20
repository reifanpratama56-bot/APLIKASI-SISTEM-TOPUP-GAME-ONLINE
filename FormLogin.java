// ════════════════════════════════════════════════════════════════════════════════════
// 📌 FILE: FormLogin.java
// 📝 FUNGSI: Halaman login untuk autentikasi user sebelum masuk ke aplikasi
// 🔧 DAPAT DIUBAH: Warna tema, ukuran, teks, validasi login
// ════════════════════════════════════════════════════════════════════════════════════

// 📦 Package: form - tempat menyimpan class GUI/Form
package form;

// 📦 Mengimport library yang dibutuhkan
import model.User;               // 📌 Mengambil class User dari package model
import javax.swing.*;            // 📌 Komponen GUI Swing
import javax.swing.border.*;     // 📌 Untuk membuat border/garis tepi
import java.awt.*;               // 📌 Untuk warna, layout, graphics
import java.awt.event.*;         // 📌 Untuk menangani event (klik, key, dll)

// ════════════════════════════════════════════════════════════════════════════════════
// 📌 CLASS: FormLogin
// 📝 FUNGSI: Membuat halaman login untuk user
// 🔧 DAPAT DIUBAH: Warna tema, ukuran komponen, teks, validasi
// ════════════════════════════════════════════════════════════════════════════════════
public class FormLogin extends JFrame {
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 DEKLARASI KOMPONEN GUI
    // ──────────────────────────────────────────────────────────────────────────────
    
    // 📝 txtUsername: Field input untuk username
    private JTextField txtUsername;
    
    // 🔒 txtPassword: Field input untuk password (dienkripsi otomatis oleh JPasswordField)
    private JPasswordField txtPassword;
    
    // 🔘 btnLogin: Tombol untuk melakukan login
    private JButton btnLogin;
    
    // 🔘 btnRegister: Tombol untuk membuka form registrasi
    private JButton btnRegister;
    
    // 🔘 btnExit: Tombol untuk keluar aplikasi
    private JButton btnExit;
    
    // 📝 lblMessage: Label untuk menampilkan pesan error/success
    private JLabel lblMessage;
    
    // 👤 loggedInUser: Menyimpan data user yang berhasil login
    private User loggedInUser;
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 CONSTRUCTOR
    // 📝 FUNGSI: Dipanggil saat objek FormLogin dibuat
    // 🔧 DAPAT DIUBAH: Bisa ditambah parameter (misal: autoLogin)
    // ════════════════════════════════════════════════════════════════════════════════
    public FormLogin() {
        // 📌 Memanggil method untuk membuat komponen UI
        initComponents();
        
        // 📌 Memanggil method untuk menambahkan listener (event handler)
        setListeners();
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 SET FULLSCREEN
        // 📝 FUNGSI: Mengatur frame menjadi fullscreen
        // 🔧 GANTI: Bisa diubah ke setSize(w, h) untuk ukuran tertentu
        // ────────────────────────────────────────────────────────────────────────────
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // 📌 Menampilkan frame
        setVisible(true);
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: initComponents()
    // 📝 FUNGSI: Membuat dan mengatur semua komponen UI di halaman login
    // 🔧 DAPAT DIUBAH: Ukuran, warna, padding, layout
    // ════════════════════════════════════════════════════════════════════════════════
    private void initComponents() {
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 PENGATURAN FRAME (Jendela Utama)
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 GANTI: Judul yang muncul di title bar
        setTitle("Login - Topup Game Store");
        
        // 📌 GANTI: Perilaku saat tombol close ditekan
        // EXIT_ON_CLOSE → aplikasi berhenti total
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 📌 Meletakkan frame di tengah layar
        setLocationRelativeTo(null);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🎨 [DESIGN] - WARNA BACKGROUND
        // 📌 GANTI: Ubah warna background utama
        // 💡 Saat ini: (240, 248, 255) = Alice Blue
        // 💡 Contoh lain: (245, 247, 250) = Abu-abu terang
        // ────────────────────────────────────────────────────────────────────────────
        getContentPane().setBackground(new Color(240, 248, 255));
        
        // 📌 Layout utama menggunakan BorderLayout
        setLayout(new BorderLayout());
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 MAIN PANEL (Panel utama dengan scroll)
        // ────────────────────────────────────────────────────────────────────────────
        JPanel mainPanel = new JPanel();
        // 🔧 GANTI: Warna background main panel
        mainPanel.setBackground(new Color(240, 248, 255));
        
        // 📌 Layout vertikal (BoxLayout.Y_AXIS) → komponen disusun dari atas ke bawah
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        // 📌 Padding panel (atas, kiri, bawah, kanan)
        // 🔧 GANTI: Ubah angka padding
        // 💡 Saat ini: 80, 50, 80, 50
        mainPanel.setBorder(BorderFactory.createEmptyBorder(80, 50, 80, 50));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🎨 [DESIGN] - LOGO
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 GANTI: Emoji logo di sini
        JLabel lblLogo = new JLabel("🎮");
        // 📌 GANTI: Ukuran font logo
        lblLogo.setFont(new Font("Dialog", Font.PLAIN, 80));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblLogo);
        
        // 📌 Jarak vertikal 20px antara logo dan judul
        mainPanel.add(Box.createVerticalStrut(20));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🎨 [DESIGN] - JUDUL
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 GANTI: Teks judul
        JLabel lblTitle = new JLabel("TOPUP GAME STORE");
        // 📌 GANTI: Font judul
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 36));
        // 📌 GANTI: Warna judul
        lblTitle.setForeground(new Color(0, 51, 102));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);
        
        // 📌 Jarak vertikal 15px antara judul dan subtitle
        mainPanel.add(Box.createVerticalStrut(15));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🎨 [DESIGN] - SUBTITLE
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 GANTI: Teks subtitle
        JLabel lblSubTitle = new JLabel("Login to continue");
        // 📌 GANTI: Font subtitle
        lblSubTitle.setFont(new Font("Dialog", Font.PLAIN, 16));
        // 📌 GANTI: Warna subtitle
        lblSubTitle.setForeground(new Color(107, 114, 128));
        lblSubTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblSubTitle);
        
        // 📌 Jarak vertikal 50px antara subtitle dan card login
        mainPanel.add(Box.createVerticalStrut(50));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🎨 [DESIGN] - CARD LOGIN (Username & Password)
        // ────────────────────────────────────────────────────────────────────────────
        JPanel cardPanel = new JPanel();
        cardPanel.setBackground(Color.WHITE);
        
        // 📌 Border card login (garis tepi + padding)
        // 🔧 GANTI: Warna border, ketebalan, padding
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 149, 237), 3),
            BorderFactory.createEmptyBorder(35, 50, 35, 50)
        ));
        
        // 📌 Layout menggunakan GridBagLayout untuk fleksibilitas
        cardPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // 📌 Spacing antar komponen
        // 🔧 GANTI: Ubah angka spacing
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        
        int row = 0;
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 FIELD USERNAME
        // ────────────────────────────────────────────────────────────────────────────
        gbc.gridx = 0; gbc.gridy = row;
        
        // 📌 GANTI: Label username
        JLabel lblUser = new JLabel("👤 Username");
        // 📌 GANTI: Font label
        lblUser.setFont(new Font("Dialog", Font.BOLD, 16));
        cardPanel.add(lblUser, gbc);
        
        // 📌 Field input username
        txtUsername = new JTextField(30);  // 📌 GANTI: Ukuran field
        
        // 📌 GANTI: Font field
        txtUsername.setFont(new Font("Dialog", Font.PLAIN, 16));
        
        // 📌 Border field (garis tepi + padding)
        // 🔧 GANTI: Warna border
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 149, 237)),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        
        gbc.gridx = 1;
        cardPanel.add(txtUsername, gbc);
        row++;
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 FIELD PASSWORD
        // ────────────────────────────────────────────────────────────────────────────
        gbc.gridx = 0; gbc.gridy = row;
        
        // 📌 GANTI: Label password
        JLabel lblPass = new JLabel("🔒 Password");
        // 📌 GANTI: Font label
        lblPass.setFont(new Font("Dialog", Font.BOLD, 16));
        cardPanel.add(lblPass, gbc);
        
        // 📌 Field input password (JPasswordField = otomatis disembunyikan)
        txtPassword = new JPasswordField(30);
        
        // 📌 GANTI: Font field
        txtPassword.setFont(new Font("Dialog", Font.PLAIN, 16));
        
        // 📌 Border field (garis tepi + padding)
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 149, 237)),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        
        gbc.gridx = 1;
        cardPanel.add(txtPassword, gbc);
        row++;
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 CENTER WRAPPER (Agar card di tengah)
        // ────────────────────────────────────────────────────────────────────────────
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(new Color(240, 248, 255));
        centerWrapper.add(cardPanel);
        mainPanel.add(centerWrapper);
        mainPanel.add(Box.createVerticalStrut(30));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 PESAN ERROR / SUCCESS
        // ────────────────────────────────────────────────────────────────────────────
        lblMessage = new JLabel(" ");
        // 📌 GANTI: Font pesan
        lblMessage.setFont(new Font("Dialog", Font.PLAIN, 14));
        // 📌 GANTI: Warna pesan error
        lblMessage.setForeground(new Color(220, 20, 60));
        lblMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblMessage);
        
        mainPanel.add(Box.createVerticalStrut(15));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🔘 TOMBOL LOGIN
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 GANTI: Teks tombol
        btnLogin = new JButton("LOGIN");
        // 📌 GANTI: Warna background tombol
        btnLogin.setBackground(new Color(0, 191, 255));
        // 📌 GANTI: Warna teks tombol
        btnLogin.setForeground(Color.BLACK);
        // 📌 GANTI: Font tombol
        btnLogin.setFont(new Font("Dialog", Font.BOLD, 18));
        btnLogin.setFocusPainted(false);  // Hilangkan border focus
        // 📌 GANTI: Padding tombol
        btnLogin.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(btnLogin);
        
        mainPanel.add(Box.createVerticalStrut(20));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🔘 TOMBOL REGISTER & EXIT
        // ────────────────────────────────────────────────────────────────────────────
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        bottomPanel.setBackground(new Color(240, 248, 255));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🔘 TOMBOL REGISTER
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 GANTI: Teks tombol
        btnRegister = new JButton("📝 Register");
        // 📌 GANTI: Warna background tombol
        btnRegister.setBackground(new Color(16, 185, 129));
        // 📌 GANTI: Warna teks tombol
        btnRegister.setForeground(Color.BLACK);
        // 📌 GANTI: Font tombol
        btnRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnRegister.setFocusPainted(false);
        // 📌 GANTI: Padding tombol
        btnRegister.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🔘 TOMBOL EXIT
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 GANTI: Teks tombol
        btnExit = new JButton("❌ Exit");
        // 📌 GANTI: Warna background tombol
        btnExit.setBackground(new Color(220, 20, 60));
        // 📌 GANTI: Warna teks tombol
        btnExit.setForeground(Color.BLACK);
        // 📌 GANTI: Font tombol
        btnExit.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnExit.setFocusPainted(false);
        // 📌 GANTI: Padding tombol
        btnExit.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        bottomPanel.add(btnRegister);
        bottomPanel.add(btnExit);
        mainPanel.add(bottomPanel);
        
        mainPanel.add(Box.createVerticalStrut(40));
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 SCROLL PANE (Agar bisa scroll jika konten terlalu panjang)
        // ────────────────────────────────────────────────────────────────────────────
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: setListeners()
    // 📝 FUNGSI: Menambahkan event listener untuk tombol dan field
    // 🔧 DAPAT DIUBAH: Shortcut key, action tambahan
    // ════════════════════════════════════════════════════════════════════════════════
    private void setListeners() {
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🔘 TOMBOL LOGIN
        // ────────────────────────────────────────────────────────────────────────────
        btnLogin.addActionListener(e -> prosesLogin());
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🔘 TOMBOL REGISTER
        // ────────────────────────────────────────────────────────────────────────────
        btnRegister.addActionListener(e -> bukaFormRegister());
        
        // ────────────────────────────────────────────────────────────────────────────
        // 🔘 TOMBOL EXIT
        // ────────────────────────────────────────────────────────────────────────────
        btnExit.addActionListener(e -> System.exit(0));
        
        // ────────────────────────────────────────────────────────────────────────────
        // ⌨️ SHORTCUT ENTER (Login dengan menekan Enter di field password)
        // ────────────────────────────────────────────────────────────────────────────
        txtPassword.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                // 📌 Jika tombol yang ditekan adalah ENTER
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    prosesLogin();  // 📌 Jalankan proses login
                }
            }
        });
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: prosesLogin()
    // 📝 FUNGSI: Memproses login user
    // 🔧 DAPAT DIUBAH: Pesan error, validasi, redirect setelah login
    // ════════════════════════════════════════════════════════════════════════════════
    private void prosesLogin() {
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 AMBIL INPUT USER
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 trim() → Menghilangkan spasi di depan dan belakang
        String username = txtUsername.getText().trim();
        
        // 📌 Mengambil password dari JPasswordField (dalam bentuk char array)
        // 📌 new String(...) → Mengubah char array menjadi String
        String password = new String(txtPassword.getPassword()).trim();
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 VALIDASI INPUT
        // ────────────────────────────────────────────────────────────────────────────
        if (username.isEmpty() || password.isEmpty()) {
            // 📌 GANTI: Pesan error
            lblMessage.setText("❌ Username dan password tidak boleh kosong!");
            return;  // Hentikan proses
        }
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 PROSES LOGIN (Menggunakan class User)
        // ────────────────────────────────────────────────────────────────────────────
        User user = User.login(username, password);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 CEK HASIL LOGIN
        // ────────────────────────────────────────────────────────────────────────────
        if (user != null) {
            // ✅ LOGIN BERHASIL
            
            // 📌 GANTI: Pesan sukses
            lblMessage.setText("✅ Login berhasil! Selamat datang, " + user.getUsername());
            lblMessage.setForeground(new Color(16, 185, 129));  // 🟢 Warna hijau
            
            // 📌 Simpan user yang login
            loggedInUser = user;
            
            // ────────────────────────────────────────────────────────────────────────
            // ⏱️ DELAY SEBELUM PINDAH HALAMAN
            // ────────────────────────────────────────────────────────────────────────
            // 📌 GANTI: Ubah 1000 untuk mengubah durasi delay
            // 💡 1000 = 1 detik
            // ────────────────────────────────────────────────────────────────────────
            Timer timer = new Timer(1000, e -> {
                // 📌 Tutup halaman login
                dispose();
                
                // 📌 Buka halaman dashboard
                new FormDashboard(user);
            });
            timer.setRepeats(false);  // Hanya berjalan sekali
            timer.start();
            
        } else {
            // ❌ LOGIN GAGAL
            // 📌 GANTI: Pesan error
            lblMessage.setText("❌ Username atau password salah!");
        }
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: bukaFormRegister()
    // 📝 FUNGSI: Membuka dialog form registrasi
    // 🔧 DAPAT DIUBAH: Field registrasi, validasi
    // ════════════════════════════════════════════════════════════════════════════════
    private void bukaFormRegister() {
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 BUAT FIELD INPUT REGISTRASI
        // ────────────────────────────────────────────────────────────────────────────
        JTextField txtRegUsername = new JTextField(15);
        JTextField txtRegEmail = new JTextField(15);
        JPasswordField txtRegPassword = new JPasswordField(15);
        JPasswordField txtRegConfirm = new JPasswordField(15);
        JTextField txtRegNoHp = new JTextField(15);
        
        // ➕ [TAMBAH] - Bisa tambah field baru di sini:
        // JTextField txtRegAlamat = new JTextField(15);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 BUAT PANEL REGISTRASI
        // ────────────────────────────────────────────────────────────────────────────
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        
        // 📌 Tambahkan field ke panel
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1; panel.add(txtRegUsername, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; panel.add(txtRegEmail, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; panel.add(txtRegPassword, gbc);
        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Konfirmasi Password:"), gbc);
        gbc.gridx = 1; panel.add(txtRegConfirm, gbc);
        gbc.gridx = 0; gbc.gridy = 4; panel.add(new JLabel("No. HP:"), gbc);
        gbc.gridx = 1; panel.add(txtRegNoHp, gbc);
        
        // ➕ [TAMBAH] - Tambah field baru ke panel:
        // gbc.gridx = 0; gbc.gridy = 5; panel.add(new JLabel("Alamat:"), gbc);
        // gbc.gridx = 1; panel.add(txtRegAlamat, gbc);
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 TAMPILKAN DIALOG
        // ────────────────────────────────────────────────────────────────────────────
        int result = JOptionPane.showConfirmDialog(
            this,                       // Parent component
            panel,                      // Panel yang ditampilkan
            "Form Register",            // Judul dialog
            JOptionPane.OK_CANCEL_OPTION,  // Tombol OK & Cancel
            JOptionPane.PLAIN_MESSAGE   // Tipe icon (tanpa icon)
        );
        
        // ────────────────────────────────────────────────────────────────────────────
        // 📌 PROSES REGISTRASI (Jika user klik OK)
        // ────────────────────────────────────────────────────────────────────────────
        if (result == JOptionPane.OK_OPTION) {
            
            // 📌 Ambil input user
            String username = txtRegUsername.getText().trim();
            String email = txtRegEmail.getText().trim();
            String password = new String(txtRegPassword.getPassword()).trim();
            String confirm = new String(txtRegConfirm.getPassword()).trim();
            String noHp = txtRegNoHp.getText().trim();
            
            // ✅ [TAMBAH] - Validasi untuk field baru:
            // String alamat = txtRegAlamat.getText().trim();
            
            // ────────────────────────────────────────────────────────────────────────
            // 📌 VALIDASI INPUT
            // ────────────────────────────────────────────────────────────────────────
            // 1️⃣ Cek username & password tidak kosong
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username dan password harus diisi!");
                return;
            }
            
            // 2️⃣ Cek password sama dengan konfirmasi
            if (!password.equals(confirm)) {
                JOptionPane.showMessageDialog(this, "Password dan konfirmasi password tidak cocok!");
                return;
            }
            
            // 3️⃣ Cek username sudah terdaftar
            if (User.isUsernameExist(username)) {
                JOptionPane.showMessageDialog(this, "Username sudah terdaftar!");
                return;
            }
            
            // ────────────────────────────────────────────────────────────────────────
            // 📌 SIMPAN USER BARU
            // ────────────────────────────────────────────────────────────────────────
            // 📌 GANTI: Role bisa diubah (member, admin, premium)
            User newUser = new User(username, email, password, noHp, "member", 0);
            User.register(newUser);
            
            // ────────────────────────────────────────────────────────────────────────
            // 📌 NOTIFIKASI SUKSES
            // ────────────────────────────────────────────────────────────────────────
            JOptionPane.showMessageDialog(this, "✅ Registrasi berhasil! Silakan login.");
        }
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: getLoggedInUser()
    // 📝 FUNGSI: Mengambil data user yang sedang login
    // ════════════════════════════════════════════════════════════════════════════════
    public User getLoggedInUser() {
        return loggedInUser;
    }
}