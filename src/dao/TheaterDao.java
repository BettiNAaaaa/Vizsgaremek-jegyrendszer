/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import model.Theater;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TheaterDao {

    public List<Theater> getAllTheatres() {
        List<Theater> list = new ArrayList<>();
        String sql = "SELECT id, name, city, address, phone, website FROM theatre ORDER BY id";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Theater(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("website")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean deleteTheatre(int id) {
        String sql = "DELETE FROM theatre WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}