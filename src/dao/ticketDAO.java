package dao;

import model.Ticket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {

    public List<Ticket> listTicketsForEvent(int eventId) {
        List<Ticket> list = new ArrayList<>();
        String sql = "SELECT id, event_id, seat_label, price, status, created FROM tickets WHERE event_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, eventId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Ticket t = new Ticket(
                            rs.getInt("id"),
                            rs.getInt("event_id"),
                            rs.getString("seat_label"),
                            rs.getDouble("price"),
                            rs.getString("status"),
                            rs.getTimestamp("created")
                    );
                    list.add(t);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean reserveTicket(int ticketId) {
        // csak akkor foglal, ha available
        String sql = "UPDATE tickets SET status='reserved' WHERE id=? AND status='available'";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ticketId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean buyTicket(int ticketId) {
        String sql = "UPDATE tickets SET status='sold' WHERE id=? AND status IN ('available','reserved')";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ticketId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cancelReservation(int ticketId) {
        String sql = "UPDATE tickets SET status='available' WHERE id=? AND status='reserved'";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ticketId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean markTicketAsSold(int ticketId) {
    String sql = "UPDATE tickets SET status='sold' WHERE id=? AND status IN ('available','reserved')";
    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, ticketId);
        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}