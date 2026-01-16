/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import dao.Database;
import model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    public List<Movie> getAll() {
        List<Movie> movies = new ArrayList<>();

        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM movies");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("coverUrl")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }

        return movies;
    }

    public Movie getById(int id) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps =
                    conn.prepareStatement("SELECT * FROM movies WHERE id = ? LIMIT 1");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("coverUrl")
                );
            }

        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public boolean add(Movie movie) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO movies(title, description, coverUrl) VALUES (?, ?, ?)"
            );

            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getDescription());
            ps.setString(3, movie.getCoverUrl());

            return ps.executeUpdate() > 0;

        } catch (Exception e) { e.printStackTrace(); }

        return false;
    }

    public boolean update(Movie movie) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE movies SET title = ?, description = ?, coverUrl = ? WHERE id = ?"
            );

            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getDescription());
            ps.setString(3, movie.getCoverUrl());
            ps.setInt(4, movie.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) { e.printStackTrace(); }

        return false;
    }

    public boolean delete(int id) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps =
                    conn.prepareStatement("DELETE FROM movies WHERE id = ?");

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
        catch (Exception e) { e.printStackTrace(); }

        return false;
    }
}