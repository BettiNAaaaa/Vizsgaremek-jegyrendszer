/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Security;

import java.util.HashMap;
import model.User;

/**
 *
 * @author joska
 */
public class SessionMananger {
    private static final HashMap<String, User> sessions = new HashMap<>();

    public static void startSession(String token, User user) {
        sessions.put(token, user);
    }

    public static User getUser(String token) {
        return sessions.get(token);
    }

    public static void logout(String token) {
        sessions.remove(token);
    }
    
}

