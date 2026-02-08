/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;




import dao.MovieDao;
import model.Movie;

public class MovieController {

    private MovieDao movieDao;

    public MovieController(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public void createMovie(Movie movie) {
        movieDao.add(movie);
    }

    public Movie getMovieById(int id) {
        return movieDao.findById(id);
    }
}
