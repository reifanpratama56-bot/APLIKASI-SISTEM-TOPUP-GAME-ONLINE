// ════════════════════════════════════════════════════════════════════════════════════
// 📌 FILE: Game.java
// 📝 FUNGSI: Menyimpan data semua game yang tersedia untuk topup beserta nominalnya
// 🔧 DAPAT DIUBAH: Tambah game baru, ubah nominal, ubah harga
// ════════════════════════════════════════════════════════════════════════════════════

package model;

// 📦 IMPORT LIBRARY
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// ════════════════════════════════════════════════════════════════════════════════════
// 📌 CLASS: Game
// 📝 FUNGSI: Blueprint untuk objek Game yang menyimpan informasi game
// 🔧 DAPAT DIUBAH: Menambah atribut baru (misal: gambar, genre, dll)
// ════════════════════════════════════════════════════════════════════════════════════
public class Game {
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 ATRIBUT / PROPERTIES GAME
    // ──────────────────────────────────────────────────────────────────────────────
    private String kodeGame;                    // 📌 Kode unik game (Primary Key)
    private String namaGame;                    // 📌 Nama lengkap game
    private String publisher;                   // 📌 Nama publisher/developer game
    private Map<String, Integer> nominalTopup;  // 📌 Map (Nama nominal → Harga dalam Rupiah)
    
    // ──────────────────────────────────────────────────────────────────────────────
    // 📌 DATABASE GAME (Static)
    // 📝 FUNGSI: Menyimpan semua data game dalam bentuk HashMap
    // 🔧 DAPAT DIUBAH: HashMap → LinkedHashMap jika ingin menjaga urutan
    // ──────────────────────────────────────────────────────────────────────────────
    private static Map<String, Game> daftarGame = new HashMap<>();
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 STATIC BLOCK - INISIALISASI DATA GAME
    // 📝 FUNGSI: Dijalankan OTOMATIS saat class Game pertama kali di-load
    // 🔧 DAPAT DIUBAH: Tambah game baru, ubah nominal, ubah harga
    // ════════════════════════════════════════════════════════════════════════════════
    static {
        
        // ═══════════════════════════════════════════════════════════════════════════
        // 🎮 GAME 1: CALL OF DUTY MOBILE
        // 📝 Publisher: Activision | 💰 Mata Uang: CP (COD Points)
        // ═══════════════════════════════════════════════════════════════════════════
        Map<String, Integer> codmNominal = new LinkedHashMap<>();
        codmNominal.put("80 CP", 15000);
        codmNominal.put("400 CP", 73000);
        codmNominal.put("800 CP", 145000);
        codmNominal.put("2000 CP", 360000);
        codmNominal.put("4000 CP", 720000);
        codmNominal.put("10000 CP", 1800000);
        daftarGame.put("CODM01", new Game("CODM01", "Call of Duty Mobile", "Activision", codmNominal));
        
        // ═══════════════════════════════════════════════════════════════════════════
        // 🎮 GAME 2: MOBILE LEGENDS
        // 📝 Publisher: Moonton | 💰 Mata Uang: Diamonds
        // ═══════════════════════════════════════════════════════════════════════════
        Map<String, Integer> mlNominal = new LinkedHashMap<>();
        mlNominal.put("86 Diamonds", 18000);
        mlNominal.put("172 Diamonds", 35000);
        mlNominal.put("257 Diamonds", 52000);
        mlNominal.put("344 Diamonds", 69000);
        mlNominal.put("429 Diamonds", 86000);
        mlNominal.put("514 Diamonds", 103000);
        mlNominal.put("706 Diamonds", 141000);
        mlNominal.put("963 Diamonds", 192000);
        mlNominal.put("1412 Diamonds", 282000);
        mlNominal.put("2195 Diamonds", 439000);
        daftarGame.put("ML01", new Game("ML01", "Mobile Legends", "Moonton", mlNominal));
        
        // ═══════════════════════════════════════════════════════════════════════════
        // 🎮 GAME 3: FREE FIRE
        // 📝 Publisher: Garena | 💰 Mata Uang: Diamonds
        // ═══════════════════════════════════════════════════════════════════════════
        Map<String, Integer> ffNominal = new LinkedHashMap<>();
        ffNominal.put("100 Diamonds", 15000);
        ffNominal.put("210 Diamonds", 30000);
        ffNominal.put("355 Diamonds", 50000);
        ffNominal.put("520 Diamonds", 73000);
        ffNominal.put("720 Diamonds", 100000);
        ffNominal.put("1060 Diamonds", 147000);
        ffNominal.put("2180 Diamonds", 300000);
        daftarGame.put("FF01", new Game("FF01", "Free Fire", "Garena", ffNominal));
        
        // ═══════════════════════════════════════════════════════════════════════════
        // 🎮 GAME 4: PUBG MOBILE
        // 📝 Publisher: Tencent Games | 💰 Mata Uang: UC (Unknown Cash)
        // ═══════════════════════════════════════════════════════════════════════════
        Map<String, Integer> pubgNominal = new LinkedHashMap<>();
        pubgNominal.put("60 UC", 15000);
        pubgNominal.put("125 UC", 30000);
        pubgNominal.put("250 UC", 59000);
        pubgNominal.put("500 UC", 118000);
        pubgNominal.put("1000 UC", 235000);
        pubgNominal.put("2000 UC", 470000);
        daftarGame.put("PUBG01", new Game("PUBG01", "PUBG Mobile", "Tencent Games", pubgNominal));
        
        // ═══════════════════════════════════════════════════════════════════════════
        // 🎮 GAME 5: GENSHIN IMPACT
        // 📝 Publisher: HoYoverse | 💰 Mata Uang: Genesis Crystal
        // ═══════════════════════════════════════════════════════════════════════════
        Map<String, Integer> giNominal = new LinkedHashMap<>();
        giNominal.put("60 Genesis Crystal", 15000);
        giNominal.put("300 Genesis Crystal", 73000);
        giNominal.put("980 Genesis Crystal", 235000);
        giNominal.put("1980 Genesis Crystal", 470000);
        giNominal.put("3280 Genesis Crystal", 775000);
        giNominal.put("6480 Genesis Crystal", 1525000);
        daftarGame.put("GI01", new Game("GI01", "Genshin Impact", "HoYoverse", giNominal));
        
        // ═══════════════════════════════════════════════════════════════════════════
        // 🎮 GAME 6: HONOR OF KINGS
        // 📝 Publisher: Tencent Games | 💰 Mata Uang: Points
        // ═══════════════════════════════════════════════════════════════════════════
        Map<String, Integer> hokNominal = new LinkedHashMap<>();
        hokNominal.put("60 Points", 15000);
        hokNominal.put("300 Points", 75000);
        hokNominal.put("600 Points", 145000);
        hokNominal.put("1200 Points", 285000);
        hokNominal.put("2500 Points", 570000);
        hokNominal.put("5000 Points", 1120000);
        hokNominal.put("10000 Points", 2200000);
        daftarGame.put("HOK01", new Game("HOK01", "Honor of Kings", "Tencent Games", hokNominal));
        
        // ═══════════════════════════════════════════════════════════════════════════
        // 🎮 GAME 7: ROBLOX
        // 📝 Publisher: Roblox Corporation | 💰 Mata Uang: Robux
        // ═══════════════════════════════════════════════════════════════════════════
        Map<String, Integer> robloxNominal = new LinkedHashMap<>();
        robloxNominal.put("400 Robux", 50000);
        robloxNominal.put("800 Robux", 100000);
        robloxNominal.put("1700 Robux", 200000);
        robloxNominal.put("4500 Robux", 500000);
        robloxNominal.put("10000 Robux", 1000000);
        robloxNominal.put("22000 Robux", 2100000);
        daftarGame.put("RBX01", new Game("RBX01", "Roblox", "Roblox Corporation", robloxNominal));
        
        // ═══════════════════════════════════════════════════════════════════════════
        // 🎮 GAME 8: STEAM WALLET
        // 📝 Publisher: Valve | 💰 Mata Uang: Rupiah (Wallet topup)
        // ═══════════════════════════════════════════════════════════════════════════
        Map<String, Integer> steamNominal = new LinkedHashMap<>();
        steamNominal.put("Rp 50.000", 50000);
        steamNominal.put("Rp 100.000", 100000);
        steamNominal.put("Rp 200.000", 200000);
        steamNominal.put("Rp 500.000", 500000);
        steamNominal.put("Rp 1.000.000", 1000000);
        daftarGame.put("STEAM01", new Game("STEAM01", "Steam Wallet", "Valve", steamNominal));
        
        // ═══════════════════════════════════════════════════════════════════════════
        // 🎮 GAME 9: VALORANT
        // 📝 Publisher: Riot Games | 💰 Mata Uang: VP (Valorant Points)
        // ═══════════════════════════════════════════════════════════════════════════
        Map<String, Integer> valNominal = new LinkedHashMap<>();
        valNominal.put("125 VP", 20000);
        valNominal.put("420 VP", 65000);
        valNominal.put("700 VP", 108000);
        valNominal.put("1375 VP", 210000);
        valNominal.put("2875 VP", 440000);
        valNominal.put("5850 VP", 880000);
        valNominal.put("11750 VP", 1760000);
        daftarGame.put("VAL01", new Game("VAL01", "Valorant", "Riot Games", valNominal));
        
        // ═══════════════════════════════════════════════════════════════════════════
        // 🎮 GAME 10: CLASH OF CLANS
        // 📝 Publisher: Supercell | 💰 Mata Uang: Gems
        // ═══════════════════════════════════════════════════════════════════════════
        Map<String, Integer> cocNominal = new LinkedHashMap<>();
        cocNominal.put("500 Gems", 50000);
        cocNominal.put("1200 Gems", 110000);
        cocNominal.put("2500 Gems", 220000);
        cocNominal.put("6500 Gems", 550000);
        cocNominal.put("14000 Gems", 1100000);
        cocNominal.put("25000 Gems", 1950000);
        daftarGame.put("COC01", new Game("COC01", "Clash of Clans", "Supercell", cocNominal));
        
        // ═══════════════════════════════════════════════════════════════════════════
        // 🎮 GAME 11: DOTA 2
        // 📝 Publisher: Valve | 💰 Mata Uang: Dota Plus
        // ═══════════════════════════════════════════════════════════════════════════
        Map<String, Integer> dotaNominal = new LinkedHashMap<>();
        dotaNominal.put("100 Dota Plus", 25000);
        dotaNominal.put("250 Dota Plus", 60000);
        dotaNominal.put("500 Dota Plus", 115000);
        dotaNominal.put("1000 Dota Plus", 225000);
        dotaNominal.put("2500 Dota Plus", 550000);
        dotaNominal.put("5000 Dota Plus", 1080000);
        daftarGame.put("DOTA01", new Game("DOTA01", "Dota 2", "Valve", dotaNominal));
        
        // ═══════════════════════════════════════════════════════════════════════════
        // 🎮 GAME 12: ARENA OF VALOR
        // 📝 Publisher: Tencent Games | 💰 Mata Uang: Vouchers
        // ═══════════════════════════════════════════════════════════════════════════
        Map<String, Integer> aovNominal = new LinkedHashMap<>();
        aovNominal.put("100 Vouchers", 15000);
        aovNominal.put("250 Vouchers", 37000);
        aovNominal.put("500 Vouchers", 73000);
        aovNominal.put("1000 Vouchers", 145000);
        aovNominal.put("2000 Vouchers", 285000);
        aovNominal.put("5000 Vouchers", 710000);
        aovNominal.put("10000 Vouchers", 1410000);
        daftarGame.put("AOV01", new Game("AOV01", "Arena of Valor", "Tencent Games", aovNominal));
        
        // ═══════════════════════════════════════════════════════════════════════════
        // 🎮 GAME 13: WILD RIFT
        // 📝 Publisher: Riot Games | 💰 Mata Uang: Wild Cores
        // ═══════════════════════════════════════════════════════════════════════════
        Map<String, Integer> wrNominal = new LinkedHashMap<>();
        wrNominal.put("100 Wild Cores", 15000);
        wrNominal.put("250 Wild Cores", 37000);
        wrNominal.put("500 Wild Cores", 73000);
        wrNominal.put("1000 Wild Cores", 145000);
        wrNominal.put("2500 Wild Cores", 360000);
        wrNominal.put("5000 Wild Cores", 715000);
        daftarGame.put("WR01", new Game("WR01", "Wild Rift", "Riot Games", wrNominal));
        
        // ═══════════════════════════════════════════════════════════════════════════
        // 🎮 GAME 14: AMONG US
        // 📝 Publisher: Innersloth | 💰 Mata Uang: Stars
        // ═══════════════════════════════════════════════════════════════════════════
        Map<String, Integer> amongNominal = new LinkedHashMap<>();
        amongNominal.put("2000 Stars", 25000);
        amongNominal.put("5000 Stars", 60000);
        amongNominal.put("12000 Stars", 140000);
        amongNominal.put("25000 Stars", 280000);
        daftarGame.put("AMONG01", new Game("AMONG01", "Among Us", "Innersloth", amongNominal));
        
        // ═══════════════════════════════════════════════════════════════════════════
        // 🎮 GAME 15: BRAWL STARS
        // 📝 Publisher: Supercell | 💰 Mata Uang: Gems
        // ═══════════════════════════════════════════════════════════════════════════
        Map<String, Integer> brawlNominal = new LinkedHashMap<>();
        brawlNominal.put("80 Gems", 50000);
        brawlNominal.put("170 Gems", 100000);
        brawlNominal.put("360 Gems", 210000);
        brawlNominal.put("950 Gems", 550000);
        daftarGame.put("BRAWL01", new Game("BRAWL01", "Brawl Stars", "Supercell", brawlNominal));
        
        // ═══════════════════════════════════════════════════════════════════════════
        // 🎮 GAME 16: MAGIC CHESS GO GO
        // 📝 Publisher: Moonton | 💰 Mata Uang: Magic Crystals
        // ═══════════════════════════════════════════════════════════════════════════
        Map<String, Integer> magicChessNominal = new LinkedHashMap<>();
        magicChessNominal.put("100 Magic Crystals", 15000);
        magicChessNominal.put("250 Magic Crystals", 37000);
        magicChessNominal.put("500 Magic Crystals", 73000);
        magicChessNominal.put("1000 Magic Crystals", 145000);
        magicChessNominal.put("2000 Magic Crystals", 285000);
        magicChessNominal.put("5000 Magic Crystals", 710000);
        daftarGame.put("MCG01", new Game("MCG01", "Magic Chess Go Go", "Moonton", magicChessNominal));
        
       
        // ────────────────────────────────────────────────────────────────────────────
        // ➕ [TAMBAH] - Cara menambahkan game baru:
        // ────────────────────────────────────────────────────────────────────────────
        // Map<String, Integer> gameBaruNominal = new LinkedHashMap<>();
        // gameBaruNominal.put("100 Item", 15000);
        // gameBaruNominal.put("200 Item", 30000);
        // daftarGame.put("KODE01", new Game("KODE01", "Nama Game", "Publisher", gameBaruNominal));
        // ────────────────────────────────────────────────────────────────────────────
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 CONSTRUCTOR
    // 📝 FUNGSI: Membuat objek Game baru
    // ════════════════════════════════════════════════════════════════════════════════
    public Game(String kodeGame, String namaGame, String publisher, Map<String, Integer> nominalTopup) {
        this.kodeGame = kodeGame;
        this.namaGame = namaGame;
        this.publisher = publisher;
        this.nominalTopup = nominalTopup;
    }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 GETTER METHODS
    // 📝 FUNGSI: Mengambil nilai dari atribut private
    // ════════════════════════════════════════════════════════════════════════════════
    public String getKodeGame() { return kodeGame; }
    public String getNamaGame() { return namaGame; }
    public String getPublisher() { return publisher; }
    public Map<String, Integer> getNominalTopup() { return nominalTopup; }
    
    // ════════════════════════════════════════════════════════════════════════════════
    // 📌 STATIC METHODS - MANAJEMEN DATA GAME
    // ════════════════════════════════════════════════════════════════════════════════
    
    // 📌 Mengambil semua data game
    public static Map<String, Game> getAllGame() { return daftarGame; }
    
    // 📌 Mencari game berdasarkan kode
    public static Game getGameByKode(String kode) { return daftarGame.get(kode); }
    
    // 📌 Mengambil semua nama game dalam bentuk array
    public static String[] getDaftarNamaGame() {
        String[] namaGame = new String[daftarGame.size()];
        int i = 0;
        for (Game g : daftarGame.values()) {
            namaGame[i++] = g.getNamaGame();
        }
        return namaGame;
    }
    
    // 📌 Mencari game berdasarkan nama (case insensitive)
    public static Game getGameByNama(String nama) {
        for (Game g : daftarGame.values()) {
            if (g.getNamaGame().equalsIgnoreCase(nama)) {
                return g;
            }
        }
        return null;
    }
    
    // 📌 Mengambil daftar nominal yang sudah diurutkan dari yang termurah
    public List<Map.Entry<String, Integer>> getSortedNominal() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(nominalTopup.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        return list;
    }
}