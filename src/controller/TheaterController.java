/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.EventDao;
import dao.TheaterDao;
import model.Theater;
import java.util.List;


public class TheaterController {
private EventDao eventDao;


public TheaterController(EventDao eventDao) {
this.eventDao = eventDao;
}

    public TheaterController(TheaterDao theaterDao) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


public void addTheater(Theater theater) {
eventDao.save(theater);
}


public List getAllTheaters() {
return eventDao.findAll();
}
}