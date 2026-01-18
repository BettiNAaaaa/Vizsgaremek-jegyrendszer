/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jegyvasarlo;


import api.BookingApi;


public class Main {
public static void main(String[] args) {
BookingApi api = new BookingApi();
api.bookTicket(1, 2);
}
}