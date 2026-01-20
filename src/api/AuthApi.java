/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import controller.AuthController;

import User.User;

public class AuthApi {

    private AuthController authController;
    private User loggedInUser;

    public AuthApi(AuthController authController) {
        this.authController = authController;
    }

    public void login(String username, String password) {
        loggedInUser = authController.login(username, password);
        System.out.println("Sikeres bejelentkezés: " + loggedInUser.getName());
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
