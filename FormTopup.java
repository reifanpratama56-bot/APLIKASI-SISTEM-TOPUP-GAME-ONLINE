// ════════════════════════════════════════════════════════════════════════════════════
// 📌 FILE: FormTopup.java
// 📝 FUNGSI: Halaman utama untuk melakukan topup game
// 🔧 DAPAT DIUBAH: Warna tema, daftar game, nominal, metode pembayaran
// ════════════════════════════════════════════════════════════════════════════════════

package form;

import model.*;
import utils.ImageLoader;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class FormTopup extends JFrame {
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 🎨 WARNA TEMA
    // ──────────────────────────────────────────────────────────────────────────────
    private final Color COLOR_PRIMARY = new Color(0, 102, 204);       // 🔵 Warna utama
    private final Color COLOR_SECONDARY = new Color(16, 185, 129);    // 🟢 Warna sekunder
    private final Color COLOR_ACCENT = new Color(245, 158, 11);       // 🟡 Warna aksen
    private final Color COLOR_DANGER = new Color(239, 68, 68);        // 🔴 Warna danger
    private final Color COLOR_BG = new Color(0, 102, 204);            // 🔵 Background atas
    private final Color COLOR_BG_DARK = new Color(0, 51, 102);        // 🔵 Background bawah
    private final Color COLOR_CARD = new Color(224, 240, 255);        // 🔵 Background card
    private final Color COLOR_TEXT = new Color(0, 51, 102);           // 🔵 Warna teks
    private final Color COLOR_TEXT_MUTED = new Color(70, 130, 200);   // 🔵 Teks muted
    private final Color COLOR_BORDER = new Color(100, 149, 237);      // 🔵 Warna border
    private final Color COLOR_HOVER = new Color(200, 225, 255);       // 🔵 Warna hover
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 DEKLARASI KOMPONEN
    // ──────────────────────────────────────────────────────────────────────────────
    private JPanel gameGridPanel, nominalGridPanel;
    private JTextField txtUserId, txtEmail, txtPromoCode;
    private JTextArea txtRingkasan;
    private JButton btnBayar, btnReset, btnApplyPromo;
    private JLabel lblTotalBayar;
    private JProgressBar progressBar;
    
    private Game selectedGame;
    private String selectedNominal;
    private int selectedPrice;
    private PaymentMethod selectedMetode;
    private JPanel selectedNominalCard = null;
    private JPanel selectedMetodeCard = null;
    private JPanel selectedGameCard = null;
    private JTextField txtSearchField;
    
    private User loggedInUser;
    private int appliedDiscount = 0;
    private String appliedPromoCode = null;
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 CONSTRUCTOR
    // ════════════════════════════════════════════════════════════════════════════════
    public FormTopup(User user) {
        this.loggedInUser = user;
        UIManager.put("OptionPane.yesButtonText", "Ya");
        UIManager.put("OptionPane.noButtonText", "Tidak");
        initComponents();
        setListeners();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: initComponents()
    // 📝 FUNGSI: Membuat semua komponen UI
    // ════════════════════════════════════════════════════════════════════════════════
    private void initComponents() {
        // 📌 Pengaturan frame
        setTitle("Topup Game Store - Topup Diamond Termurah");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COLOR_BG);
        
        // 📌 Main panel dengan gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, COLOR_BG, getWidth(), getHeight(), COLOR_BG_DARK);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.CENTER;
        
        // 📌 Content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(COLOR_CARD);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setMaximumSize(new Dimension(1200, 1200));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        
        // 📌 Tambahkan semua section
        contentPanel.add(createHeader());
        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(createHeroBanner());
        contentPanel.add(Box.createVerticalStrut(25));
        contentPanel.add(createSectionTitle("🎮 Pilih Game", "Pilih game favoritmu untuk topup"));
        contentPanel.add(Box.createVerticalStrut(12));
        contentPanel.add(createGameSection());
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(createSectionTitle("💰 Pilih Nominal Topup", "Pilih jumlah diamond atau item yang ingin dibeli"));
        contentPanel.add(Box.createVerticalStrut(12));
        contentPanel.add(createNominalSection());
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(createSectionTitle("💳 Metode Pembayaran", "Pilih metode pembayaran yang tersedia"));
        contentPanel.add(Box.createVerticalStrut(12));
        contentPanel.add(createPaymentSection());
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(createFormPembeli());
        contentPanel.add(Box.createVerticalStrut(20));
        
        // 📌 Bottom panel (Ringkasan & Action)
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        bottomPanel.setBackground(COLOR_CARD);
        bottomPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.setMaximumSize(new Dimension(1200, 400));
        bottomPanel.add(createRingkasanPanel());
        bottomPanel.add(createActionPanel());
        
        contentPanel.add(bottomPanel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(createFooter());
        
        mainPanel.add(contentPanel, gbc);
        
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: createHeader()
    // 📝 FUNGSI: Membuat header (poin + judul + tombol kembali)
    // ════════════════════════════════════════════════════════════════════════════════
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(HEADER_BG);
        header.setMaximumSize(new Dimension(1200, 60));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // 📌 Kiri: Poin user
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        leftPanel.setBackground(HEADER_BG);
        JLabel balance = new JLabel("⭐ " + loggedInUser.getPoin() + " Poin");
        balance.setFont(new Font("Dialog", Font.BOLD, 13));
        balance.setForeground(COLOR_TEXT);
        balance.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDER),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        leftPanel.add(balance);
        
        // 📌 Kanan: Tombol kembali
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightPanel.setBackground(HEADER_BG);
        JButton btnBack = new JButton("← Kembali ke Dashboard");
        btnBack.setFont(new Font("Dialog", Font.BOLD, 12));
        btnBack.setBackground(new Color(255, 255, 255));
        btnBack.setForeground(COLOR_PRIMARY);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_PRIMARY, 1),
            BorderFactory.createEmptyBorder(8, 18, 8, 18)
        ));
        // Efek hover
        btnBack.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnBack.setBackground(COLOR_PRIMARY);
                btnBack.setForeground(Color.WHITE);
            }
            public void mouseExited(MouseEvent e) {
                btnBack.setBackground(Color.WHITE);
                btnBack.setForeground(COLOR_PRIMARY);
            }
        });
        btnBack.addActionListener(e -> {
            dispose();
            new FormDashboard(loggedInUser);
        });
        rightPanel.add(btnBack);
        
        // 📌 Tengah: Judul
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(HEADER_BG);
        JLabel title = new JLabel("🎮 TOPUP GAME STORE");
        title.setFont(new Font("Dialog", Font.BOLD, 24));
        title.setForeground(COLOR_PRIMARY);
        centerPanel.add(title);
        
        header.add(leftPanel, BorderLayout.WEST);
        header.add(centerPanel, BorderLayout.CENTER);
        header.add(rightPanel, BorderLayout.EAST);
        
        return header;
    }
    private final Color HEADER_BG = new Color(200, 225, 255);
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: createHeroBanner()
    // 📝 FUNGSI: Membuat banner promosi
    // ════════════════════════════════════════════════════════════════════════════════
    private JPanel createHeroBanner() {
        JPanel hero = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, COLOR_PRIMARY, getWidth(), 0, new Color(0, 153, 255));
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        hero.setLayout(new BorderLayout());
        hero.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        hero.setPreferredSize(new Dimension(0, 110));
        hero.setMaximumSize(new Dimension(1200, 120));
        
        // 📌 Teks utama di tengah
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        
        JLabel textMain = new JLabel("✨ Topup Diamond Termurah");
        textMain.setFont(new Font("Dialog", Font.BOLD, 22));
        textMain.setForeground(Color.WHITE);
        textMain.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel textSub = new JLabel("Proses cepat • 100% aman • Langsung masuk ke akun");
        textSub.setFont(new Font("Dialog", Font.PLAIN, 13));
        textSub.setForeground(new Color(230, 240, 255));
        textSub.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        textPanel.add(textMain);
        textPanel.add(Box.createVerticalStrut(10));
        textPanel.add(textSub);
        
        centerPanel.add(textPanel, gbc);
        
        // 📌 Badge di kanan
        JLabel badge = new JLabel("⚡ PROSES 1-5 MENIT ⚡");
        badge.setFont(new Font("Dialog", Font.BOLD, 11));
        badge.setForeground(COLOR_ACCENT);
        badge.setBackground(new Color(255, 245, 200));
        badge.setOpaque(true);
        badge.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        
        hero.add(centerPanel, BorderLayout.CENTER);
        hero.add(badge, BorderLayout.EAST);
        
        return hero;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: createSectionTitle()
    // 📝 FUNGSI: Membuat judul section
    // ════════════════════════════════════════════════════════════════════════════════
    private JPanel createSectionTitle(String title, String subtitle) {
        JPanel panel = new JPanel();
        panel.setBackground(COLOR_CARD);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMaximumSize(new Dimension(1200, 60));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        titleLabel.setForeground(COLOR_TEXT);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        subtitleLabel.setForeground(COLOR_TEXT_MUTED);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(subtitleLabel);
        
        return panel;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: createGameSection()
    // 📝 FUNGSI: Membuat grid pilihan game
    // 🔧 TAMBAH: Game baru di array games[]
    // ════════════════════════════════════════════════════════════════════════════════
    private JPanel createGameSection() {
        JPanel card = createCardPanel();
        card.setLayout(new BorderLayout());
        card.setMaximumSize(new Dimension(1200, 380));
        
        // 📌 Search bar
        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDER),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        JLabel searchIcon = new JLabel("🔍");
        txtSearchField = new JTextField();
        txtSearchField.setBorder(null);
        txtSearchField.setFont(new Font("Dialog", Font.PLAIN, 13));
        searchPanel.add(searchIcon, BorderLayout.WEST);
        searchPanel.add(txtSearchField, BorderLayout.CENTER);
        card.add(searchPanel, BorderLayout.NORTH);
        card.add(Box.createVerticalStrut(15), BorderLayout.CENTER);
        
        // 📌 Grid game
        gameGridPanel = new JPanel(new GridLayout(2, 8, 10, 10));
        gameGridPanel.setBackground(COLOR_CARD);
        gameGridPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        
        // 📌 Daftar game (16 game)
        // ➕ TAMBAH: Tambah game baru di sini
        GameData[] games = {
            new GameData("Mobile Legends", "MLBB", "📱", "Moonton"),
            new GameData("Magic Chess Go Go", "MCGG", "🌍", "Moonton"),
            new GameData("ROBLOX", "Roblox", "🤖", "Roblox Corporation"),
            new GameData("Free Fire", "FF", "🔫", "Garena"),
            new GameData("PUBG Mobile", "PUBG", "🪂", "Tencent Games"),
            new GameData("Genshin Impact", "Genshin", "⚜️", "HoYoverse"),
            new GameData("Honor of Kings", "HOK", "👑", "Tencent Games"),
            new GameData("Steam Wallet", "Steam", "🎮", "Valve"),
            new GameData("Valorant", "Valorant", "🎯", "Riot Games"),
            new GameData("Clash of Clans", "COC", "🏰", "Supercell"),
            new GameData("Call of Duty Mobile", "CODM", "🔫", "Activision"),
            new GameData("Dota 2", "Dota2", "⚔️", "Valve"),
            new GameData("Arena of Valor", "AOV", "🎭", "Tencent"),
            new GameData("Wild Rift", "Wild Rift", "🐺", "Riot Games"),
            new GameData("Among Us", "Among", "👽", "Innersloth"),
            new GameData("Brawl Stars", "Brawl", "⭐", "Supercell"),
            
        };
        
        for (GameData g : games) {
            gameGridPanel.add(createGameCard(g));
        }
        card.add(gameGridPanel, BorderLayout.CENTER);
        
        // 📌 Search filter
        txtSearchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                filterGames(txtSearchField.getText());
            }
        });
        
        return card;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 INNER CLASS: GameData
    // 📝 FUNGSI: Menyimpan data game untuk ditampilkan di grid
    // ════════════════════════════════════════════════════════════════════════════════
    private class GameData {
        String name, shortName, icon, publisher;
        GameData(String name, String shortName, String icon, String publisher) {
            this.name = name; 
            this.shortName = shortName; 
            this.icon = icon; 
            this.publisher = publisher;
        }
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: filterGames()
    // 📝 FUNGSI: Filter game berdasarkan keyword pencarian
    // ════════════════════════════════════════════════════════════════════════════════
    private void filterGames(String keyword) {
        gameGridPanel.removeAll();
        GameData[] allGames = {
            new GameData("Mobile Legends","MLBB", "📱", "Moonton"),
            new GameData("Magic Chess Go Go", "MCGG", "🌍", "Moonton"),
            new GameData("ROBLOX", "Roblox", "🤖", "Roblox Corporation"),
            new GameData("Free Fire", "FF", "🔫", "Garena"),
            new GameData("PUBG Mobile", "PUBG", "🪂", "Tencent Games"),
            new GameData("Genshin Impact", "Genshin", "⚜️", "HoYoverse"),
            new GameData("Honor of Kings", "HOK", "👑", "Tencent Games"),
            new GameData("Steam Wallet", "Steam", "🎮", "Valve"),
            new GameData("Valorant", "Valorant", "🎯", "Riot Games"),
            new GameData("Clash of Clans", "COC", "🏰", "Supercell"),
            new GameData("Call of Duty Mobile", "CODM", "🔫", "Activision"),
            new GameData("Dota 2", "Dota2", "⚔️", "Valve"),
            new GameData("Arena of Valor", "AOV", "🎭", "Tencent"),
            new GameData("Wild Rift", "Wild Rift", "🐺", "Riot Games"),
            new GameData("Among Us", "Among", "👽", "Innersloth"),
            new GameData("Brawl Stars", "Brawl", "⭐", "Supercell"),
            
        };
        
        for (GameData g : allGames) {
            if (keyword.isEmpty() || g.name.toLowerCase().contains(keyword.toLowerCase()) ||
                g.shortName.toLowerCase().contains(keyword.toLowerCase())) {
                gameGridPanel.add(createGameCard(g));
            }
        }
        gameGridPanel.revalidate();
        gameGridPanel.repaint();
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: createGameCard()
    // 📝 FUNGSI: Membuat card untuk satu game dengan logo gambar
    // ════════════════════════════════════════════════════════════════════════════════
    private JPanel createGameCard(GameData game) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDER),
            BorderFactory.createEmptyBorder(10, 8, 10, 8)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // 📌 Logo game dari ImageLoader
        String imageFile = getImageFileName(game.shortName);
        ImageIcon gameIcon = ImageLoader.loadGameIcon(imageFile, 55, 55);
        JLabel iconLabel = new JLabel(gameIcon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel nameLabel = new JLabel(game.shortName);
        nameLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        nameLabel.setForeground(COLOR_TEXT);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel publisherLabel = new JLabel(game.publisher);
        publisherLabel.setFont(new Font("Dialog", Font.PLAIN, 8));
        publisherLabel.setForeground(COLOR_TEXT_MUTED);
        publisherLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        card.add(iconLabel);
        card.add(Box.createVerticalStrut(6));
        card.add(nameLabel);
        card.add(Box.createVerticalStrut(2));
        card.add(publisherLabel);
        
        // 📌 Event mouse (klik, hover)
        card.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectGame(game.name);
                if (selectedGameCard != null) {
                    selectedGameCard.setBackground(Color.WHITE);
                    selectedGameCard.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(COLOR_BORDER),
                        BorderFactory.createEmptyBorder(10, 8, 10, 8)
                    ));
                }
                selectedGameCard = card;
                card.setBackground(COLOR_HOVER);
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(COLOR_PRIMARY, 2),
                    BorderFactory.createEmptyBorder(10, 8, 10, 8)
                ));
            }
            public void mouseEntered(MouseEvent e) {
                if (card != selectedGameCard) {
                    card.setBackground(COLOR_HOVER);
                    card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(COLOR_PRIMARY),
                        BorderFactory.createEmptyBorder(10, 8, 10, 8)
                    ));
                }
            }
            public void mouseExited(MouseEvent e) {
                if (card != selectedGameCard) {
                    card.setBackground(Color.WHITE);
                    card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(COLOR_BORDER),
                        BorderFactory.createEmptyBorder(10, 8, 10, 8)
                    ));
                }
            }
        });
        
        return card;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: getImageFileName()
    // 📝 FUNGSI: Mapping nama game ke file gambar
    // 🔧 TAMBAH: Game baru di sini
    // ════════════════════════════════════════════════════════════════════════════════
    private String getImageFileName(String shortName) {
        switch (shortName.toLowerCase()) {
            case "mlbb": return "mlbb.png";
            case "mcgg": return "mcgg.png";
            case "roblox": return "roblox.png";
            case "ff": return "ff.png";
            case "pubg": return "pubg.png";
            case "genshin": return "genshin.png";
            case "hok": return "hok.png";
            case "steam": return "steam.png";
            case "valorant": return "valorant.png";
            case "coc": return "coc.png";
            case "codm": return "codm.png";
            case "dota2": return "dota2.png";
            case "aov": return "aov.png";
            case "wild rift": return "wildrift.png";
            case "among": return "among.png";
            case "brawl": return "brawl.png";
            default: return "default.png";
        }
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: getPaymentImageFileName()
    // 📝 FUNGSI: Mapping metode pembayaran ke file gambar
    // ════════════════════════════════════════════════════════════════════════════════
    private String getPaymentImageFileName(String paymentName) {
        switch (paymentName.toLowerCase()) {
            case "dana": return "dana.png";
            case "gopay": return "gopay.png";
            case "ovo": return "ovo.png";
            case "shopeepay": return "shopeepay.png";
            default: return "default.png";
        }
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: selectGame()
    // 📝 FUNGSI: Memproses pemilihan game
    // ════════════════════════════════════════════════════════════════════════════════
    private void selectGame(String gameName) {
        selectedGame = Game.getGameByNama(gameName);
        if (selectedGame == null) {
            java.util.LinkedHashMap<String, Integer> dummyNominal = new java.util.LinkedHashMap<>();
            dummyNominal.put("100 Diamond", 15000);
            dummyNominal.put("200 Diamond", 30000);
            dummyNominal.put("500 Diamond", 73000);
            dummyNominal.put("1000 Diamond", 145000);
            selectedGame = new Game(gameName, gameName, "Various", dummyNominal);
        }
        updateNominalGrid();
        updateRingkasan();
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: createNominalSection()
    // 📝 FUNGSI: Membuat section pilihan nominal
    // ════════════════════════════════════════════════════════════════════════════════
    private JPanel createNominalSection() {
        JPanel card = createCardPanel();
        card.setMaximumSize(new Dimension(1200, 220));
        nominalGridPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        nominalGridPanel.setBackground(COLOR_CARD);
        
        JLabel placeholder = new JLabel("Pilih game terlebih dahulu", SwingConstants.CENTER);
        placeholder.setForeground(COLOR_TEXT_MUTED);
        placeholder.setFont(new Font("Dialog", Font.PLAIN, 13));
        nominalGridPanel.add(placeholder);
        
        card.add(nominalGridPanel);
        return card;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: updateNominalGrid()
    // 📝 FUNGSI: Mengupdate grid nominal berdasarkan game yang dipilih
    // ════════════════════════════════════════════════════════════════════════════════
    private void updateNominalGrid() {
        nominalGridPanel.removeAll();
        nominalGridPanel.setLayout(new GridLayout(0, 5, 10, 10));
        
        if (selectedGame != null && selectedGame.getNominalTopup() != null && !selectedGame.getNominalTopup().isEmpty()) {
            for (Map.Entry<String, Integer> entry : selectedGame.getNominalTopup().entrySet()) {
                nominalGridPanel.add(createNominalCard(entry.getKey(), entry.getValue()));
            }
        } else {
            String[][] dummyNominal = {
                {"100 Diamond", "15000"}, {"200 Diamond", "30000"}, {"500 Diamond", "73000"},
                {"1000 Diamond", "145000"}, {"2000 Diamond", "285000"}, {"5000 Diamond", "710000"}
            };
            for (String[] nom : dummyNominal) {
                nominalGridPanel.add(createNominalCard(nom[0], Integer.parseInt(nom[1])));
            }
        }
        nominalGridPanel.revalidate();
        nominalGridPanel.repaint();
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: createNominalCard()
    // 📝 FUNGSI: Membuat card untuk satu nominal
    // 🔧 TAMBAH: Deteksi item type baru di sini
    // ════════════════════════════════════════════════════════════════════════════════
    private JPanel createNominalCard(String nominal, int price) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDER),
            BorderFactory.createEmptyBorder(12, 10, 12, 10)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        String amount = nominal.split(" ")[0];
        
        // 📌 Deteksi jenis item
        // 🔧 TAMBAH: Tambah else if untuk game baru
        String itemType;
        if (selectedGame != null && selectedGame.getNamaGame().equals("Call of Duty Mobile")) {
            itemType = "🔫 CP";
        } else if (nominal.contains("CP")) {
            itemType = "🔫 CP";
        } else if (nominal.contains("Diamond")) {
            itemType = "💎 Diamond";
        } else if (nominal.contains("UC")) {
            itemType = "🎯 UC";
        } else if (nominal.contains("Magic Crystals")) {
            itemType = "✨ Magic Crystal";
        } else if (nominal.contains("Genesis Crystal")) {
            itemType = "⚜️ Genesis Crystal";
        } else if (nominal.contains("Points")) {
            itemType = "👑 Points";
        } else if (nominal.contains("Robux")) {
            itemType = "🤖 Robux";
        } else if (nominal.contains("VP")) {
            itemType = "🎯 VP";
        } else if (nominal.contains("Gems")) {
            itemType = "💎 Gems";
        } else if (nominal.contains("Vouchers")) {
            itemType = "🎭 Vouchers";
        } else if (nominal.contains("Wild Cores")) {
            itemType = "🐺 Wild Cores";
        } else if (nominal.contains("Stars")) {
            itemType = "⭐ Stars";
        } else if (nominal.contains("Dota Plus")) {
            itemType = "⚔️ Dota Plus";
        } else {
            itemType = "📦 Item";
        }
        
        JLabel amountLabel = new JLabel(amount);
        amountLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        amountLabel.setForeground(COLOR_TEXT);
        amountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel itemLabel = new JLabel(itemType);
        itemLabel.setFont(new Font("Dialog", Font.PLAIN, 9));
        itemLabel.setForeground(COLOR_TEXT_MUTED);
        itemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel priceLabel = new JLabel(formatRupiah(price));
        priceLabel.setFont(new Font("Dialog", Font.BOLD, 12));
        priceLabel.setForeground(COLOR_PRIMARY);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        card.add(amountLabel);
        card.add(Box.createVerticalStrut(4));
        card.add(itemLabel);
        card.add(Box.createVerticalStrut(6));
        card.add(priceLabel);
        
        card.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (selectedNominalCard != null) {
                    selectedNominalCard.setBackground(Color.WHITE);
                    selectedNominalCard.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(COLOR_BORDER),
                        BorderFactory.createEmptyBorder(12, 10, 12, 10)
                    ));
                }
                selectedNominal = nominal;
                selectedPrice = price;
                selectedNominalCard = card;
                card.setBackground(COLOR_HOVER);
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(COLOR_PRIMARY, 2),
                    BorderFactory.createEmptyBorder(12, 10, 12, 10)
                ));
                updateRingkasan();
            }
            public void mouseEntered(MouseEvent e) {
                if (card != selectedNominalCard) {
                    card.setBackground(COLOR_HOVER);
                    card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(COLOR_PRIMARY),
                        BorderFactory.createEmptyBorder(12, 10, 12, 10)
                    ));
                }
            }
            public void mouseExited(MouseEvent e) {
                if (card != selectedNominalCard) {
                    card.setBackground(Color.WHITE);
                    card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(COLOR_BORDER),
                        BorderFactory.createEmptyBorder(12, 10, 12, 10)
                    ));
                }
            }
        });
        
        return card;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: createPaymentSection()
    // 📝 FUNGSI: Membuat section metode pembayaran (E-WALLET)
    // ════════════════════════════════════════════════════════════════════════════════
    private JPanel createPaymentSection() {
        JPanel card = createCardPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setMaximumSize(new Dimension(1200, 180));
        card.setPreferredSize(new Dimension(1100, 160));
        
        JLabel ewalletTitle = new JLabel("💳 E-WALLET");
        ewalletTitle.setFont(new Font("Dialog", Font.BOLD, 14));
        ewalletTitle.setForeground(COLOR_TEXT);
        ewalletTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(ewalletTitle);
        card.add(Box.createVerticalStrut(10));
        
        JPanel ewalletPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 8));
        ewalletPanel.setBackground(COLOR_CARD);
        
        // 📌 Daftar E-WALLET
        String[][] eWallets = {{"Dana", "💰"}, {"GoPay", "🐐"}, {"Ovo", "🟣"}, {"ShopeePay", "🛒"}};
        for (String[] ew : eWallets) {
            ewalletPanel.add(createPaymentCard(ew[0], ew[1]));
        }
        card.add(ewalletPanel);
        card.add(Box.createVerticalStrut(10));
        
        return card;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: createPaymentCard()
    // 📝 FUNGSI: Membuat card untuk satu metode pembayaran
    // ════════════════════════════════════════════════════════════════════════════════
    private JPanel createPaymentCard(String name, String icon) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(248, 250, 252));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDER),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.setPreferredSize(new Dimension(85, 75));
        
        // 📌 Logo payment dari ImageLoader
        String imageFile = getPaymentImageFileName(name);
        ImageIcon paymentIcon = ImageLoader.loadPaymentIcon(imageFile, 40, 40);
        JLabel iconLabel = new JLabel(paymentIcon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
        nameLabel.setForeground(COLOR_TEXT);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        card.add(iconLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(nameLabel);
        
        card.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (selectedMetodeCard != null) {
                    selectedMetodeCard.setBackground(new Color(248, 250, 252));
                    selectedMetodeCard.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(COLOR_BORDER),
                        BorderFactory.createEmptyBorder(8, 15, 8, 15)
                    ));
                }
                selectedMetode = PaymentMethod.getMetodeByNama(name);
                if (selectedMetode == null) {
                    selectedMetode = new PaymentMethod(name.toUpperCase(), name, 2500, 0, null, null);
                }
                selectedMetodeCard = card;
                card.setBackground(COLOR_HOVER);
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(COLOR_PRIMARY, 2),
                    BorderFactory.createEmptyBorder(8, 15, 8, 15)
                ));
                updateRingkasan();
            }
            public void mouseEntered(MouseEvent e) {
                if (card != selectedMetodeCard) {
                    card.setBackground(COLOR_HOVER);
                    card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(COLOR_PRIMARY),
                        BorderFactory.createEmptyBorder(8, 15, 8, 15)
                    ));
                }
            }
            public void mouseExited(MouseEvent e) {
                if (card != selectedMetodeCard) {
                    card.setBackground(new Color(248, 250, 252));
                    card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(COLOR_BORDER),
                        BorderFactory.createEmptyBorder(8, 15, 8, 15)
                    ));
                }
            }
        });
        
        return card;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: createFormPembeli()
    // 📝 FUNGSI: Membuat form input user (ID Game, Email, Kode Promo)
    // ════════════════════════════════════════════════════════════════════════════════
    private JPanel createFormPembeli() {
        JPanel card = createCardPanel();
        card.setMaximumSize(new Dimension(1200, 220));
        card.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // 📌 ID Game
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel idLabel = new JLabel("🆔 ID GAME");
        idLabel.setFont(new Font("Dialog", Font.BOLD, 13));
        idLabel.setForeground(COLOR_TEXT);
        card.add(idLabel, gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtUserId = new JTextField(25);
        txtUserId.setFont(new Font("Dialog", Font.PLAIN, 13));
        txtUserId.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDER),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        card.add(txtUserId, gbc);
        
        // 📌 Email
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        JLabel emailLabel = new JLabel("📧 EMAIL");
        emailLabel.setFont(new Font("Dialog", Font.BOLD, 13));
        emailLabel.setForeground(COLOR_TEXT);
        card.add(emailLabel, gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtEmail = new JTextField(25);
        txtEmail.setFont(new Font("Dialog", Font.PLAIN, 13));
        txtEmail.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDER),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        card.add(txtEmail, gbc);
        
        // 📌 Kode Promo
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel promoLabel = new JLabel("🎟️ KODE PROMO");
        promoLabel.setFont(new Font("Dialog", Font.BOLD, 13));
        promoLabel.setForeground(COLOR_TEXT);
        card.add(promoLabel, gbc);
        
        gbc.gridx = 1;
        JPanel promoInputPanel = new JPanel(new BorderLayout(10, 0));
        txtPromoCode = new JTextField();
        txtPromoCode.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDER),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        txtPromoCode.setFont(new Font("Dialog", Font.PLAIN, 13));
        
        btnApplyPromo = new JButton("APPLY");
        btnApplyPromo.setFont(new Font("Dialog", Font.BOLD, 13));
        btnApplyPromo.setBackground(new Color(16, 185, 129));
        btnApplyPromo.setForeground(Color.BLACK);
        btnApplyPromo.setFocusPainted(false);
        btnApplyPromo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnApplyPromo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        promoInputPanel.add(txtPromoCode, BorderLayout.CENTER);
        promoInputPanel.add(btnApplyPromo, BorderLayout.EAST);
        card.add(promoInputPanel, gbc);
        
        return card;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: createRingkasanPanel()
    // 📝 FUNGSI: Membuat panel ringkasan pembayaran
    // ════════════════════════════════════════════════════════════════════════════════
    private JPanel createRingkasanPanel() {
        JPanel card = createCardPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setMaximumSize(new Dimension(580, 400));
        
        JLabel title = new JLabel("📋 RINGKASAN PEMBAYARAN");
        title.setFont(new Font("Dialog", Font.BOLD, 14));
        title.setForeground(COLOR_TEXT);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(title);
        card.add(Box.createVerticalStrut(12));
        
        txtRingkasan = new JTextArea();
        txtRingkasan.setEditable(false);
        txtRingkasan.setFont(new Font("Dialog", Font.PLAIN, 12));
        txtRingkasan.setBackground(Color.WHITE);
        txtRingkasan.setForeground(COLOR_TEXT);
        txtRingkasan.setLineWrap(true);
        txtRingkasan.setWrapStyleWord(true);
        txtRingkasan.setText("Belum ada game yang dipilih");
        txtRingkasan.setRows(8);
        
        JScrollPane scroll = new JScrollPane(txtRingkasan);
        scroll.setBorder(null);
        scroll.setPreferredSize(new Dimension(520, 160));
        card.add(scroll);
        
        card.add(Box.createVerticalStrut(12));
        JSeparator sep = new JSeparator();
        sep.setForeground(COLOR_BORDER);
        card.add(sep);
        card.add(Box.createVerticalStrut(10));
        
        // 📌 Total Bayar
        JPanel totalPanel = new JPanel(new BorderLayout());
        totalPanel.setBackground(Color.WHITE);
        totalPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        JLabel totalLabel = new JLabel("💰 TOTAL BAYAR");
        totalLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        totalLabel.setForeground(COLOR_TEXT);
        lblTotalBayar = new JLabel("Rp 0");
        lblTotalBayar.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTotalBayar.setForeground(COLOR_PRIMARY);
        totalPanel.add(totalLabel, BorderLayout.WEST);
        totalPanel.add(lblTotalBayar, BorderLayout.EAST);
        card.add(totalPanel);
        
        card.add(Box.createVerticalStrut(12));
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setForeground(COLOR_SECONDARY);
        progressBar.setBackground(COLOR_BORDER);
        progressBar.setFont(new Font("Dialog", Font.BOLD, 10));
        progressBar.setString("Menunggu data lengkap");
        card.add(progressBar);
        
        return card;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: createActionPanel()
    // 📝 FUNGSI: Membuat panel tombol aksi (Bayar & Reset)
    // ════════════════════════════════════════════════════════════════════════════════
    private JPanel createActionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(COLOR_CARD);
        panel.setMaximumSize(new Dimension(580, 400));
        
        // 📌 Tombol Bayar
        btnBayar = new JButton("💳 BAYAR SEKARANG");
        btnBayar.setFont(new Font("Dialog", Font.BOLD, 16));
        btnBayar.setBackground(COLOR_PRIMARY);
        btnBayar.setForeground(Color.BLACK);
        btnBayar.setFocusPainted(false);
        btnBayar.setBorder(BorderFactory.createEmptyBorder(18, 30, 18, 30));
        btnBayar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBayar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBayar.setEnabled(false);
        
        // 📌 Tombol Reset
        btnReset = new JButton("🗑️ RESET FORM");
        btnReset.setFont(new Font("Dialog", Font.BOLD, 14));
        btnReset.setBackground(Color.WHITE);
        btnReset.setForeground(COLOR_DANGER);
        btnReset.setFocusPainted(false);
        btnReset.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_DANGER, 2),
            BorderFactory.createEmptyBorder(12, 25, 12, 25)
        ));
        btnReset.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReset.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        panel.add(btnBayar);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btnReset);
        panel.add(Box.createVerticalStrut(20));
        
        // 📌 Benefit Panel
        JPanel benefitPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        benefitPanel.setBackground(COLOR_CARD);
        benefitPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        benefitPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDER, 1),
            BorderFactory.createEmptyBorder(12, 20, 12, 20)
        ));
        
        JPanel benefit1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        benefit1.setBackground(COLOR_CARD);
        JLabel icon1 = new JLabel("⚡");
        icon1.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel text1 = new JLabel("Proses 1-5 menit");
        text1.setFont(new Font("Dialog", Font.BOLD, 12));
        text1.setForeground(COLOR_TEXT_MUTED);
        benefit1.add(icon1);
        benefit1.add(text1);
        
        JPanel benefit2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        benefit2.setBackground(COLOR_CARD);
        JLabel icon2 = new JLabel("🛡️");
        icon2.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel text2 = new JLabel("100% Garansi Refund");
        text2.setFont(new Font("Dialog", Font.BOLD, 12));
        text2.setForeground(COLOR_TEXT_MUTED);
        benefit2.add(icon2);
        benefit2.add(text2);
        
        JPanel benefit3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        benefit3.setBackground(COLOR_CARD);
        JLabel icon3 = new JLabel("💬");
        icon3.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel text3 = new JLabel("Support 24/7");
        text3.setFont(new Font("Dialog", Font.BOLD, 12));
        text3.setForeground(COLOR_TEXT_MUTED);
        benefit3.add(icon3);
        benefit3.add(text3);
        
        benefitPanel.add(benefit1);
        benefitPanel.add(benefit2);
        benefitPanel.add(benefit3);
        panel.add(benefitPanel);
        
        return panel;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: createFooter()
    // 📝 FUNGSI: Membuat footer
    // ════════════════════════════════════════════════════════════════════════════════
    private JPanel createFooter() {
        JPanel footer = new JPanel();
        footer.setBackground(COLOR_CARD);
        footer.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        footer.setMaximumSize(new Dimension(1200, 30));
        JLabel text = new JLabel("© 2024 Topup Game Store | Topup Diamond Termurah dan Terpercaya");
        text.setFont(new Font("Dialog", Font.PLAIN, 11));
        text.setForeground(COLOR_TEXT_MUTED);
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        footer.add(text);
        return footer;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: createCardPanel()
    // 📝 FUNGSI: Membuat panel card dengan border
    // ════════════════════════════════════════════════════════════════════════════════
    private JPanel createCardPanel() {
        JPanel card = new JPanel();
        card.setBackground(COLOR_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDER),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        return card;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: updateRingkasan()
    // 📝 FUNGSI: Mengupdate ringkasan pembayaran
    // ════════════════════════════════════════════════════════════════════════════════
    private void updateRingkasan() {
        StringBuilder sb = new StringBuilder();
        sb.append("Game yang dipilih    : ").append(selectedGame != null ? selectedGame.getNamaGame() : "-").append("\n");
        sb.append("Nominal              : ").append(selectedNominal != null ? selectedNominal : "-").append("\n");
        sb.append("Harga Game           : ").append(selectedPrice > 0 ? formatRupiah(selectedPrice) : "Rp 0").append("\n");
        
        int adminFee = 0;
        int total = selectedPrice;
        
        if (selectedMetode != null) {
            adminFee = selectedMetode.getBiayaAdmin();
            total += adminFee;
            sb.append("Biaya Admin          : ").append(formatRupiah(adminFee)).append("\n");
            sb.append("Metode Bayar         : ").append(selectedMetode.getNamaMetode()).append("\n");
        } else {
            sb.append("Biaya Admin          : Rp 0\n");
            sb.append("Metode Bayar         : -\n");
        }
        
        if (appliedPromoCode != null && appliedDiscount > 0) {
            sb.append("Promo                : ").append(appliedPromoCode).append("\n");
            sb.append("Diskon Promo         : -").append(formatRupiah(appliedDiscount)).append("\n");
            total -= appliedDiscount;
        }
        
        sb.append("─────────────────────────────────\n");
        sb.append("💰 TOTAL BAYAR        : ").append(formatRupiah(total));
        
        txtRingkasan.setText(sb.toString());
        lblTotalBayar.setText(formatRupiah(total));
        
        boolean isValid = selectedGame != null && selectedNominal != null && selectedMetode != null;
        btnBayar.setEnabled(isValid);
        
        if (isValid) {
            progressBar.setValue(75);
            progressBar.setString("Siap bayar!");
            progressBar.setForeground(COLOR_SECONDARY);
        } else {
            progressBar.setValue(25);
            progressBar.setString("Lengkapi data");
            progressBar.setForeground(COLOR_ACCENT);
        }
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: formatRupiah()
    // 📝 FUNGSI: Memformat angka ke format Rupiah
    // ════════════════════════════════════════════════════════════════════════════════
    private String formatRupiah(int amount) {
        return "Rp " + String.format("%,d", amount).replace(",", ".");
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: setListeners()
    // 📝 FUNGSI: Menambahkan event listener ke tombol
    // ════════════════════════════════════════════════════════════════════════════════
    private void setListeners() {
        btnBayar.addActionListener(e -> prosesBayar());
        btnReset.addActionListener(e -> resetForm());
        btnApplyPromo.addActionListener(e -> applyPromo());
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: applyPromo()
    // 📝 FUNGSI: Menerapkan kode promo (HANYA WEEKEND20 dan FREEADMIN)
    // ════════════════════════════════════════════════════════════════════════════════
    private void applyPromo() {
        String promoCode = txtPromoCode.getText().trim().toUpperCase();
        if (promoCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan kode promo terlebih dahulu!");
            return;
        }
        
        // 📌 Validasi hanya WEEKEND20 dan FREEADMIN
        if (!promoCode.equals("WEEKEND20") && !promoCode.equals("FREEADMIN")) {
            JOptionPane.showMessageDialog(this, 
                "❌ Kode promo tidak valid!\n\n" +
                "📋 Kode promo yang tersedia:\n" +
                "• WEEKEND20 - Diskon 20%\n" +
                "• FREEADMIN - Gratis biaya admin",
                "Promo Invalid", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // 📌 Proses validasi melalui PromoManager
        if (PromoManager.isValidPromo(promoCode)) {
            // 📌 Ambil total sebelum promo
            int totalSebelum = getCurrentTotal();
            int adminFee = selectedMetode != null ? selectedMetode.getBiayaAdmin() : 0;
            
            // 📌 Terapkan promo dengan admin fee
            int totalSesudah = PromoManager.applyPromoWithAdmin(promoCode, selectedPrice, adminFee);
            
            // 📌 Hitung diskon
            appliedDiscount = totalSebelum - totalSesudah;
            appliedPromoCode = promoCode;
            
            // 📌 Cek tipe promo untuk pesan yang sesuai
            String promoType = PromoManager.getPromoType(promoCode);
            String pesan = "";
            
            if ("free_admin".equals(promoType)) {
                pesan = "✅ Biaya admin GRATIS! Hemat Rp " + String.format("%,d", adminFee);
            } else {
                int diskonValue = (int)((appliedDiscount * 100.0) / totalSebelum);
                pesan = "✅ Kode promo " + promoCode + " berhasil!\nDiskon: " + diskonValue + "% (" + formatRupiah(appliedDiscount) + ")";
            }
            
            JOptionPane.showMessageDialog(this, pesan, "Promo Berhasil", JOptionPane.INFORMATION_MESSAGE);
            updateRingkasan();
        } else {
            JOptionPane.showMessageDialog(this, 
                "❌ Kode promo tidak valid!\n\n" +
                "📋 Kode promo yang tersedia:\n" +
                "• WEEKEND20 - Diskon 20%\n" +
                "• FREEADMIN - Gratis biaya admin",
                "Promo Invalid", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: getCurrentTotal()
    // 📝 FUNGSI: Menghitung total saat ini (harga + admin)
    // ════════════════════════════════════════════════════════════════════════════════
    private int getCurrentTotal() {
        int total = selectedPrice;
        if (selectedMetode != null) {
            total += selectedMetode.getBiayaAdmin();
        }
        return total;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: prosesBayar()
    // 📝 FUNGSI: Memulai proses pembayaran
    // ════════════════════════════════════════════════════════════════════════════════
    private void prosesBayar() {
        // 📌 Validasi ID Game
        if (txtUserId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan ID Game Anda terlebih dahulu!");
            return;
        }
        // 📌 Validasi Email
        if (txtEmail.getText().trim().isEmpty() || !txtEmail.getText().contains("@")) {
            JOptionPane.showMessageDialog(this, "Masukkan email yang valid!");
            return;
        }
        
        int totalBayar = getCurrentTotal() - appliedDiscount;
        showRealisticEWalletConfirmation(totalBayar);
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: showRealisticEWalletConfirmation()
    // 📝 FUNGSI: Menampilkan dialog konfirmasi pembayaran
    // ════════════════════════════════════════════════════════════════════════════════
    private void showRealisticEWalletConfirmation(int totalBayar) {
        // 📌 Membuat dialog konfirmasi
        JDialog confirmDialog = new JDialog(this, "💳 Konfirmasi Pembayaran", true);
        confirmDialog.setLayout(new BorderLayout());
        confirmDialog.getContentPane().setBackground(Color.WHITE);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        
        // 📌 Header
        JLabel iconLabel = new JLabel("💳");
        iconLabel.setFont(new Font("Dialog", Font.PLAIN, 48));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(iconLabel);
        mainPanel.add(Box.createVerticalStrut(5));
        
        JLabel titleLabel = new JLabel("KONFIRMASI PEMBAYARAN");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 51, 102));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(5));
        
        JLabel subTitleLabel = new JLabel("Silakan lakukan pembayaran sesuai instruksi di bawah");
        subTitleLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        subTitleLabel.setForeground(new Color(107, 114, 128));
        subTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(subTitleLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        
        // 📌 Timer Countdown
        JPanel timerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        timerPanel.setBackground(new Color(255, 240, 240));
        timerPanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        JLabel timerIcon = new JLabel("⏱️");
        timerIcon.setFont(new Font("Dialog", Font.PLAIN, 16));
        JLabel timerLabel = new JLabel("Sisa waktu: 00:10");
        timerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        timerLabel.setForeground(new Color(220, 38, 38));
        timerPanel.add(timerIcon);
        timerPanel.add(timerLabel);
        mainPanel.add(timerPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        
        // 📌 Virtual Account
        String vaNumber = "8810" + System.currentTimeMillis() % 10000000000L;
        JPanel vaPanel = new JPanel();
        vaPanel.setBackground(new Color(240, 248, 255));
        vaPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 102, 204), 2),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        vaPanel.setLayout(new BoxLayout(vaPanel, BoxLayout.Y_AXIS));
        JLabel vaTitle = new JLabel("💳 VIRTUAL ACCOUNT");
        vaTitle.setFont(new Font("Dialog", Font.BOLD, 12));
        vaTitle.setForeground(new Color(0, 102, 204));
        vaTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        vaPanel.add(vaTitle);
        vaPanel.add(Box.createVerticalStrut(5));
        JLabel vaNumberLabel = new JLabel(vaNumber);
        vaNumberLabel.setFont(new Font("Monospaced", Font.BOLD, 22));
        vaNumberLabel.setForeground(new Color(0, 102, 204));
        vaNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        vaPanel.add(vaNumberLabel);
        
        // 📌 Tombol Salin VA
        JButton copyButton = new JButton("📋 Salin VA");
        copyButton.setFont(new Font("Dialog", Font.BOLD, 11));
        copyButton.setBackground(new Color(224, 240, 255));
        copyButton.setForeground(Color.BLACK);
        copyButton.setFocusPainted(false);
        copyButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        copyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        copyButton.addActionListener(e -> {
            java.awt.Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new java.awt.datatransfer.StringSelection(vaNumber), null);
            JOptionPane.showMessageDialog(confirmDialog, "✅ Nomor VA berhasil disalin!", "Info", JOptionPane.INFORMATION_MESSAGE);
        });
        vaPanel.add(Box.createVerticalStrut(5));
        vaPanel.add(copyButton);
        mainPanel.add(vaPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        
        // 📌 Status Progress
        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(new Color(245, 247, 250));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        JLabel statusLabel = new JLabel("🔄 Menunggu pembayaran...");
        statusLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        statusLabel.setForeground(new Color(245, 158, 11));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusPanel.add(statusLabel);
        JProgressBar statusProgress = new JProgressBar(0, 100);
        statusProgress.setValue(25);
        statusProgress.setStringPainted(false);
        statusProgress.setPreferredSize(new Dimension(350, 6));
        statusProgress.setForeground(new Color(245, 158, 11));
        statusProgress.setBackground(new Color(229, 231, 235));
        statusProgress.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusPanel.add(statusProgress);
        mainPanel.add(statusPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        
        // 📌 Struk Pembelian
        JPanel strukPanel = new JPanel();
        strukPanel.setBackground(new Color(248, 250, 252));
        strukPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 212, 220), 1),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        strukPanel.setLayout(new GridLayout(0, 2, 10, 6));
        strukPanel.setMaximumSize(new Dimension(450, 180));
        
        String[][] strukData = {
            {"🎮 Game", selectedGame.getNamaGame()},
            {"💎 Nominal", selectedNominal},
            {"💳 Metode", selectedMetode.getNamaMetode()},
            {"🆔 ID Game", txtUserId.getText()},
            {"💰 Total Bayar", formatRupiah(totalBayar)}
        };
        for (String[] data : strukData) {
            JLabel labelLeft = new JLabel(data[0]);
            labelLeft.setFont(new Font("Dialog", Font.PLAIN, 12));
            labelLeft.setForeground(new Color(107, 114, 128));
            JLabel labelRight = new JLabel(data[1]);
            labelRight.setFont(new Font("Dialog", Font.BOLD, 12));
            labelRight.setForeground(new Color(31, 41, 55));
            if (data[0].equals("💰 Total Bayar")) {
                labelRight.setForeground(new Color(220, 38, 38));
                labelRight.setFont(new Font("Dialog", Font.BOLD, 16));
            }
            strukPanel.add(labelLeft);
            strukPanel.add(labelRight);
        }
        mainPanel.add(strukPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        
        // 📌 Instruksi Pembayaran
        JPanel instruksiPanel = new JPanel();
        instruksiPanel.setBackground(new Color(255, 248, 230));
        instruksiPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(245, 158, 11), 1),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        instruksiPanel.setLayout(new BoxLayout(instruksiPanel, BoxLayout.Y_AXIS));
        JLabel instruksiTitle = new JLabel("📌 INSTRUKSI PEMBAYARAN");
        instruksiTitle.setFont(new Font("Dialog", Font.BOLD, 13));
        instruksiTitle.setForeground(new Color(245, 158, 11));
        instruksiTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        instruksiPanel.add(instruksiTitle);
        instruksiPanel.add(Box.createVerticalStrut(8));
        
        // 📌 Instruksi berdasarkan E-Wallet
        String metode = selectedMetode.getNamaMetode().toLowerCase();
        String[] instruksi;
        switch (metode) {
            case "dana":
                instruksi = new String[]{
                    "1. Buka aplikasi DANA",
                    "2. Pilih menu 'Bayar' atau 'Transfer'",
                    "3. Pilih 'Virtual Account' / 'VA'",
                    "4. Masukkan nomor VA: " + vaNumber,
                    "5. Masukkan nominal: " + formatRupiah(totalBayar),
                    "6. Konfirmasi dan selesaikan pembayaran"
                };
                break;
            case "gopay":
                instruksi = new String[]{
                    "1. Buka aplikasi GoPay (Gojek)",
                    "2. Pilih menu 'Bayar'",
                    "3. Pilih 'Virtual Account' / 'VA'",
                    "4. Masukkan nomor VA: " + vaNumber,
                    "5. Masukkan nominal: " + formatRupiah(totalBayar),
                    "6. Konfirmasi dan selesaikan pembayaran"
                };
                break;
            case "ovo":
                instruksi = new String[]{
                    "1. Buka aplikasi OVO",
                    "2. Pilih menu 'Bayar'",
                    "3. Pilih 'Virtual Account' / 'VA'",
                    "4. Masukkan nomor VA: " + vaNumber,
                    "5. Masukkan nominal: " + formatRupiah(totalBayar),
                    "6. Konfirmasi dan selesaikan pembayaran"
                };
                break;
            case "shopeepay":
                instruksi = new String[]{
                    "1. Buka aplikasi Shopee",
                    "2. Pilih menu 'ShopeePay'",
                    "3. Pilih 'Bayar Tagihan'",
                    "4. Masukkan nomor VA: " + vaNumber,
                    "5. Masukkan nominal: " + formatRupiah(totalBayar),
                    "6. Konfirmasi dan selesaikan pembayaran"
                };
                break;
            default:
                instruksi = new String[]{
                    "1. Buka aplikasi " + selectedMetode.getNamaMetode(),
                    "2. Pilih menu 'Bayar' atau 'Transfer'",
                    "3. Masukkan nomor VA: " + vaNumber,
                    "4. Masukkan nominal: " + formatRupiah(totalBayar),
                    "5. Konfirmasi dan selesaikan pembayaran"
                };
                break;
        }
        for (String step : instruksi) {
            JLabel stepLabel = new JLabel(step);
            stepLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
            stepLabel.setForeground(new Color(80, 80, 80));
            stepLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            instruksiPanel.add(stepLabel);
            instruksiPanel.add(Box.createVerticalStrut(4));
        }
        mainPanel.add(instruksiPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        
        // 📌 Tombol Aksi
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(Color.WHITE);
        JButton btnBayar = new JButton("✅ Saya Sudah Bayar");
        btnBayar.setFont(new Font("Dialog", Font.BOLD, 14));
        btnBayar.setBackground(new Color(16, 185, 129));
        btnBayar.setForeground(Color.BLACK);
        btnBayar.setFocusPainted(false);
        btnBayar.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        btnBayar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton btnBatal = new JButton("❌ Batal");
        btnBatal.setFont(new Font("Dialog", Font.BOLD, 14));
        btnBatal.setBackground(new Color(239, 68, 68));
        btnBatal.setForeground(Color.BLACK);
        btnBatal.setFocusPainted(false);
        btnBatal.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        btnBatal.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPanel.add(btnBayar);
        buttonPanel.add(btnBatal);
        mainPanel.add(buttonPanel);
        
        confirmDialog.add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        confirmDialog.setSize(520, 700);
        confirmDialog.setLocationRelativeTo(this);
        confirmDialog.setResizable(false);
        
        // 📌 Countdown Timer
        Timer countdownTimer = new Timer(1000, new ActionListener() {
            int seconds = 600;
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds--;
                int menit = seconds / 60;
                int detik = seconds % 60;
                timerLabel.setText(String.format("Sisa waktu: %02d:%02d", menit, detik));
                int progress = (int)((600 - seconds) * 100.0 / 600);
                statusProgress.setValue(progress);
                if (seconds <= 300) {
                    timerLabel.setForeground(new Color(220, 38, 38));
                    statusProgress.setForeground(new Color(220, 38, 38));
                    statusLabel.setText("⏰ Waktu hampir habis!");
                    statusLabel.setForeground(new Color(220, 38, 38));
                }
                if (seconds <= 0) {
                    ((Timer) e.getSource()).stop();
                    timerLabel.setText("⏰ Waktu habis!");
                    timerLabel.setForeground(new Color(220, 38, 38));
                    statusLabel.setText("❌ Waktu pembayaran habis!");
                    statusLabel.setForeground(new Color(220, 38, 38));
                    btnBayar.setEnabled(false);
                }
            }
        });
        countdownTimer.start();
        
        // 📌 Action Listener
        btnBayar.addActionListener(e -> {
            countdownTimer.stop();
            confirmDialog.dispose();
            prosesBayarLanjut(totalBayar);
        });
        btnBatal.addActionListener(e -> {
            countdownTimer.stop();
            confirmDialog.dispose();
        });
        confirmDialog.setVisible(true);
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: prosesBayarLanjut()
    // 📝 FUNGSI: Proses setelah user mengkonfirmasi pembayaran
    // ════════════════════════════════════════════════════════════════════════════════
    private void prosesBayarLanjut(int totalBayar) {
        try {
            // 📌 Simpan transaksi
            Transaction transaksi = new Transaction(
                selectedGame.getNamaGame(), selectedNominal, selectedPrice,
                selectedMetode.getNamaMetode(), selectedMetode.getBiayaAdmin(),
                selectedMetode.getDiskonPersen(), totalBayar,
                txtUserId.getText().trim(), txtEmail.getText().trim(),
                loggedInUser.getUsername()
            );
            Transaction.simpanTransaksi(transaksi);
            User.updateUserAfterTopup(loggedInUser.getUsername(), totalBayar);
            
            // 📌 Progress Bar Menunggu Topup
            JPanel progressPanel = new JPanel();
            progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.Y_AXIS));
            progressPanel.setBackground(Color.WHITE);
            progressPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            
            JLabel iconLabel = new JLabel("⏳");
            iconLabel.setFont(new Font("Dialog", Font.PLAIN, 50));
            iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            progressPanel.add(iconLabel);
            progressPanel.add(Box.createVerticalStrut(15));
            
            JLabel titleLabel = new JLabel("MENUNGGU TOPUP MASUK");
            titleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
            titleLabel.setForeground(new Color(0, 102, 204));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            progressPanel.add(titleLabel);
            progressPanel.add(Box.createVerticalStrut(5));
            
            JLabel subTitleLabel = new JLabel("Mohon tunggu, topup sedang diproses...");
            subTitleLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
            subTitleLabel.setForeground(new Color(107, 114, 128));
            subTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            progressPanel.add(subTitleLabel);
            progressPanel.add(Box.createVerticalStrut(20));
            
            JProgressBar progressBarLoading = new JProgressBar(0, 100);
            progressBarLoading.setStringPainted(true);
            progressBarLoading.setForeground(new Color(16, 185, 129));
            progressBarLoading.setBackground(new Color(229, 231, 235));
            progressBarLoading.setFont(new Font("Dialog", Font.BOLD, 13));
            progressBarLoading.setPreferredSize(new Dimension(400, 30));
            progressBarLoading.setAlignmentX(Component.CENTER_ALIGNMENT);
            progressPanel.add(progressBarLoading);
            progressPanel.add(Box.createVerticalStrut(15));
            
            // 📌 Info transaksi
            JPanel infoPanel = new JPanel(new GridLayout(0, 2, 15, 8));
            infoPanel.setBackground(new Color(248, 250, 252));
            infoPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 212, 220), 1),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)
            ));
            infoPanel.setMaximumSize(new Dimension(450, 150));
            String[][] infoData = {
                {"🎮 Game", selectedGame.getNamaGame()},
                {"💎 Nominal", selectedNominal},
                {"💳 Metode", selectedMetode.getNamaMetode()},
                {"🆔 ID Game", txtUserId.getText()},
                {"💰 Total Bayar", formatRupiah(totalBayar)}
            };
            for (String[] data : infoData) {
                JLabel labelLeft = new JLabel(data[0]);
                labelLeft.setFont(new Font("Dialog", Font.PLAIN, 12));
                labelLeft.setForeground(new Color(107, 114, 128));
                JLabel labelRight = new JLabel(data[1]);
                labelRight.setFont(new Font("Dialog", Font.BOLD, 12));
                labelRight.setForeground(new Color(31, 41, 55));
                if (data[0].equals("💰 Total Bayar")) {
                    labelRight.setForeground(new Color(220, 38, 38));
                    labelRight.setFont(new Font("Dialog", Font.BOLD, 14));
                }
                infoPanel.add(labelLeft);
                infoPanel.add(labelRight);
            }
            progressPanel.add(infoPanel);
            progressPanel.add(Box.createVerticalStrut(20));
            
            JLabel statusLabel = new JLabel("🔄 Memverifikasi pembayaran...");
            statusLabel.setFont(new Font("Dialog", Font.BOLD, 13));
            statusLabel.setForeground(new Color(0, 102, 204));
            statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            progressPanel.add(statusLabel);
            progressPanel.add(Box.createVerticalStrut(10));
            
            JLabel progressText = new JLabel("0%");
            progressText.setFont(new Font("Dialog", Font.BOLD, 14));
            progressText.setForeground(new Color(16, 185, 129));
            progressText.setAlignmentX(Component.CENTER_ALIGNMENT);
            progressPanel.add(progressText);
            
            // 📌 Dialog progress
            JDialog progressDialog = new JDialog(this, "⏳ Memproses Topup", true);
            progressDialog.setContentPane(new JScrollPane(progressPanel));
            progressDialog.setSize(520, 540);
            progressDialog.setLocationRelativeTo(this);
            progressDialog.setResizable(false);
            progressDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            
            // 📌 Timer progress
            Timer progressTimer = new Timer(100, new ActionListener() {
                int progress = 0;
                @Override
                public void actionPerformed(ActionEvent e) {
                    progress += 1;
                    if (progress <= 100) {
                        progressBarLoading.setValue(progress);
                        progressText.setText(progress + "%");
                        if (progress <= 20) {
                            statusLabel.setText("🔄 Memverifikasi pembayaran...");
                        } else if (progress <= 40) {
                            statusLabel.setText("⏳ Menghubungi server game...");
                        } else if (progress <= 60) {
                            statusLabel.setText("📤 Mengirim diamond...");
                        } else if (progress <= 80) {
                            statusLabel.setText("✅ Topup sedang diproses...");
                        } else if (progress <= 95) {
                            statusLabel.setText("🎉 Hampir selesai...");
                        } else {
                            statusLabel.setText("✨ Topup berhasil!");
                        }
                        if (progress <= 30) {
                            iconLabel.setText("⏳");
                        } else if (progress <= 60) {
                            iconLabel.setText("📤");
                        } else if (progress <= 85) {
                            iconLabel.setText("✅");
                        } else {
                            iconLabel.setText("🎉");
                        }
                    } else {
                        ((Timer) e.getSource()).stop();
                        progressDialog.dispose();
                        showSuccessStruk(totalBayar, transaksi);
                    }
                }
            });
            progressTimer.start();
            progressDialog.setVisible(true);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: showSuccessStruk()
    // 📝 FUNGSI: Menampilkan struk sukses pembayaran
    // ════════════════════════════════════════════════════════════════════════════════
    private void showSuccessStruk(int totalBayar, Transaction transaksi) {
        User updatedUser = User.getUserByUsername(loggedInUser.getUsername());
        int poinSekarang = (updatedUser != null) ? updatedUser.getPoin() : loggedInUser.getPoin();
        
        JPanel successPanel = new JPanel();
        successPanel.setLayout(new BoxLayout(successPanel, BoxLayout.Y_AXIS));
        successPanel.setBackground(Color.WHITE);
        successPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        
        JLabel iconLabel = new JLabel("🎉");
        iconLabel.setFont(new Font("Dialog", Font.PLAIN, 60));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        successPanel.add(iconLabel);
        successPanel.add(Box.createVerticalStrut(5));
        
        JLabel titleLabel = new JLabel("PEMBAYARAN BERHASIL!");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 22));
        titleLabel.setForeground(new Color(16, 185, 129));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        successPanel.add(titleLabel);
        successPanel.add(Box.createVerticalStrut(15));
        
        // 📌 Struk
        JPanel strukPanel = new JPanel();
        strukPanel.setBackground(new Color(248, 250, 252));
        strukPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 212, 220), 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        strukPanel.setLayout(new GridLayout(0, 2, 10, 6));
        strukPanel.setMaximumSize(new Dimension(480, 220));
        String[][] strukData = {
            {"📋 Kode Transaksi", transaksi.getKodeTransaksi()},
            {"🎮 Game", selectedGame.getNamaGame()},
            {"💎 Nominal", selectedNominal},
            {"💳 Metode Bayar", selectedMetode.getNamaMetode()},
            {"💰 Total Bayar", formatRupiah(totalBayar)},
            {"🆔 ID Game", txtUserId.getText()}
        };
        for (String[] data : strukData) {
            JLabel labelLeft = new JLabel(data[0]);
            labelLeft.setFont(new Font("Dialog", Font.PLAIN, 12));
            labelLeft.setForeground(new Color(107, 114, 128));
            JLabel labelRight = new JLabel(data[1]);
            labelRight.setFont(new Font("Dialog", Font.BOLD, 12));
            labelRight.setForeground(new Color(31, 41, 55));
            if (data[0].equals("💰 Total Bayar")) {
                labelRight.setForeground(new Color(220, 38, 38));
                labelRight.setFont(new Font("Dialog", Font.BOLD, 16));
            }
            strukPanel.add(labelLeft);
            strukPanel.add(labelRight);
        }
        successPanel.add(strukPanel);
        successPanel.add(Box.createVerticalStrut(15));
        
        JLabel msgLabel = new JLabel("✅ Topup telah masuk ke akun Anda!");
        msgLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        msgLabel.setForeground(new Color(16, 185, 129));
        msgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        successPanel.add(msgLabel);
        successPanel.add(Box.createVerticalStrut(5));
        
        JLabel msgLabel2 = new JLabel("🎮 Selamat bermain! Terima kasih telah menggunakan layanan kami.");
        msgLabel2.setFont(new Font("Dialog", Font.PLAIN, 12));
        msgLabel2.setForeground(new Color(107, 114, 128));
        msgLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        successPanel.add(msgLabel2);
        successPanel.add(Box.createVerticalStrut(15));
        
        JPanel poinPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        poinPanel.setBackground(Color.WHITE);
        JLabel poinLabel = new JLabel("⭐ Poin Anda sekarang: " + poinSekarang + " poin");
        poinLabel.setFont(new Font("Dialog", Font.BOLD, 13));
        poinLabel.setForeground(new Color(245, 158, 11));
        poinPanel.add(poinLabel);
        successPanel.add(poinPanel);
        successPanel.add(Box.createVerticalStrut(5));
        
        JLabel poinInfo = new JLabel("💡 Setiap Rp 10.000 topup = 1 poin");
        poinInfo.setFont(new Font("Dialog", Font.PLAIN, 11));
        poinInfo.setForeground(new Color(107, 114, 128));
        poinInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        successPanel.add(poinInfo);
        successPanel.add(Box.createVerticalStrut(15));
        
        JButton btnOK = new JButton("✅ OK");
        btnOK.setFont(new Font("Dialog", Font.BOLD, 14));
        btnOK.setBackground(new Color(16, 185, 129));
        btnOK.setForeground(Color.BLACK);
        btnOK.setFocusPainted(false);
        btnOK.setBorder(BorderFactory.createEmptyBorder(12, 40, 12, 40));
        btnOK.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOK.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setBackground(Color.WHITE);
        btnPanel.add(btnOK);
        successPanel.add(btnPanel);
        
        JDialog successDialog = new JDialog(this, "Topup Sukses", true);
        successDialog.setContentPane(new JScrollPane(successPanel));
        successDialog.setSize(550, 620);
        successDialog.setLocationRelativeTo(this);
        successDialog.setResizable(false);
        
        btnOK.addActionListener(e -> {
            successDialog.dispose();
            int lagi = JOptionPane.showConfirmDialog(this, "Ingin melakukan topup lagi?", "Transaksi Baru", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (lagi == JOptionPane.YES_OPTION) {
                resetForm();
            } else {
                dispose();
                new FormDashboard(loggedInUser);
            }
        });
        successDialog.setVisible(true);
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: padRight()
    // 📝 FUNGSI: Menambahkan padding ke kanan string
    // ════════════════════════════════════════════════════════════════════════════════
    private String padRight(String s, int length) {
        if (s == null) s = "-";
        if (s.length() > length) return s.substring(0, length);
        return String.format("%-" + length + "s", s);
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 METHOD: resetForm()
    // 📝 FUNGSI: Mereset semua pilihan dan form
    // ════════════════════════════════════════════════════════════════════════════════
    private void resetForm() {
        selectedGame = null;
        selectedNominal = null;
        selectedPrice = 0;
        selectedMetode = null;
        selectedNominalCard = null;
        selectedMetodeCard = null;
        selectedGameCard = null;
        appliedDiscount = 0;
        appliedPromoCode = null;
        
        txtUserId.setText("");
        txtEmail.setText("");
        txtPromoCode.setText("");
        txtSearchField.setText("");
        
        updateRingkasan();
        
        // Reset game grid
        for (Component comp : gameGridPanel.getComponents()) {
            if (comp instanceof JPanel) {
                comp.setBackground(Color.WHITE);
                ((JPanel) comp).setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(COLOR_BORDER),
                    BorderFactory.createEmptyBorder(10, 8, 10, 8)
                ));
            }
        }
        
        // Reset nominal grid
        nominalGridPanel.removeAll();
        JLabel placeholder = new JLabel("Pilih game terlebih dahulu", SwingConstants.CENTER);
        placeholder.setForeground(COLOR_TEXT_MUTED);
        placeholder.setFont(new Font("Dialog", Font.PLAIN, 13));
        nominalGridPanel.add(placeholder);
        nominalGridPanel.revalidate();
        nominalGridPanel.repaint();
        
        progressBar.setValue(0);
        progressBar.setString("Belum lengkap");
        progressBar.setForeground(COLOR_ACCENT);
        
        JOptionPane.showMessageDialog(this, "✨ Form berhasil direset!", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}