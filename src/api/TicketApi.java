/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import controller.TicketController;
import model.Event;
import model.Booking;
import User.User;

public class TicketApi {

    private TicketController ticketController;
    private User loggedInUser;

    public TicketApi(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    // POST /tickets
    public Booking bookTicket(Event event) {
        if (loggedInUser == null) {
            throw new RuntimeException("Nincs bejelentkezve");
        }
        return ticketController.bookTicket(loggedInUser, event);
    }
}