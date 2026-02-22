/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;


import controller.MovieController;
import model.Event;
import java.util.List;

public class MovieApi {
    private final MovieController movieController;

    public MovieApi(MovieController movieController) {
        this.movieController = movieController;
    }

    public List<Event> getAllMovies() {
        return movieController.getAllMovies();
    }
}