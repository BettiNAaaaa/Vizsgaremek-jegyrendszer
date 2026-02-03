/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;



import controller.AdminController;
import model.User;
import model.Role;

public class AdminApi {

    private AdminController adminController;
    private User loggedInUser;

    public AdminApi(AdminController adminController) {
        this.adminController = adminController;
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    // DELETE /movies/{id}
    public void deleteMovie(int id) {
        if (loggedInUser.getRole() != Role.ADMIN) {
            throw new RuntimeException("Nincs jogosultság");
        }
        adminController.deleteMovie(id);
    }

    // DELETE /theaters/{id}
    public void deleteTheater(int id) {
        if (loggedInUser.getRole() != Role.ADMIN) {
            throw new RuntimeException("Nincs jogosultság");
        }
        adminController.deleteTheater(id);
    }
}
