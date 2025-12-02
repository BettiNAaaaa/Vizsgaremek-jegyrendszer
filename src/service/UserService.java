/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Security.PasswordHasher;
import Security.SessionManager;
import model.User;
import repository.UserRepository;

import java.util.UUID;

public class UserService {

    private final UserRepository repo = new UserRepository();

    public String login(String username, String password) {
        User user = repo.findByUsername(username);

        if (user == null) return null;
        if (!user.getPasswordHash().equals(PasswordHasher.hash(password))) return null;

        String token = UUID.randomUUID().toString();
        SessionManager.startSession(token, user);
        return token;
    }

    public boolean register(String username, String password) {
        User u = new User(0, username, PasswordHasher.hash(password), false);
        return repo.create(u);
    }

    private static class SessionManager {

        private static void startSession(String token, User user) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public SessionManager() {
        }
    }
}