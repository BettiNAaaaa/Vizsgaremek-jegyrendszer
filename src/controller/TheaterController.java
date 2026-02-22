/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;



import dao.TheaterDao;
import model.Theater;
import java.util.List;

public class TheaterController {
    private final TheaterDao theaterDao;

    public TheaterController(TheaterDao theaterDao) {
        this.theaterDao = theaterDao;
    }

    public List<Theater> getAllTheatres() {
        return theaterDao.getAllTheatres();
    }

    public boolean deleteTheatre(int id) {
        return theaterDao.deleteTheatre(id);
    }
}