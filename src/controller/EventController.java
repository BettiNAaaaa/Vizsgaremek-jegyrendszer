package controller;



import dao.EventDao;
import model.Event;
import java.util.List;

public class EventController {
    private final EventDao eventDao;

    public EventController(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public List<Event> getAllEvents() {
        return eventDao.getAll();
    }

    public List<Event> getCinemaEvents() {
        return eventDao.getByType("cinema");
    }

    public List<Event> getTheatreEvents() {
        return eventDao.getByType("theatre");
    }

    public Event getById(int id) {
        return eventDao.findById(id);
    }
}