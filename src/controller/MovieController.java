/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import dao.EventDao;
import dao.MovieDao;
import model.Movie;
import java.util.List;
import model.Event;


public class MovieController {
private EventDao eventDao;


public MovieController(EventDao eventDao) {
this.eventDao = eventDao;
}

    public MovieController(MovieDao movieDao) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


public void addMovie(Movie movie) {
eventDao.save(movie);
}


public List<Event>  getAllMovies() {
return eventDao.findAll();
}
}