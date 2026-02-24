package dao;

import model.Role;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public User findByEmail(String email) {
        String sql = "SELECT id, email, password, name, role FROM users WHERE email = ?";

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
    
    public int countAdmins() {
    String sql = "SELECT COUNT(*) FROM users WHERE role = 'admin'";
    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) return rs.getInt(1);

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}

public boolean createUser(String email, String password, String name, String role) {
    String sql = "INSERT INTO users (email, password, name, role) VALUES (?, ?, ?, ?)";

    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, email);
        ps.setString(2, password);
        ps.setString(3, name);
        ps.setString(4, role); // "user" vagy "admin"
        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    private User mapUser(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String name = rs.getString("name");
        String roleStr = rs.getString("role"); 
        Role role = Role.valueOf(roleStr.toUpperCase()); 

        return new User(id, email, password, name, role);
    }
}