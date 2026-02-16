/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;




import api.*;
import controller.*;
import dao.*;
import model.*;
import security.JwtUtil;

public class Main {

    public static void main(String[] args) {
        
        String token = JwtUtil.generateToken("admin", "ADMIN");
        System.out.println(token);

        // dao r
        UserDao userDao = new UserDao();
        MovieDao movieDao = new MovieDao();
        TheaterDao theaterDao = new TheaterDao();
        BookingDao bookingDao = new BookingDao();

        // kontroller
        AuthController authController = new AuthController(userDao);
        MovieController movieController = new MovieController(movieDao);
        TheaterController theaterController = new TheaterController(theaterDao);
        TicketController ticketController = new TicketController(bookingDao);
        AdminController adminController = new AdminController(userDao);

        // apik
        AuthApi authApi = new AuthApi(authController);
        MovieApi movieApi = new MovieApi(movieController);
        TheaterApi theaterApi = new TheaterApi(theaterController);
        TicketApi ticketApi = new TicketApi(ticketController);
        AdminApi adminApi = new AdminApi(adminController);

        // felhasznalok=
        User admin = new User(1, "Admin", "admin@mozi.hu", "admin", "admin", Role.ADMIN);
        User user = new User(2, "User", "user@mozi.hu", "user", "user", Role.USER);

        userDao.add(admin);
        userDao.add(user);

        // admin bejelenkezes
        System.out.println("ADMIN bejelentkezés...");
        authApi.login("admin", "admin");

        User loggedAdmin = authApi.getLoggedInUser();
        movieApi.setLoggedInUser(loggedAdmin);
        theaterApi.setLoggedInUser(loggedAdmin);
        adminApi.setLoggedInUser(loggedAdmin);

        // adatok felvetele
        movieApi.createMovie(new Movie(1, "Dűne", 100, ""));
        movieApi.createMovie(new Movie(2, "Mátrix", 80, ""));

        theaterApi.createTheater(new Theater(1, "Hamlet", 60));

        // felhasznalo bejelenkezes
        System.out.println("\nUSER bejelentkezés...");
        authApi.login("user", "user");

        User loggedUser = authApi.getLoggedInUser();
        ticketApi.setLoggedInUser(loggedUser);

        // jegyfoglalas
        Movie selectedMovie = movieController.getMovieById(1);
        Booking booking = ticketApi.bookTicket(selectedMovie);

        System.out.println(
                "Felhasználó: " + booking.getUser().getName()
                + " | Esemény: " + booking.getEvent().getTitle()
        );

        // admin torles teszt
        System.out.println("\nAdmin törli a felhasználót...");
        authApi.login("admin", "admin");
        adminApi.setLoggedInUser(authApi.getLoggedInUser());
        adminApi.deleteUser(2);

        System.out.println("Felhasználó törölve.");
    }
    
    

}
