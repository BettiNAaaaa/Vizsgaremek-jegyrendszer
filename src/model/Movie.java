/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class Movie {
    
   private int id;
   private String title;
   private int availableSeats;
   
   
   public Movie(int id,String title, int availableSeats){
       this.id=id;
       this.title=title;
       this.availableSeats=availableSeats;
       
   }
   
   public int getId() {
       return id;
   }
   
   public String getTitle(){
       return title;
   }
   
   public int getAvailableSeats(){
           return availableSeats;
   }
   
   public void bookSeat(){
       if (availableSeats > 0) {
           availableSeats--;
       }
   }
    
}
