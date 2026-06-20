package form;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JFrame {
    
    private JProgressBar progressBar;
    private JLabel lblLoading;
    
    public SplashScreen() {
        initComponents();
        startLoading();
    }
    
    private void initComponents() {
        setTitle("Topup Game Store");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        
        getContentPane().setBackground(new Color(236, 72, 153));
        setLayout(new BorderLayout());
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(236, 72, 153));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(236, 72, 153));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        
        JLabel lblLogo = new JLabel("🎮");
        lblLogo.setFont(new Font("Dialog", Font.PLAIN, 100));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(lblLogo);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        JLabel lblTitle = new JLabel("TOPUP GAME STORE");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 32));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(lblTitle);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JLabel lblSubtitle = new JLabel("Topup Diamond & Item Game Favoritmu");
        lblSubtitle.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblSubtitle.setForeground(new Color(236, 72, 153));
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(lblSubtitle);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        JPanel southPanel = new JPanel();
        southPanel.setBackground(new Color(236, 72, 153));
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 30, 30));
        
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.GREEN);
        progressBar.setBackground(new Color(236, 72, 153));
        progressBar.setFont(new Font("Dialog", Font.BOLD, 13));
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        progressBar.setPreferredSize(new Dimension(400, 25));
        southPanel.add(progressBar);
        southPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        
        lblLoading = new JLabel("Loading... 0%");
        lblLoading.setFont(new Font("Dialog", Font.PLAIN, 13));
        lblLoading.setForeground(new Color(220, 220, 255));
        lblLoading.setAlignmentX(Component.CENTER_ALIGNMENT);
        southPanel.add(lblLoading);
        southPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JLabel lblFooter = new JLabel("© 2026 Topup Game Store");
        lblFooter.setFont(new Font("Dialog", Font.PLAIN, 11));
        lblFooter.setForeground(Color.WHITE);
        lblFooter.setAlignmentX(Component.CENTER_ALIGNMENT);
        southPanel.add(lblFooter);
        
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void startLoading() {
        Timer timer = new Timer(30, e -> {
            int value = progressBar.getValue();
            if (value < 100) {
                progressBar.setValue(value + 1);
                lblLoading.setText("Loading... " + (value + 1) + "%");
            } else {
                ((Timer) e.getSource()).stop();
                openLoginForm();
            }
        });
        timer.start();
    }
    
    private void openLoginForm() {
        dispose();
        new FormLogin().setVisible(true);
    }
}
