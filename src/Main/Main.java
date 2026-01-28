/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;


import User.Role;
import api.*;
import controller.*;
import dao.*;
import model.*;
import user.*;

public class Main {

    public static void main(String[] args) {

        // ===== DAO RÉTEG =====
        MovieDao movieDao = new MovieDao();
        TheaterDao theaterDao = new TheaterDao();
        BookingDao bookingDao = new BookingDao();
        UserDao userDao = new UserDao();

        // ===== CONTROLLER RÉTEG =====
        MovieController movieController = new MovieController(movieDao);
        TheaterController theaterController = new TheaterController(theaterDao);
        TicketController ticketController = new TicketController(bookingDao);
        AdminController adminController = new AdminController(movieDao, theaterDao);
        AuthController authController = new AuthController(userDao);

        // ===== API RÉTEG =====
        MovieApi movieApi = new MovieApi(movieController);
        TheaterApi theaterApi = new TheaterApi(theaterController);
        TicketApi ticketApi = new TicketApi(ticketController);
        AdminApi adminApi = new AdminApi(adminController);
        AuthApi authApi = new AuthApi(authController);

        // ===== FELHASZNÁLÓK FELVÉTELE =====
        user admin = new user(1, "Admin", "admin@mozi.hu", "admin", "admin", Role.ADMIN);
        user user = new User(2, "User", "user@mozi.hu", "user", "user", Role.USER);

        userDao.add(admin);
        userDao.add(user);

        // ===== ADMIN BELÉPÉS =====
        System.out.println("ADMIN bejelentkezés...");
        authApi.login("admin", "admin");

        var loggedAdmin = authApi.getLoggedInUser();
        movieApi.setLoggedInUser(loggedAdmin);
        theaterApi.setLoggedInUser(loggedAdmin);
        adminApi.setLoggedInUser(loggedAdmin);

        // ===== ADATOK FELVÉTELE =====
        movieApi.createMovie(new Movie(1, "Dűne", 100, 155));
        movieApi.createMovie(new Movie(2, "Mátrix", 80, 136));

        theaterApi.createTheater(new Theater(1, "Hamlet", 60));
        theaterApi.createTheater(new Theater(2, "Bánk bán", 50));

        // ===== LISTÁZÁS =====
        System.out.println("\nFilmek:");
        movieApi.getAllMovies().forEach(m ->
                System.out.println(m.getTitle())
        );

        System.out.println("\nSzínházi előadások:");
        theaterApi.getAllTheaters().forEach(t ->
                System.out.println(t.getTitle())
        );

        // ===== USER BELÉPÉS =====
        System.out.println("\nUSER bejelentkezés...");
        authApi.login("user", "user");

        var loggedUser = authApi.getLoggedInUser();
        ticketApi.setLoggedInUser(loggedUser);

        // ===== JEGYFOGLALÁS =====
        Movie selectedMovie = movieApi.getAllMovies().get(0);
        Booking booking = ticketApi.bookTicket(selectedMovie);

        System.out.println("\nJegyfoglalás sikeres:");
        System.out.println("Felhasználó: " + booking.getUser().getName());
        System.out.println("Esemény: " + booking.getEvent().getTitle());
    }
}

