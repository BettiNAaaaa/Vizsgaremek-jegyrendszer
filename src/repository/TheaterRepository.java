/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package repository;

import dao.Database;
import model.Theater;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TheaterRepository {

    public List<Theater> getAll() {
        List<Theater> theaters = new ArrayList<>();

        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM theaters");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                theaters.add(new Theater(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location")
                ));
            }

        } catch (Exception e) { e.printStackTrace(); }

        return theaters;
    }

    public Theater getById(int id) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps =
                    conn.prepareStatement("SELECT * FROM theaters WHERE id = ? LIMIT 1");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Theater(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location")
                );
            }

        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public boolean add(Theater t) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO theaters(name, location) VALUES (?, ?)"
            );

            ps.setString(1, t.getName());
            ps.setString(2, t.getLocation());

            return ps.executeUpdate() > 0;

        } catch (Exception e) { e.printStackTrace(); }

        return false;
    }

    public boolean update(Theater t) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE theaters SET name = ?, location = ? WHERE id = ?"
            );

            ps.setString(1, t.getName());
            ps.setString(2, t.getLocation());
            ps.setInt(3, t.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) { e.printStackTrace(); }

        return false;
    }

    public boolean delete(int id) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps =
                    conn.prepareStatement("DELETE FROM theaters WHERE id = ?");

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) { e.printStackTrace(); }

        return false;
    }
}
