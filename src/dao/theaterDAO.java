/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import model.Theater;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author joska
 */
public class TheaterDao {

    public void add(Theater theater) {
        
        String sql = "INSERT INTO theaters (id, title, available_seats) VALUES (?, ?, ?)";

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
    
    
    public void deleteTheater(int id){
        
        String sql = "DELETE FROM theaters WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

