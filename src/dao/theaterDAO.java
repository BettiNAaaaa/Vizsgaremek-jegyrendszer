package dao;

import database.Database; 
import model.Theater;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TheaterDao {

    
    private static final String TABLE = "theatre";

    public void add(Theater theater) {
        String sql = "INSERT INTO " + TABLE + " (id, title, available_seats) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, theater.getId());
            ps.setString(2, theater.getTitle());
            ps.setInt(3, theater.getAvailableSeats());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTheater(int id) {
        String sql = "DELETE FROM " + TABLE + " WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    public List<Theater> getAllTheaters() {
        List<Theater> list = new ArrayList<>();
        String sql = "SELECT id, title, available_seats FROM " + TABLE;

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Theater(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("available_seats")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}