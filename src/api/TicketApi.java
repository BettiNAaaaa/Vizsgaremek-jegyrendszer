package api;




import controller.TicketController;
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

 
    public boolean buyTicket(int ticketId) {
        if (loggedInUser == null) {
            throw new IllegalArgumentException("Nincs bejelentkezett felhasználó!");
        }

       
        return ticketController.buyTicket(ticketId);
    }
}
