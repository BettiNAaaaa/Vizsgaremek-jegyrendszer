/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jegyvasarlo;
<<<<<<< HEAD
=======
 import dao.*;
import model.*;
import service.*;
import java.sql.Connection;
/**
 *
 * @author joska
 */
public class Jegyvasarlo {

  


    public static void main(String[] args) {
        try (Connection conn = Database.getConnection()) {
            userDAO userDAO = new userDAO(conn);
            UserService userService = new UserService(userDAO);
            User newUser = new User(0, "Teszt Elek", "teszt@valami.hu", "1234");
            boolean ok = UserService.register(newUser);
            System.out.println(ok ? "Sikeres regisztráció" : "E-mail már létezik!");

     
            String user = userService.login("teszt@valami.hu", "1234");
            if (user != null) {
                System.out.println(user.getName() + "Sikeres bejelentkezés: ");
            } else {
                System.out.println("Hibás e-mail vagy jelszó");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
>>>>>>> 23c7c33b0c2c533135f2f80a14ab31c31481f56e
