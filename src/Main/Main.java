/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;


import api.*;
import controller.*;
import dao.*;
import model.*;
import user.*;

public class Main {

    public static void main(String[] args) {

        // dao haha
        EventDao eventDao = new EventDao();
        BookingDao bookingDao = new BookingDao();
        UserDao userDao = new UserDao();


        // kontrollerek
        MovieController movieController = new MovieController(eventDao);
        TheaterController theaterController = new TheaterController(eventDao);
        TicketController ticketController = new TicketController(eventDao, bookingDao);
        AdminController adminController = new AdminController(eventDao);
        AuthController authController = new AuthController(userDao);

        // Apik xd
        AuthApi authApi = new AuthApi(authController);

        // Login
        authApi.login("admin", "admin");   // próbáld ki: user / user
        var loggedUser = authApi.getLoggedInUser();

        // Film létrehozása
        MovieApi movieApi = new MovieApi(movieController);
        movieApi.createMovie(new Movie(1, "Dűne", 100, 155));

        // jegyfoglalsokba
        TicketApi ticketApi = new TicketApi(ticketController, loggedUser);
        ticketApi.bookTicket(1, 2);

        // Admin
        AdminApi adminApi = new AdminApi(adminController, loggedUser);
        adminApi.getAllEvents().forEach(e -> {
            System.out.println(e.getTitle());
        }
        );
    }
}
