package controller;



import dao.TicketDao;
import model.Ticket;
import java.util.List;

public class TicketController {
    private final TicketDao ticketDao;

    public TicketController(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public List<Ticket> listTicketsForEvent(int eventId) {
        return ticketDao.listTicketsForEvent(eventId);
    }

    public boolean reserveTicket(int ticketId) {
        return ticketDao.reserveTicket(ticketId);
    }

    public boolean buyTicket(int ticketId) {
        return ticketDao.markTicketAsSold(ticketId);
    }

    public boolean cancelReservation(int ticketId) {
        return ticketDao.cancelReservation(ticketId);
    }
}