/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

<<<<<<< HEAD
import dao.EventDao;
import model.Movie;
import java.util.List;


public class MovieController {
private EventDao eventDao;


public MovieController(EventDao eventDao) {
this.eventDao = eventDao;
}


public void addMovie(Movie movie) {
eventDao.save(movie);
}


public List getAllMovies() {
return eventDao.findAll();
}
}
=======
/**
 *
 * @author joska
 */
public class MovieController {
    
}
>>>>>>> 23c7c33b0c2c533135f2f80a14ab31c31481f56e
