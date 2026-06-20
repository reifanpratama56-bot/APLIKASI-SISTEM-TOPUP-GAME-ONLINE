package model;

import java.util.*;

public class PromoManager {
    
    private static List<Promo> activePromos = new ArrayList<>();
    
    static {
        activePromos.add(new Promo("WEEKEND20", "Diskon 20% semua game", 20, "percent"));
        activePromos.add(new Promo("FREEADMIN", "Gratis biaya admin", 0, "free_admin"));
    }
    
    public static List<Promo> getActivePromos() {
        return activePromos;
    }
    
    public static int applyPromo(String promoCode, int amount, int adminFee) {
        for (Promo p : activePromos) {
            if (p.code.equalsIgnoreCase(promoCode)) {
                switch (p.type) {
                    case "percent":
                        int diskon = amount * p.value / 100;
                        return amount - diskon;
                    case "free_admin":
                        return amount;
                    default:
                        return amount;
                }
            }
        }
        return amount;
    }
    
    public static int applyPromoWithAdmin(String promoCode, int amount, int adminFee) {
        for (Promo p : activePromos) {
            if (p.code.equalsIgnoreCase(promoCode)) {
                switch (p.type) {
                    case "percent":
                        int total = amount + adminFee;
                        int diskon = total * p.value / 100;
                        return total - diskon;
                    case "free_admin":
                        return amount;
                    default:
                        return amount + adminFee;
                }
            }
        }
        return amount + adminFee;
    }
    
    public static boolean isValidPromo(String promoCode) {
        for (Promo p : activePromos) {
            if (p.code.equalsIgnoreCase(promoCode)) {
                return true;
            }
        }
        return false;
    }
    
    public static String getPromoType(String promoCode) {
        for (Promo p : activePromos) {
            if (p.code.equalsIgnoreCase(promoCode)) {
                return p.type;
            }
        }
        return null;
    }
    
    public static String getPromoDescription(String promoCode) {
        for (Promo p : activePromos) {
            if (p.code.equalsIgnoreCase(promoCode)) {
                return p.name;
            }
        }
        return null;
    }
    
    public static int getPromoValue(String promoCode) {
        for (Promo p : activePromos) {
            if (p.code.equalsIgnoreCase(promoCode)) {
                return p.value;
            }
        }
        return 0;
    }
    
    public static boolean isFreeAdminPromo(String promoCode) {
        for (Promo p : activePromos) {
            if (p.code.equalsIgnoreCase(promoCode) && p.type.equals("free_admin")) {
                return true;
            }
        }
        return false;
    }
    
    static class Promo {
        String code, name, type;
        int value;
        
        Promo(String code, String name, int value, String type) {
            this.code = code;
            this.name = name;
            this.value = value;
            this.type = type;
        }
    }
}
