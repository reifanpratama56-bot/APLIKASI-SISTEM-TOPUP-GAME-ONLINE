package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class ImageLoader {
    
    public static ImageIcon loadGameIcon(String fileName, int width, int height) {
        try {
            URL imgURL = ImageLoader.class.getResource("/images/games/" + fileName);
            if (imgURL != null) {
                ImageIcon originalIcon = new ImageIcon(imgURL);
                Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
            } else {
                System.out.println("⚠️ Game image not found: " + fileName);
                return createPlaceholderIcon(fileName, width, height, new Color(99, 102, 241));
            }
        } catch (Exception e) {
            System.out.println("❌ Error loading game: " + fileName);
            return createPlaceholderIcon(fileName, width, height, new Color(99, 102, 241));
        }
    }
    
    public static ImageIcon loadPaymentIcon(String fileName, int width, int height) {
        try {
            URL imgURL = ImageLoader.class.getResource("/images/payments/" + fileName);
            if (imgURL != null) {
                ImageIcon originalIcon = new ImageIcon(imgURL);
                Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                System.out.println("✅ Load payment: " + fileName);
                return new ImageIcon(scaledImage);
            } else {
                System.out.println("⚠️ Payment image not found: " + fileName);
                return createPlaceholderIcon(fileName, width, height, new Color(16, 185, 129));
            }
        } catch (Exception e) {
            System.out.println("❌ Error loading payment: " + fileName);
            return createPlaceholderIcon(fileName, width, height, new Color(16, 185, 129));
        }
    }
    
    private static ImageIcon createPlaceholderIcon(String fileName, int width, int height, Color color) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0, 0, color, width, height, color.darker());
        g2d.setPaint(gp);
        g2d.fillRoundRect(0, 0, width, height, 15, 15);
        
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Dialog", Font.BOLD, width / 3));
        String text = fileName.substring(0, Math.min(2, fileName.length())).toUpperCase();
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        g2d.drawString(text, (width - textWidth) / 2, (height + textHeight) / 2 - 4);
        g2d.dispose();
        
        return new ImageIcon(image);
    }
}
