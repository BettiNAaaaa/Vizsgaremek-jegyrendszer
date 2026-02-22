package controller;

import dao.EventDao;
import dao.TicketDao;
import model.Booking;
import model.Event;
import model.User;

public class TicketController {

    private final EventDao eventDao;
    private final TicketDao ticketDao;

    public TicketController(EventDao eventDao, TicketDao ticketDao) {
        this.eventDao = eventDao;
        this.ticketDao = ticketDao;
    }

    public Booking bookTicket(int eventId, User user, int seatCount) {
        if (user == null) throw new IllegalArgumentException("Nincs bejelentkezett felhasználó");
        if (seatCount <= 0) throw new IllegalArgumentException("A jegyek száma legyen pozitív");

        Event event = eventDao.findById(eventId);
        if (event == null) throw new IllegalArgumentException("Nincs ilyen esemény");

        
        event.bookSeat(seatCount);

     
        eventDao.updateAvailableSeats(eventId, event.getAvailableSeats());
        int ticketId = ticketDao.insertTicket(user.getId(), eventId, seatCount);

        return new Booking(ticketId, user, event, seatCount);
    }
}