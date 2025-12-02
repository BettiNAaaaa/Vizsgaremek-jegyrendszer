/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

/**
 *
 * @author joska
 */
public class TicketRepository {
      public List<Ticket> getAll() {
        List<Ticket> tickets = new ArrayList<>();

        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM tickets");
            ResultSet rs = ps.executeQuery();

    
}
