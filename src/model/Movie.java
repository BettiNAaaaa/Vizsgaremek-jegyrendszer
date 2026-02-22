/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;



import java.sql.Timestamp;

public class Movie extends Event {
    public Movie(int id, String title, String room, Timestamp start, Timestamp end, int seats, Integer cinemaId) {
        super(id, title, room, "cinema", start, end, seats, cinemaId, null);
    }
}