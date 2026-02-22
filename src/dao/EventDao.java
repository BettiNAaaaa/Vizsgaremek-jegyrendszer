package dao;

import model.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDao {

    
    private static final String TABLE = "events";

    public Event findById(int id) {
        String sql = "SELECT id, title, type, available_seats, poster_url FROM " + TABLE + " WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapEvent(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Event> getAll() {
        List<Event> list = new ArrayList<>();
        String sql = "SELECT id, title, type, available_seats, poster_url FROM " + TABLE + " ORDER BY id";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapEvent(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Event> getAllByType(String type) {
        List<Event> list = new ArrayList<>();
        String sql = "SELECT id, title, type, available_seats, poster_url FROM " + TABLE + " WHERE type = ? ORDER BY id";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, type);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapEvent(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void updateAvailableSeats(int eventId, int newSeats) {
        String sql = "UPDATE " + TABLE + " SET available_seats = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newSeats);
            ps.setInt(2, eventId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Event mapEvent(ResultSet rs) throws SQLException {
        return new Event(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("type"),
                rs.getInt("available_seats"),
                rs.getString("poster_url")
        );
    }
}