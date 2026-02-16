/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.MovieDao;
import dao.TheaterDao;
import dao.UserDao;
import model.Movie;
import model.Theater;



public class AdminController {
    
    private MovieDao movieDao;
    private TheaterDao theaterDao;
    private UserDao userDao;

    public AdminController(MovieDao movieDao,TheaterDao theaterDao,UserDao userDao) {
        this.movieDao = movieDao;
        this.theaterDao = theaterDao;
        this.userDao = userDao;
    }

    public AdminController(UserDao userDao) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // filmek

    public void addMovie(int id, String title, int availableSeats) {
        Movie movie = new Movie(id, title, availableSeats, posterUrl);
        movieDao.addMovie(movie);
    }

    // szinhazxd

    public void addTheater(int id, String title, int availableSeats) {
        Theater theater = new Theater(id, title, availableSeats);
        theaterDao.add(theater);
    }

    // felhasznalok

    public boolean deleteUser(String username) {
        return userDao.deleteByUsername(username);
    }

    public void deleteMovie(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void deleteTheater(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
