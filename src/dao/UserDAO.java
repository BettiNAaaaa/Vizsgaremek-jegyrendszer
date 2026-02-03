/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;



import User.User;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private List<User> users = new ArrayList<>();

    public void add(User user) {
        users.add(user);
    }

    public User findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAll() {
        return users;
    }
    
    public boolean deleteByUsername(String username){
    User user = findByUsername(username);
    if user (!=null){
        users.remove(user);
        return true;
        }
    return false;
}
} 

