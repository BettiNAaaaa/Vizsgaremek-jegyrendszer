/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;



import dao.MovieDao;
import model.Event;
import java.util.List;

public class MovieController {
    private final MovieDao movieDao;

    public MovieController(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public List<Event> getAllMovies() {
        return movieDao.getAllMovies();
    }

    public Event getMovieById(int id) {
        return movieDao.findById(id);
    }
}