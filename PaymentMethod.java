package model;

import java.util.HashMap;
import java.util.Map;

public class PaymentMethod {
    
    private String kodeMetode;
    private String namaMetode;
    private int biayaAdmin;
    private double diskonPersen;
    private String bankName;
    private String vaNumber;
    
    private static Map<String, PaymentMethod> daftarMetode = new HashMap<>();
    
    static {
        daftarMetode.put("DANA", new PaymentMethod("DANA", "Dana", 2500, 0, null, null));
        daftarMetode.put("GOPAY", new PaymentMethod("GOPAY", "GoPay", 2000, 0, null, null));
        daftarMetode.put("OVO", new PaymentMethod("OVO", "Ovo", 1500, 0, null, null));
        daftarMetode.put("SHOPEEPAY", new PaymentMethod("SHOPEEPAY", "ShopeePay", 2000, 5, null, null));
    }
    
    public PaymentMethod(String kodeMetode, String namaMetode, int biayaAdmin, double diskonPersen, String bankName, String vaNumber) {
        this.kodeMetode = kodeMetode;
        this.namaMetode = namaMetode;
        this.biayaAdmin = biayaAdmin;
        this.diskonPersen = diskonPersen;
        this.bankName = bankName;
        this.vaNumber = vaNumber;
    }
    
    public String getKodeMetode() { return kodeMetode; }
    public String getNamaMetode() { return namaMetode; }
    public int getBiayaAdmin() { return biayaAdmin; }
    public double getDiskonPersen() { return diskonPersen; }
    public String getBankName() { return bankName; }
    public String getVaNumber() { return vaNumber; }
    public boolean isBank() { return bankName != null; }
    
    public static Map<String, PaymentMethod> getAllMetode() {
        return daftarMetode;
    }
    
    public static PaymentMethod getMetodeByNama(String nama) {
        for (PaymentMethod pm : daftarMetode.values()) {
            if (pm.getNamaMetode().equalsIgnoreCase(nama)) {
                return pm;
            }
        }
        return null;
    }
    
    public static String[] getDaftarNamaMetode() {
        String[] namaMetode = new String[daftarMetode.size()];
        int i = 0;
        for (PaymentMethod pm : daftarMetode.values()) {
            namaMetode[i++] = pm.getNamaMetode();
        }
        return namaMetode;
    }
}
