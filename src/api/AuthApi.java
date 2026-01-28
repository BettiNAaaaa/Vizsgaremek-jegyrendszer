/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;



import controller.AuthController;
import User.User;

public class AuthApi {

    private AuthController authController;

    public AuthApi(AuthController authController) {
        this.authController = authController;
    }

    // POST /login
    public boolean login(String username, String password) {
        return authController.login(username, password);
    }

    public User getLoggedInUser() {
        return authController.getLoggedInUser();
    }
}
