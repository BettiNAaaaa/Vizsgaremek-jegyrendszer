/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;



import model.Movie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT id, title, poster_url FROM movies";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("available_seats"),
                        rs.getString("poster_url") 
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }

    public void addMovie(Movie movie) {
        String sql = "INSERT INTO movies (title, poster_url) VALUES (?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getPosterUrl());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Movie findById(int id) {
        String sql = "SELECT id, title, poster_url FROM movies WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("available_seats"),
                        rs.getString("poster_url")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

   public boolean deleteMovie(int id) {
    String sql = "DELETE FROM movies WHERE id = ?";

    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);
        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}

