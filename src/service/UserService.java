/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.userDAO;
import model.User;

public class UserService {

    private userDAO userDao = new userDAO();

    public User login(String email, String password) {

        User u = userDao.findByEmail(email);

        if (u == null) return null;
        if (!u.getPassword().equals(password)) return null;

        return u;
    }

    public boolean register(String name, String email, String password) {
        if (userDao.findByEmail(email) != null) {
            return false; // email foglalt
        }

        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setPassword(password);
        u.setRole("USER");

        return userDao.save(u);
    }

    public boolean isAdmin(User u) {
        return u.getRole().equals("ADMIN");
    }

    public java.util.List<User> getAll() {
        return userDao.findAll();
    }
}