package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Transaction {
    
    private String kodeTransaksi;
    private String gameName;
    private String nominalItem;
    private int hargaAwal;
    private String metodeBayar;
    private int biayaAdmin;
    private double diskonPersen;
    private int hargaFinal;
    private String userId;
    private String email;
    private String tanggal;
    private String status;
    private String vaNumber;
    private String username;
    
    private static Map<String, Transaction> dataTransaksi = new HashMap<>();
    
    public Transaction(String gameName, String nominalItem, int hargaAwal, 
                       String metodeBayar, int biayaAdmin, double diskonPersen, 
                       int hargaFinal, String userId, String email) {
        this(gameName, nominalItem, hargaAwal, metodeBayar, biayaAdmin, diskonPersen, 
             hargaFinal, userId, email, null);
    }
    
    public Transaction(String gameName, String nominalItem, int hargaAwal, 
                       String metodeBayar, int biayaAdmin, double diskonPersen, 
                       int hargaFinal, String userId, String email, String username) {
        
        this.kodeTransaksi = "TRX" + System.currentTimeMillis() + 
                             UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        
        this.gameName = gameName;
        this.nominalItem = nominalItem;
        this.hargaAwal = hargaAwal;
        this.metodeBayar = metodeBayar;
        this.biayaAdmin = biayaAdmin;
        this.diskonPersen = diskonPersen;
        this.hargaFinal = hargaFinal;
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.tanggal = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        this.status = "WAITING_PAYMENT";
        this.vaNumber = generateVANumber();
    }
    
    private String generateVANumber() {
        return "8810" + System.currentTimeMillis() % 10000000000L;
    }
    
    public void setStatusLunas() { this.status = "PAYMENT_SUCCESS"; }
    public void setStatusGagal() { this.status = "PAYMENT_FAILED"; }
    public void setStatusDiproses() { this.status = "PROCESSING"; }
    public void setStatusWaiting() { this.status = "WAITING_PAYMENT"; }
    
    public String getKodeTransaksi() { return kodeTransaksi; }
    public String getGameName() { return gameName; }
    public String getNominalItem() { return nominalItem; }
    public int getHargaAwal() { return hargaAwal; }
    public String getMetodeBayar() { return metodeBayar; }
    public int getBiayaAdmin() { return biayaAdmin; }
    public double getDiskonPersen() { return diskonPersen; }
    public int getHargaFinal() { return hargaFinal; }
    public String getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getTanggal() { return tanggal; }
    public String getStatus() { return status; }
    public String getVaNumber() { return vaNumber; }
    public String getUsername() { return username; }
    
    public static void simpanTransaksi(Transaction transaksi) {
        dataTransaksi.put(transaksi.getKodeTransaksi(), transaksi);
    }
    
    public static void updateStatusTransaksi(String kodeTransaksi, String statusBaru) {
        Transaction transaksi = dataTransaksi.get(kodeTransaksi);
        if (transaksi != null) {
            if (statusBaru.equals("PAYMENT_SUCCESS")) {
                transaksi.setStatusLunas();
            } else if (statusBaru.equals("PAYMENT_FAILED")) {
                transaksi.setStatusGagal();
            } else if (statusBaru.equals("PROCESSING")) {
                transaksi.setStatusDiproses();
            }
            System.out.println("Status transaksi " + kodeTransaksi + " diupdate menjadi: " + statusBaru);
        }
    }
    
    public static Map<String, Transaction> getAllTransaksi() { 
        return dataTransaksi; 
    }
    
    public static Map<String, Transaction> getTransaksiByUsername(String username) {
        Map<String, Transaction> result = new HashMap<>();
        for (Map.Entry<String, Transaction> entry : dataTransaksi.entrySet()) {
            if (entry.getValue().getUsername() != null && 
                entry.getValue().getUsername().equals(username)) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }
}
