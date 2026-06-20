package form;

import model.User;
import model.Transaction;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;

public class FormHistory extends JFrame {
    
    private final Color COLOR_BG = new Color(0, 102, 204);
    private final Color COLOR_BG_DARK = new Color(0, 51, 102);
    private final Color HEADER_BG = new Color(0, 51, 102);
    private final Color TABLE_ROW_EVEN = new Color(255, 255, 255);
    private final Color TABLE_ROW_ODD = new Color(240, 248, 255);
    private final Color TEXT_COLOR = new Color(0, 0, 0);
    private final Color HEADER_TEXT_COLOR = new Color(0, 0, 0);
    private final Color TEXT_MUTED = new Color(80, 80, 80);
    private final Color BORDER_COLOR = new Color(100, 149, 237);
    private final Color STATUS_SUCCESS = new Color(16, 185, 129);
    private final Color STATUS_PENDING = new Color(245, 158, 11);
    private final Color STATUS_PROCESSING = new Color(59, 130, 246);
    private final Color STATUS_FAILED = new Color(239, 68, 68);
    
    private JTable table;
    private DefaultTableModel model;
    private User loggedInUser;
    private JLabel lblTotalTransaksi;
    private JLabel lblTotalNominal;
    
    public FormHistory(User user) {
        this.loggedInUser = user;
        initComponents();
        loadData();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    
    private void initComponents() {
        setTitle("Riwayat Topup - " + loggedInUser.getUsername());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 212, 220), 1),
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        contentPanel.setLayout(new BorderLayout(0, 15));
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        JLabel titleLabel = new JLabel("📋 RIWAYAT TOPUP");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 51, 102));
        
        JLabel subtitleLabel = new JLabel("Daftar semua transaksi topup Anda");
        subtitleLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        subtitleLabel.setForeground(TEXT_MUTED);
        
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(Color.WHITE);
        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);
        headerPanel.add(titlePanel, BorderLayout.WEST);
        
        JPanel statsPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        JPanel cardTotalTrans = createStatCard("📊 TOTAL TRANSAKSI", new Color(16, 185, 129));
        lblTotalTransaksi = new JLabel("0");
        lblTotalTransaksi.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTotalTransaksi.setForeground(new Color(16, 185, 129));
        cardTotalTrans.add(lblTotalTransaksi);
        
        JPanel cardTotalNominal = createStatCard("💰 TOTAL NOMINAL", new Color(0, 102, 204));
        lblTotalNominal = new JLabel("Rp 0");
        lblTotalNominal.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTotalNominal.setForeground(new Color(0, 102, 204));
        cardTotalNominal.add(lblTotalNominal);
        
        statsPanel.add(cardTotalTrans);
        statsPanel.add(cardTotalNominal);
        headerPanel.add(statsPanel, BorderLayout.EAST);
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        
        String[] columns = {"No", "Tanggal", "Game", "Nominal", "Total Bayar", "Metode", "Status"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(model);
        table.setRowHeight(45);
        table.setFont(new Font("Dialog", Font.PLAIN, 13));
        table.setForeground(TEXT_COLOR);
        table.setBackground(Color.WHITE);
        table.setSelectionBackground(new Color(200, 225, 255));
        table.setSelectionForeground(TEXT_COLOR);
        table.setShowGrid(true);
        table.setGridColor(new Color(200, 212, 220));
        table.setIntercellSpacing(new Dimension(10, 8));
        
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Dialog", Font.BOLD, 14));
        tableHeader.setForeground(HEADER_TEXT_COLOR);
        tableHeader.setBackground(HEADER_BG);
        tableHeader.setPreferredSize(new Dimension(0, 45));
        
        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(160);
        table.getColumnModel().getColumn(2).setPreferredWidth(180);
        table.getColumnModel().getColumn(3).setPreferredWidth(140);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(130);
        table.getColumnModel().getColumn(6).setPreferredWidth(120);
        
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(new Font("Dialog", Font.PLAIN, 13));
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(TABLE_ROW_EVEN);
                    } else {
                        c.setBackground(TABLE_ROW_ODD);
                    }
                } else {
                    c.setBackground(new Color(200, 225, 255));
                }
                c.setForeground(TEXT_COLOR);
                setHorizontalAlignment(SwingConstants.CENTER);
                return c;
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 212, 220)));
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
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
        btnRefresh.addActionListener(e -> loadData());
        
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
            dispose();
            new FormDashboard(loggedInUser);
        });
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnBack);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);
        
        JLabel infoLabel = new JLabel("💡 Transaksi yang sudah sukses akan masuk ke akun dalam 1-5 menit");
        infoLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
        infoLabel.setForeground(TEXT_MUTED);
        bottomPanel.add(infoLabel, BorderLayout.WEST);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        JScrollPane mainScroll = new JScrollPane(mainPanel);
        mainScroll.setBorder(null);
        add(mainScroll);
    }
    
    private JPanel createStatCard(String title, Color accentColor) {
        JPanel card = new JPanel();
        card.setBackground(new Color(248, 250, 252));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 212, 220), 1),
            BorderFactory.createEmptyBorder(15, 25, 15, 25)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 13));
        titleLabel.setForeground(accentColor);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));
        
        return card;
    }
    
    private void loadData() {
        model.setRowCount(0);
        Map<String, Transaction> transaksiUser = Transaction.getTransaksiByUsername(loggedInUser.getUsername());
        
        if (transaksiUser.isEmpty()) {
            model.addRow(new Object[]{"-", "-", "📭 Belum ada transaksi", "-", "-", "-", "⏳"});
            lblTotalTransaksi.setText("0");
            lblTotalNominal.setText("Rp 0");
            table.setRowHeight(60);
            return;
        }
        
        table.setRowHeight(45);
        java.util.List<Transaction> transactionList = new ArrayList<>(transaksiUser.values());
        transactionList.sort((a, b) -> {
            String dateA = a.getTanggal();
            String dateB = b.getTanggal();
            if (dateA == null || dateB == null) return 0;
            return dateB.compareTo(dateA);
        });
        
        int no = 1;
        int totalTransaksi = 0;
        int totalNominal = 0;
        
        for (Transaction t : transactionList) {
            totalTransaksi++;
            totalNominal += t.getHargaFinal();
            
            String statusText;
            Color statusColor;
            
            if (t.getStatus().equals("PAYMENT_SUCCESS") || t.getStatus().equals("SUCCESS")) {
                statusText = "✅ Success";
                statusColor = STATUS_SUCCESS;
            } else if (t.getStatus().equals("WAITING_PAYMENT")) {
                statusText = "⏳ Pending";
                statusColor = STATUS_PENDING;
            } else if (t.getStatus().equals("PROCESSING")) {
                statusText = "🔄 Processing";
                statusColor = STATUS_PROCESSING;
            } else {
                statusText = "❌ Failed";
                statusColor = STATUS_FAILED;
            }
            
            String nominalItem = t.getNominalItem() != null ? t.getNominalItem() : "-";
            String tanggal = t.getTanggal() != null ? t.getTanggal() : "-";
            
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
        
        lblTotalTransaksi.setText(String.valueOf(totalTransaksi));
        lblTotalNominal.setText("Rp " + String.format("%,d", totalNominal).replace(",", "."));
        
        table.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(new Font("Dialog", Font.BOLD, 12));
                String status = (String) value;
                if (status != null) {
                    if (status.contains("Success")) {
                        c.setForeground(STATUS_SUCCESS);
                    } else if (status.contains("Pending")) {
                        c.setForeground(STATUS_PENDING);
                    } else if (status.contains("Processing")) {
                        c.setForeground(STATUS_PROCESSING);
                    } else if (status.contains("Failed")) {
                        c.setForeground(STATUS_FAILED);
                    } else {
                        c.setForeground(Color.BLACK);
                    }
                }
                setHorizontalAlignment(SwingConstants.CENTER);
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
