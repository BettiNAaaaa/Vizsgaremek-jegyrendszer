/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import User.User;
import controller.AdminController;
import model.Event;
import java.util.List;


public class AdminApi {


private AdminController adminController;


public AdminApi(AdminController adminController) {
this.adminController = adminController;
}

    public AdminApi(AdminController adminController, User loggedUser) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


// GET 
public List<Event> getAllEvents() {
return adminController.listAllEvents();
}
}