/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;



import config.Database;
import dao.Database;
import model.User;

import java.sql.*;

public class UserRepository {

    public User findByUsername(String username) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM users WHERE username = ? LIMIT 1"
            );
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("passwordHash"),
                        rs.getBoolean("isAdmin")
                );
            }
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public boolean create(User user) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO users(username, passwordHash, isAdmin) VALUES (?, ?, ?)"
            );
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ps.setBoolean(3, user.isAdmin());
            return ps.executeUpdate() > 0;

        } catch (Exception e) { return false; }
    }
}

