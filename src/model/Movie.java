/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;



public class Movie extends Event {

    private int lengthMinutes;

    // EZ A KONSTRUKTOR KELL A MAIN-BEN HASZNÁLTHOZ
    public Movie(int id, String title, int availableSeats, int lengthMinutes) {
        super(id, title, availableSeats);
        this.lengthMinutes = lengthMinutes;
    }

    public Movie(int id, String title, int availableSeats) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getLengthMinutes() {
        return lengthMinutes;
    }
}
