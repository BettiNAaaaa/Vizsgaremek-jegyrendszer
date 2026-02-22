/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;



import model.Event;
import java.util.List;

public class MovieDao {
    private final EventDao eventDao = new EventDao();

    public List<Event> getAllMovies() {
        return eventDao.getByType("cinema");
    }

    public Event findById(int id) {
        Event e = eventDao.findById(id);
        if (e == null) return null;
        if (!"cinema".equalsIgnoreCase(e.getType())) return null;
        return e;
    }
}