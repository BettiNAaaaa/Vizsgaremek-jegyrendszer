package api;

import controller.AuthController;
import model.User;

public class AuthApi {

    private final AuthController authController;

    public AuthApi(AuthController authController) {
        this.authController = authController;
    }

    public User login(String email, String password) {
        return authController.login(email, password);
    }

    public User getLoggedInUser() {
        return authController.getLoggedInUser();
    }

    public void logout() {
        authController.logout();
    }
}