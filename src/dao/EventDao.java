/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Event;
import java.util.*;

public class EventDao {
    private List<Event> events = new ArrayList<>();

    public void save(Event event) {
        events.add(event);
    }

    public List<Event> findAll() {
        return events;
    }

    public Event findById(int id) {
        return events.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }
}