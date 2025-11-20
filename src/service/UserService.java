/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.UserDAO;
import model.User;
import java.sql.SQLException;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean register(User user) throws SQLException {
        User existing = userDAO.getByEmail(user.getEmail());
        if (existing != null) {
            System.out.println("Ez az e-mail már létezik!");
            return false;
        }
        userDAO.addUser(user);
        return true;
    }

    public User login(String email, String password) throws SQLException {
        User user = userDAO.getByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        if (user != null && user.getPassword().equals(password)) {
        return user; 
    }
        
        return null;
    }
    
    public boolean isAdmin(User user) {
    return user.getRole().equalsIgnoreCase("ADMIN");
}
    
   
}

