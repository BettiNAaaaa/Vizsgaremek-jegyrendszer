package dao;

import java.sql.*;

public class TicketDao {

    
    private static final String TABLE = "tickets";

    public int insertTicket(int userId, int eventId, int seatCount) {
        String sql = "INSERT INTO " + TABLE + " (user_id, event_id, seat_count) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, userId);
            ps.setInt(2, eventId);
            ps.setInt(3, seatCount);
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}