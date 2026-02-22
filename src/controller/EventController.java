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

    public List<Event> getAllByType(String type) {
        return eventDao.getAllByType(type);
    }

    public Event findById(int id) {
        return eventDao.findById(id);
    }
}