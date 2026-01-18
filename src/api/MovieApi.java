/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import controller.MovieController;
import model.Movie;
import java.util.List;


public class MovieApi {


private MovieController movieController;


public MovieApi(MovieController movieController) {
this.movieController = movieController;
}


// GET movies
public List getMovies() {
return movieController.getAllMovies();
}


// POST /movies
public void createMovie(Movie movie) {
movieController.addMovie(movie);
}
}