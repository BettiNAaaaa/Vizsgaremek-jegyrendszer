package controller;

import dao.UserDao;
import model.User;

public class AuthController {

    private final UserDao userDao;
    private User loggedInUser;

    public AuthController(UserDao userDao) {
        this.userDao = userDao;
    }

    public User login(String email, String password) {
        User user = userDao.findByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Hibás email vagy jelszó");
        }
        loggedInUser = user;
        return user;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void logout() {
        loggedInUser = null;
    }
}