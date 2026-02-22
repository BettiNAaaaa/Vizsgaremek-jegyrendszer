package dao;



import model.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {

    
    public List<Ticket> listTicketsForEvent(int eventId) {
        List<Ticket> list = new ArrayList<>();
        String sql = "{CALL list_tickets_for_event(?)}";

        try (Connection conn = Database.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, eventId);

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    list.add(mapTicket(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

   
    public boolean reserveTicket(int ticketId) {
        String sql = "{CALL reserve_ticket(?)}";
        try (Connection conn = Database.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, ticketId);
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean markTicketAsSold(int ticketId) {
        String sql = "{CALL mark_ticket_as_sold(?)}";
        try (Connection conn = Database.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, ticketId);
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

   
    public boolean cancelReservation(int ticketId) {
        String sql = "{CALL cancel_reservation(?)}";
        try (Connection conn = Database.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, ticketId);
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Ticket mapTicket(ResultSet rs) throws SQLException {
        return new Ticket(
                rs.getInt("id"),
                rs.getInt("event_id"),
                rs.getString("seat_label"),
                rs.getBigDecimal("price"),
                rs.getString("status"),
                rs.getTimestamp("created")
        );
    }
}