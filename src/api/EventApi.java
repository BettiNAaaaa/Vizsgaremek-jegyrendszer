/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;



import controller.EventController;
import model.Event;
import java.util.List;

public class EventApi {

    private final EventController eventController;

    public EventApi(EventController eventController) {
        this.eventController = eventController;
    }

    public List<Event> getAllEvents() {
        return eventController.getAllEvents();
    }

    public List<Event> getMovies() {
        return eventController.getAllByType("movie");
    }

    public List<Event> getTheatres() {
        return eventController.getAllByType("theatre");
    }
}