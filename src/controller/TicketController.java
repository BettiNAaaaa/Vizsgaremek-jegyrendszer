package controller;



import dao.TicketDao;

public class TicketController {

    private final TicketDao ticketDao;

    public TicketController(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public boolean buyTicket(int ticketId) {
        return ticketDao.markTicketAsSold(ticketId);
    }
}