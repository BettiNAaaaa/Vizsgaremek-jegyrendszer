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
        User u = userDao.findByEmail(email);
        if (u == null || !u.getPassword().equals(password)) {
            throw new IllegalArgumentException("Hibás email vagy jelszó");
        }
        loggedInUser = u;
        return u;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void logout() {
        loggedInUser = null;
    }
}