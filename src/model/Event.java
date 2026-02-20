/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public abstract class Event {

    protected int id;
    protected String title;
    protected int availableSeats;

    public Event(int id, String title, int availableSeats) {
        this.id = id;
        this.title = title;
        this.availableSeats = availableSeats;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
        }
    }

    public void bookSeat(int seats) {
        if (seats > 0 && availableSeats >= seats) {
        availableSeats -= seats;
    } else {
        System.out.println("Not enough available seats.");
    }
    }
}
