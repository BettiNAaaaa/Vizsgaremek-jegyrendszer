/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import controller.TheaterController;
import model.Theater;
import User.User;
import User.Role;

import java.util.List;

public class TheaterApi {

    private TheaterController theaterController;
    private User loggedInUser;

    public TheaterApi(TheaterController theaterController) {
        this.theaterController = theaterController;
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    // GET /theaters
    public List<Theater> getAllTheaters() {
        return theaterController.getAllTheaters();
    }

    // POST /theaters (ADMIN)
    public void createTheater(Theater theater) {
        if (loggedInUser.getRole() != Role.ADMIN) {
            throw new RuntimeException("Nincs jogosultság");
        }
        theaterController.createTheater(theater);
    }
}