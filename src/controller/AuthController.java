/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import service.UserService;

public class AuthController {

    private final UserService userService = new UserService();

    public String login(String username, String password) {
        return userService.login(username, password);
    }

    public boolean register(String username, String password) {
        return userService.register(username, password);
    }
}