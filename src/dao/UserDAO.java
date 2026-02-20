package dao;

import database.Database;  
import model.Role;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

   
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapUser(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    
    public boolean deleteByUsername(String username) {
        String sql = "DELETE FROM users WHERE username = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            int affected = ps.executeUpdate();

            return affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private User mapUser(ResultSet rs) throws SQLException {
        
    int id = rs.getInt("id");
    String name = rs.getString("name");
    String email = rs.getString("email");
    String username = rs.getString("username");
    String password = rs.getString("password");

    String roleStr = rs.getString("role"); 
    Role role = Role.valueOf(roleStr);

    return new User(id, name, email, username, password, role);
}
    
    public User findByEmail(String email) {

    String sql = "SELECT * FROM users WHERE username = ?";

    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, email);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return mapUser(rs);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}
    
    public boolean deleteById(int id) {
    String sql = "DELETE FROM users WHERE id = ?";

    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);
        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public void add(User user) {
        String sql = "INSERT INTO users (email, password, name, role) VALUES (?, ?, ?, ?)";

    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getName());

        // DB: enum('admin','user') -> kisbetű!
        ps.setString(4, user.getRole().name().toLowerCase());

        ps.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}}