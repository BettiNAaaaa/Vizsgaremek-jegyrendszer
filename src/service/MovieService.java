/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Movie;
import repository.MovieRepository;
import api.MovieApiClient;

import java.util.List;

public class MovieService {

    private final MovieRepository repo = new MovieRepository();
    private final MovieApiClient api = new MovieApiClient();

    public List<Movie> getAll() { return repo.getAll(); }

    public boolean createMovie(String title, String descr) {
        String cover = api.fetchMovieCover(title);
        return repo.add(new Movie(0, title, descr, cover));
    }

    private static class MovieRepository {

        public MovieRepository() {
        }

        private List<Movie> getAll() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private boolean add(Movie movie) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    private static class MovieApiClient {

        public MovieApiClient() {
        }

        private String fetchMovieCover(String title) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}