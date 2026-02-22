package controller;



import dao.UserDao;

public class AdminController {
    private final UserDao userDao;

    public AdminController(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean deleteUser(int id) {
        return userDao.deleteById(id);
    }
}