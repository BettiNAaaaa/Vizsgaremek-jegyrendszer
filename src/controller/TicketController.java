/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

<<<<<<< HEAD
import dao.BookingDao;
import dao.EventDao;
import model.*;
import user.User;

/**
 *
 * @author HP
 */
public class TicketController {
    
private EventDao eventDao;
private BookingDao bookingDao;


public TicketController(EventDao eventDao, BookingDao bookingDao) {
this.eventDao = eventDao;
this.bookingDao = bookingDao;
}


public Booking bookTicket(int eventId, User user, int seatCount) {
Event event = eventDao.findById(eventId);
if (event == null) {
throw new IllegalArgumentException("Nincs ilyen esemény");
}
event.bookSeat(seatCount);
Booking booking = new Booking(user, event, seatCount);
bookingDao.save(booking);
return booking;
 }
=======
/**
 *
 * @author joska
 */
public class TicketController {
    
>>>>>>> 23c7c33b0c2c533135f2f80a14ab31c31481f56e
}
