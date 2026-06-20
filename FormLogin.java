package form;

import model.User;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class FormLogin extends JFrame {
    
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegister;
    private JButton btnExit;
    private JLabel lblMessage;
    private User loggedInUser;
    
    public FormLogin() {
        initComponents();
        setListeners();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    
    private void initComponents() {
        setTitle("Login - Topup Game Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        getContentPane().setBackground(new Color(240, 248, 255));
        setLayout(new BorderLayout());
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 248, 255));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(80, 50, 80, 50));
        
        JLabel lblLogo = new JLabel("🎮");
        lblLogo.setFont(new Font("Dialog", Font.PLAIN, 80));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblLogo);
        mainPanel.add(Box.createVerticalStrut(20));
        
        JLabel lblTitle = new JLabel("TOPUP GAME STORE");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 36));
        lblTitle.setForeground(new Color(0, 51, 102));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);
        mainPanel.add(Box.createVerticalStrut(15));
        
        JLabel lblSubTitle = new JLabel("Login to continue");
        lblSubTitle.setFont(new Font("Dialog", Font.PLAIN, 16));
        lblSubTitle.setForeground(new Color(107, 114, 128));
        lblSubTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblSubTitle);
        mainPanel.add(Box.createVerticalStrut(50));
        
        JPanel cardPanel = new JPanel();
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 149, 237), 3),
            BorderFactory.createEmptyBorder(35, 50, 35, 50)
        ));
        cardPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        
        int row = 0;
        
        gbc.gridx = 0; gbc.gridy = row;
        JLabel lblUser = new JLabel("👤 Username");
        lblUser.setFont(new Font("Dialog", Font.BOLD, 16));
        cardPanel.add(lblUser, gbc);
        
        txtUsername = new JTextField(30);
        txtUsername.setFont(new Font("Dialog", Font.PLAIN, 16));
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 149, 237)),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        gbc.gridx = 1;
        cardPanel.add(txtUsername, gbc);
        row++;
        
        gbc.gridx = 0; gbc.gridy = row;
        JLabel lblPass = new JLabel("🔒 Password");
        lblPass.setFont(new Font("Dialog", Font.BOLD, 16));
        cardPanel.add(lblPass, gbc);
        
        txtPassword = new JPasswordField(30);
        txtPassword.setFont(new Font("Dialog", Font.PLAIN, 16));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 149, 237)),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        gbc.gridx = 1;
        cardPanel.add(txtPassword, gbc);
        row++;
        
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(new Color(240, 248, 255));
        centerWrapper.add(cardPanel);
        mainPanel.add(centerWrapper);
        mainPanel.add(Box.createVerticalStrut(30));
        
        lblMessage = new JLabel(" ");
        lblMessage.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblMessage.setForeground(new Color(220, 20, 60));
        lblMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblMessage);
        mainPanel.add(Box.createVerticalStrut(15));
        
        btnLogin = new JButton("LOGIN");
        btnLogin.setBackground(new Color(0, 191, 255));
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setFont(new Font("Dialog", Font.BOLD, 18));
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(btnLogin);
        mainPanel.add(Box.createVerticalStrut(20));
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        bottomPanel.setBackground(new Color(240, 248, 255));
        
        btnRegister = new JButton("📝 Register");
        btnRegister.setBackground(new Color(16, 185, 129));
        btnRegister.setForeground(Color.BLACK);
        btnRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnRegister.setFocusPainted(false);
        btnRegister.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnExit = new JButton("❌ Exit");
        btnExit.setBackground(new Color(220, 20, 60));
        btnExit.setForeground(Color.BLACK);
        btnExit.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnExit.setFocusPainted(false);
        btnExit.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        bottomPanel.add(btnRegister);
        bottomPanel.add(btnExit);
        mainPanel.add(bottomPanel);
        mainPanel.add(Box.createVerticalStrut(40));
        
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void setListeners() {
        btnLogin.addActionListener(e -> prosesLogin());
        btnRegister.addActionListener(e -> bukaFormRegister());
        btnExit.addActionListener(e -> System.exit(0));
        
        txtPassword.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    prosesLogin();
                }
            }
        });
    }
    
    private void prosesLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        
        if (username.isEmpty() || password.isEmpty()) {
            lblMessage.setText("❌ Username dan password tidak boleh kosong!");
            return;
        }
        
        User user = User.login(username, password);
        
        if (user != null) {
            lblMessage.setText("✅ Login berhasil! Selamat datang, " + user.getUsername());
            lblMessage.setForeground(new Color(16, 185, 129));
            loggedInUser = user;
            
            Timer timer = new Timer(1000, e -> {
                dispose();
                new FormDashboard(user);
            });
            timer.setRepeats(false);
            timer.start();
        } else {
            lblMessage.setText("❌ Username atau password salah!");
        }
    }
    
    private void bukaFormRegister() {
        JTextField txtRegUsername = new JTextField(15);
        JTextField txtRegEmail = new JTextField(15);
        JPasswordField txtRegPassword = new JPasswordField(15);
        JPasswordField txtRegConfirm = new JPasswordField(15);
        JTextField txtRegNoHp = new JTextField(15);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        
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
        
        int result = JOptionPane.showConfirmDialog(
            this,
            panel,
            "Form Register",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );
        
        if (result == JOptionPane.OK_OPTION) {
            String username = txtRegUsername.getText().trim();
            String email = txtRegEmail.getText().trim();
            String password = new String(txtRegPassword.getPassword()).trim();
            String confirm = new String(txtRegConfirm.getPassword()).trim();
            String noHp = txtRegNoHp.getText().trim();
            
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username dan password harus diisi!");
                return;
            }
            
            if (!password.equals(confirm)) {
                JOptionPane.showMessageDialog(this, "Password dan konfirmasi password tidak cocok!");
                return;
            }
            
            if (User.isUsernameExist(username)) {
                JOptionPane.showMessageDialog(this, "Username sudah terdaftar!");
                return;
            }
            
            User newUser = new User(username, email, password, noHp, "member", 0);
            User.register(newUser);
            JOptionPane.showMessageDialog(this, "✅ Registrasi berhasil! Silakan login.");
        }
    }
    
    public User getLoggedInUser() {
        return loggedInUser;
    }
}
