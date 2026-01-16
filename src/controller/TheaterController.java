/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

<<<<<<< HEAD
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
=======
/**
 *
 * @author joska
 */
public class TheaterController {
    
>>>>>>> 23c7c33b0c2c533135f2f80a14ab31c31481f56e
}
