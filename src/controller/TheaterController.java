package controller;

import dao.TheaterDao;
import model.Theater;

import java.util.List;

public class TheaterController {

    private final TheaterDao theaterDao;

    public TheaterController(TheaterDao theaterDao) {
        this.theaterDao = theaterDao;
    }

    public void createTheater(Theater theater) {
        theaterDao.add(theater);
    }

    public void deleteTheater(int id) {
        theaterDao.deleteTheater(id);
    }

    public List<Theater> getAllTheaters() {
        return theaterDao.getAllTheaters();
    }
}