/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.EventDao;
import java.util.List;
import model.Theater;

/**
 *
 * @author HP
 */
public class TheaterController {
    
private EventDao eventDao;

public TheaterController(EventDao eventDao) {
this.eventDao = eventDao;
}

public void addTheater(Theater theater) {
eventDao.save(theater);
}

public List getAllTheaters() {
return eventDao.findAll();
 }
}
