package api;


import controller.TicketController;
import model.Booking;
import model.User;

public class TicketApi {

    private final TicketController ticketController;
    private User loggedInUser;

    public TicketApi(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    public Booking bookTicket(int eventId, int seatCount) {
        return ticketController.bookTicket(eventId, loggedInUser, seatCount);
    }
}