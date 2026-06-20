//CODE CLASS MAIN (BUAT RUN DISINI

package main;

import form.SplashScreen;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class TopupMain {
    public static void main(String[] args) {
        // Set look and feel
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