/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.BookingDao;
import dao.EventDao;
import model.*;
import User.User;


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
}