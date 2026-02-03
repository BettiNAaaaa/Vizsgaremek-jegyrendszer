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
        List<Movie> list = new ArrayList<>();
        String sql = "SELECT * FROM movies";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("poster_url")
                ));
            }

        } catch (SQLException e) {
        }

        return list;
    }

    public void addMovie(Movie movie) {
        String sql = "INSERT INTO movies (title, poster_url) VALUES (?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getPosterUrl());
            ps.executeUpdate();

        } catch (SQLException e) {
        }
    }

    public void add(Movie movie) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
    

