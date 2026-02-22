package dao;



import model.Event;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDao {

    public Event findById(int id) {
        String sql = "SELECT id, title, room, type, start, end, seats, cinema_id, theatre_id FROM events WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapEvent(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Event> getAll() {
        String sql = "SELECT id, title, room, type, start, end, seats, cinema_id, theatre_id FROM events ORDER BY id";
        List<Event> list = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(mapEvent(rs));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Event> getByType(String type) {
        String sql = "SELECT id, title, room, type, start, end, seats, cinema_id, theatre_id FROM events WHERE type = ? ORDER BY id";
        List<Event> list = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, type);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapEvent(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Event mapEvent(ResultSet rs) throws SQLException {
        return new Event(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("room"),
                rs.getString("type"),
                rs.getTimestamp("start"),
                rs.getTimestamp("end"),
                rs.getInt("seats"),
                (Integer) rs.getObject("cinema_id"),
                (Integer) rs.getObject("theatre_id")
        );
    }
}