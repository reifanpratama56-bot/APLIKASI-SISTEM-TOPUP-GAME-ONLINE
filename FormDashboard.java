package form;

import model.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class FormDashboard extends JFrame {
    
    private final Color COLOR_BG = new Color(0, 102, 204);
    private final Color COLOR_BG_DARK = new Color(0, 51, 102);
    private final Color CARD_BG = new Color(224, 240, 255);
    private final Color CARD_BG_HEADER = new Color(200, 225, 255);
    private final Color TEXT_PRIMARY = new Color(0, 51, 102);
    private final Color TEXT_MUTED = new Color(70, 130, 200);
    private final Color BORDER_COLOR = new Color(100, 149, 237);
    private final Color ACCENT_POIN = new Color(245, 158, 11);
    private final Color ACCENT_TOTAL = new Color(16, 185, 129);
    private final Color ACCENT_FAVORITE = new Color(0, 102, 204);
    private final Color ACCENT_PROMO = new Color(239, 68, 68);
    private final Color BUTTON_BG = new Color(224, 240, 255);
    
    private User loggedInUser;
    private JLabel lblWelcome;
    private JLabel lblPoin;
    private JLabel lblTotalTopup;
    private JLabel lblFavoriteGame;
    private JPanel promoPanel;
    
    public FormDashboard(User user) {
        this.loggedInUser = user;
        initComponents();
        loadData();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    
    private void initComponents() {
        setTitle("Dashboard - Topup Game Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
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
        mainPanel.setLayout(new BorderLayout());
        
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(0, 102, 204, 240));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(224, 240, 255));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(CARD_BG_HEADER);
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 2),
            BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        
        JLabel avatar = new JLabel("👤");
        avatar.setFont(new Font("Dialog", Font.PLAIN, 55));
        
        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setBackground(CARD_BG_HEADER);
        
        lblWelcome = new JLabel("Halo, " + loggedInUser.getUsername());
        lblWelcome.setFont(new Font("Dialog", Font.BOLD, 24));
        lblWelcome.setForeground(TEXT_PRIMARY);
        
        JLabel lblMember = new JLabel("Member sejak 2026");
        lblMember.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblMember.setForeground(TEXT_MUTED);
        
        textPanel.add(lblWelcome);
        textPanel.add(lblMember);
        
        headerPanel.add(avatar, BorderLayout.WEST);
        headerPanel.add(textPanel, BorderLayout.CENTER);
        centerPanel.add(headerPanel);
        centerPanel.add(Box.createVerticalStrut(20));
        
        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        statsPanel.setBackground(CARD_BG);
        statsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        
        statsPanel.add(createStatCard("⭐ POIN SAYA", ACCENT_POIN, loggedInUser.getPoin() + " Poin", 32));
        statsPanel.add(createStatCard("💰 TOTAL TOPUP", ACCENT_TOTAL, "Rp " + formatRupiah(loggedInUser.getTotalTopup()), 32));
        statsPanel.add(createStatCard("🎮 GAME FAVORIT", ACCENT_FAVORITE, "Mobile Legends", 18));
        
        centerPanel.add(statsPanel);
        centerPanel.add(Box.createVerticalStrut(20));
        
        JPanel promoCard = new JPanel();
        promoCard.setBackground(CARD_BG);
        promoCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 2),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        promoCard.setLayout(new BoxLayout(promoCard, BoxLayout.Y_AXIS));
        promoCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
        
        JLabel promoTitle = new JLabel("🎉 PROMO HARI INI");
        promoTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        promoTitle.setForeground(ACCENT_PROMO);
        promoTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        promoCard.add(promoTitle);
        promoCard.add(Box.createVerticalStrut(15));
        
        promoPanel = new JPanel();
        promoPanel.setBackground(CARD_BG);
        promoPanel.setLayout(new GridLayout(3, 1, 0, 8));
        promoCard.add(promoPanel);
        
        centerPanel.add(promoCard);
        centerPanel.add(Box.createVerticalStrut(25));
        
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        buttonPanel.setBackground(CARD_BG);
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
        
        buttonPanel.add(createNavButton("🎮", "TOPUP", ACCENT_FAVORITE, () -> {
            dispose();
            new FormTopup(loggedInUser);
        }));
        
        buttonPanel.add(createNavButton("📋", "HISTORY", ACCENT_TOTAL, () -> {
            new FormHistory(loggedInUser);
        }));
        
        buttonPanel.add(createNavButton("👤", "PROFILE", ACCENT_POIN, this::showProfile));
        
        buttonPanel.add(createNavButton("🚪", "LOGOUT", ACCENT_PROMO, () -> {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin logout?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new FormLogin().setVisible(true);
            }
        }));
        
        centerPanel.add(buttonPanel);
        centerPanel.add(Box.createVerticalStrut(20));
        
        JLabel footer = new JLabel("© 2026 Topup Game Store | Topup Diamond Termurah dan Terpercaya");
        footer.setFont(new Font("Dialog", Font.PLAIN, 11));
        footer.setForeground(TEXT_MUTED);
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(footer);
        
        contentPanel.add(centerPanel, gbc);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);
    }
    
    private JPanel createStatCard(String title, Color accentColor, String value, int fontSize) {
        JPanel card = new JPanel();
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        titleLabel.setForeground(accentColor);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(12));
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Dialog", Font.BOLD, fontSize));
        valueLabel.setForeground(TEXT_PRIMARY);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(valueLabel);
        
        if (title.equals("⭐ POIN SAYA")) {
            lblPoin = valueLabel;
        } else if (title.equals("💰 TOTAL TOPUP")) {
            lblTotalTopup = valueLabel;
        } else if (title.equals("🎮 GAME FAVORIT")) {
            lblFavoriteGame = valueLabel;
        }
        
        return card;
    }
    
    private JButton createNavButton(String icon, String text, Color color, Runnable action) {
        JButton btn = new JButton();
        btn.setLayout(new BoxLayout(btn, BoxLayout.Y_AXIS));
        btn.setBackground(BUTTON_BG);
        btn.setForeground(color);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Dialog", Font.PLAIN, 32));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        textLabel.setForeground(color);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btn.add(iconLabel);
        btn.add(Box.createVerticalStrut(8));
        btn.add(textLabel);
        btn.addActionListener(e -> action.run());
        
        return btn;
    }
    
    private void showProfile() {
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        profilePanel.setBackground(CARD_BG);
        
        profilePanel.add(createProfileRow("👤 Username", loggedInUser.getUsername()));
        profilePanel.add(Box.createVerticalStrut(12));
        profilePanel.add(createProfileRow("📧 Email", loggedInUser.getEmail()));
        profilePanel.add(Box.createVerticalStrut(12));
        profilePanel.add(createProfileRow("📱 No HP", loggedInUser.getNoHp()));
        profilePanel.add(Box.createVerticalStrut(12));
        profilePanel.add(createProfileRow("⭐ Poin", loggedInUser.getPoin() + " Poin"));
        profilePanel.add(Box.createVerticalStrut(12));
        profilePanel.add(createProfileRow("💰 Total Topup", "Rp " + formatRupiah(loggedInUser.getTotalTopup())));
        
        JOptionPane.showMessageDialog(
            this,
            profilePanel,
            "📋 Profil Saya",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    private JPanel createProfileRow(String label, String value) {
        JPanel row = new JPanel(new BorderLayout(15, 0));
        row.setBackground(CARD_BG);
        
        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(new Font("Dialog", Font.BOLD, 13));
        lblLabel.setForeground(TEXT_PRIMARY);
        
        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Dialog", Font.PLAIN, 13));
        lblValue.setForeground(TEXT_MUTED);
        
        row.add(lblLabel, BorderLayout.WEST);
        row.add(lblValue, BorderLayout.EAST);
        
        return row;
    }
    
    private void loadData() {
        promoPanel.removeAll();
        
        String[] promos = {
            "🎁 WEEKEND20 - Diskon 20% semua game!",
            "🎉 FREEADMIN - Gratis biaya admin!",
        };
        
        for (String promo : promos) {
            JPanel promoRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
            promoRow.setBackground(CARD_BG);
            JLabel promoLabel = new JLabel("📢 " + promo);
            promoLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
            promoLabel.setForeground(TEXT_MUTED);
            promoRow.add(promoLabel);
            promoPanel.add(promoRow);
        }
        
        promoPanel.revalidate();
        promoPanel.repaint();
        
        java.util.Map<String, Integer> gameCount = new java.util.HashMap<>();
        for (Transaction t : Transaction.getTransaksiByUsername(loggedInUser.getUsername()).values()) {
            gameCount.put(t.getGameName(), gameCount.getOrDefault(t.getGameName(), 0) + 1);
        }
        
        String favorite = gameCount.entrySet().stream()
            .max(java.util.Map.Entry.comparingByValue())
            .map(java.util.Map.Entry::getKey)
            .orElse("Belum ada topup");
        
        if (lblFavoriteGame != null) {
            lblFavoriteGame.setText(favorite);
        }
        
        User updatedUser = User.getUserByUsername(loggedInUser.getUsername());
        if (updatedUser != null) {
            loggedInUser = updatedUser;
            if (lblPoin != null) {
                lblPoin.setText(updatedUser.getPoin() + " Poin");
            }
            if (lblTotalTopup != null) {
                lblTotalTopup.setText("Rp " + formatRupiah(updatedUser.getTotalTopup()));
            }
        }
    }
    
    private String formatRupiah(int amount) {
        return String.format("%,d", amount).replace(",", ".");
    }
}
