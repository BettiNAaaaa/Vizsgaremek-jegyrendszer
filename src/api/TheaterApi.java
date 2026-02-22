/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;


import controller.TheaterController;
import model.Theater;
import java.util.List;

public class TheaterApi {
    private final TheaterController theaterController;

    public TheaterApi(TheaterController theaterController) {
        this.theaterController = theaterController;
    }

    public List<Theater> getAllTheatres() {
        return theaterController.getAllTheatres();
    }

    public boolean deleteTheatre(int id) {
        return theaterController.deleteTheatre(id);
    }
}