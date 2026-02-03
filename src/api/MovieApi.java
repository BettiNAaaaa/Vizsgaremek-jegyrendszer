/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import controller.MovieController;
import model.Movie;
import User.User;
import model.Role;

import java.util.List;

public class MovieApi {

    private MovieController movieController;
    private User loggedInUser;

    public MovieApi(MovieController movieController) {
        this.movieController = movieController;
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    // GET /movies
    public List<Movie> getAllMovies() {
        return movieController.getAllMovies();
    }

    // POST /movies (ADMIN)
    public void createMovie(Movie movie) {
        if (loggedInUser.getRole() != Role.ADMIN) {
            throw new RuntimeException("Nincs jogosultság");
        }
        movieController.createMovie(movie);
    }
}