/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author HP
 */
public class Booking {
    
private User user;
private Event event;
private int seatCount;


public Booking(User user, Event event, int seatCount) {
this.user = user;
this.event = event;
this.seatCount = seatCount;
}


public String getSummary() {
return user.getName() + " foglalt " + seatCount + " jegyet erre: " + event.getTitle();
}
}

