/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import controller.TicketController;
import model.Booking;
import User.User;


public class TicketApi {


private TicketController ticketController;


public TicketApi(TicketController ticketController) {
this.ticketController = ticketController;
}


// POST /tickets/book
public Booking bookTicket(int eventId, int seatCount) {
User user = new User(1, "Teszt Elek", "teszt@email.hu");
return ticketController.bookTicket(eventId, user, seatCount);
}
}