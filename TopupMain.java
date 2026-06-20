package main;

import form.SplashScreen;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TopupMain {
    public static void main(String[] args) {
        try {   
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new SplashScreen().setVisible(true);
        }); 
    }
}
