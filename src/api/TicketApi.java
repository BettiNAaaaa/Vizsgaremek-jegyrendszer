package api;


import controller.TicketController;
import model.Ticket;
import java.util.List;

public class TicketApi {
    private final TicketController ticketController;

    public TicketApi(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    public List<Ticket> listTicketsForEvent(int eventId) {
        return ticketController.listTicketsForEvent(eventId);
    }

    public boolean reserveTicket(int ticketId) {
        return ticketController.reserveTicket(ticketId);
    }

    public boolean buyTicket(int ticketId) {
        return ticketController.buyTicket(ticketId);
    }

    public boolean cancelReservation(int ticketId) {
        return ticketController.cancelReservation(ticketId);
    }
}