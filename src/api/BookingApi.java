/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;


import model.*;
import model.User;
import controller.BookingController;
import controller.EventController;

/**
 *
 * @author HP
 */
public class BookingApi {
    private EventController eventController = new EventController();
private BookingController bookingController = new BookingController();


public void bookTicket(int eventId, int seatCount) {
User user = new User(1, "Teszt Elek", "teszt@email.hu");
Event event = eventController.findEventById(eventId);


if (event == null) {
System.out.println("Nincs ilyen esemény");
return;
}


Booking booking = bookingController.createBooking(user, event, seatCount);
System.out.println(booking.getSummary());
}
}
