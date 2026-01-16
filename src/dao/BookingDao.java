/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Booking;
import java.util.*;


public class BookingDao {
private List<Booking> bookings = new ArrayList<>();


public void save(Booking booking) {
bookings.add(booking);
}


public List<Booking> findAll() {
return bookings;
}
}
