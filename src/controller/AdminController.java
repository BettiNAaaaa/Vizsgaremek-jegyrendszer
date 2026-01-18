/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.EventDao;
import model.Event;
import java.util.List;


public class AdminController {
private EventDao eventDao;


public AdminController(EventDao eventDao) {
this.eventDao = eventDao;
}


public List<Event> listAllEvents() {
return eventDao.findAll();
}
}