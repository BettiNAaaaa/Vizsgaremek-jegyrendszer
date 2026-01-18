/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.*;
import User.User;


public class BookingController {


public Booking createBooking(User user, Event event, int seats) {
event.bookSeat(seats);
return new Booking(user, event, seats);
}
}