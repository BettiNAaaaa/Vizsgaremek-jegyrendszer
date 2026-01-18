/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import controller.MovieController;
import model.Movie;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/movies")
public class MovieApi {


private MovieController movieController;


public MovieApi(MovieController movieController) {
this.movieController = movieController;
}


@PostMapping
public void addMovie(@RequestBody Movie movie) {
movieController.addMovie(movie);
}


@GetMapping
public List getAllMovies() {
return movieController.getAllMovies();
}
}