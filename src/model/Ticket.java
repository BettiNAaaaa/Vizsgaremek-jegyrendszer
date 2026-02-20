/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author joska
 */
public class Ticket {
    
    private int id;
    private int eventId;
    private int seatCount;
    
    public Ticket(int id, int eventId, int seatCount){
        this.id = id;
        this.eventId = eventId;
        this.seatCount = seatCount;
    }
    
    public int getId(){
        return id;
    }
    
     public int getEventId(){
        return eventId;
    }
     
      public int getSeatCount(){
        return seatCount;
    }
}
