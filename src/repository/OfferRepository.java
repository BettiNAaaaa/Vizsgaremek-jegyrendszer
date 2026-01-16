/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;


import dao.Database;
import model.Offer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferRepository {

    public List<Offer> getAll() {
        List<Offer> list = new ArrayList<>();

        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps =
                    conn.prepareStatement("SELECT * FROM offers");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Offer(
                        rs.getInt("id"),
                        rs.getString("text"),
                        rs.getString("validUntil")
                ));
            }

        } catch (Exception e) { e.printStackTrace(); }

        return list;
    }

    public Offer getById(int id) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps =
                    conn.prepareStatement("SELECT * FROM offers WHERE id = ? LIMIT 1");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Offer(
                        rs.getInt("id"),
                        rs.getString("text"),
                        rs.getString("validUntil")
                );
            }

        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public boolean add(Offer o) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO offers(text, validUntil) VALUES (?, ?)"
            );

            ps.setString(1, o.getText());
            ps.setString(2, o.getValidUntil());

            return ps.executeUpdate() > 0;

        } catch (Exception e) { e.printStackTrace(); }

        return false;
    }

    public boolean update(Offer o) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE offers SET text = ?, validUntil = ? WHERE id = ?"
            );

            ps.setString(1, o.getText());
            ps.setString(2, o.getValidUntil());
            ps.setInt(3, o.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) { e.printStackTrace(); }

        return false;
    }

    public boolean delete(int id) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps =
                    conn.prepareStatement("DELETE FROM offers WHERE id = ?");

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) { e.printStackTrace(); }

        return false;
    }
}