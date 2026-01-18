/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import controller.TheaterController;
import model.Theater;
import java.util.List;


public class TheaterApi {


private TheaterController theaterController;


public TheaterApi(TheaterController theaterController) {
this.theaterController = theaterController;
}


// GET 
public List getTheaters() {
return theaterController.getAllTheaters();
}


// POST 
public void createTheater(Theater theater) {
theaterController.addTheater(theater);
}
}