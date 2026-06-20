package model;

import java.util.HashMap;
import java.util.Map;

public class User {
    
    private String username;
    private String email;
    private String password;
    private String noHp;
    private String role;
    private int poin;
    private int totalTopup;
    
    private static Map<String, User> dataUser = new HashMap<>();
    
    static {
        dataUser.put("reifan", new User("reifan", "reifan@gmail.com", "reifan123", 
                                        "081234567890", "reifan", 0, 0));
        dataUser.put("linda", new User("linda", "linda@gmail.com", "linda123", 
                                       "081234567890", "linda", 0, 0));
        dataUser.put("fina", new User("fina", "fina@gmail.com", "fina123", 
                                      "081234567890", "fina", 0, 0));
        dataUser.put("andi", new User("andi", "andi@gmail.com", "andi123", 
                                      "081234567890", "andi", 0, 0));
        dataUser.put("arly", new User("arly", "arly@gmail.com", "arly123", 
                                      "081234567890", "arly", 0, 0));
    }
    
    public User(String username, String email, String password, String noHp, String role, int poin) {
        this(username, email, password, noHp, role, poin, 0);
    }
    
    public User(String username, String email, String password, String noHp, String role, int poin, int totalTopup) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.noHp = noHp;
        this.role = role;
        this.poin = poin;
        this.totalTopup = totalTopup;
    }
    
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getNoHp() { return noHp; }
    public String getRole() { return role; }
    public int getPoin() { return poin; }
    public int getTotalTopup() { return totalTopup; }
    
    public void setPoin(int poin) { this.poin = poin; }
    public void tambahPoin(int poin) { this.poin += poin; }
    public void tambahTotalTopup(int amount) { this.totalTopup += amount; }
    
    public static User login(String username, String password) {
        User user = dataUser.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    public static void register(User user) {
        dataUser.put(user.getUsername(), user);
    }
    
    public static boolean isUsernameExist(String username) {
        return dataUser.containsKey(username);
    }
    
    public static Map<String, User> getAllUsers() { 
        return dataUser; 
    }
    
    public static User getUserByUsername(String username) {
        return dataUser.get(username);
    }
    
    public static void updateUserAfterTopup(String username, int amount) {
        User user = dataUser.get(username);
        if (user != null) {
            int poinEarned = amount / 10000;
            user.tambahPoin(poinEarned);
            user.tambahTotalTopup(amount);
            System.out.println("✅ User " + username + " mendapat " + poinEarned + 
                               " poin! Total poin: " + user.getPoin());
        }
    }
}
