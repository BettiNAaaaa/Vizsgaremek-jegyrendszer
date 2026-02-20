/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;



import com.auth0.jwt.interfaces.DecodedJWT;
import controller.AdminController;
import model.User;
import model.Role;
import security.JwtUtil;

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

    public boolean deleteUser(String token, int id) {
        DecodedJWT jwt = JwtUtil.verifyToken(token);
    String role = jwt.getClaim("role").asString();

    if (!"ADMIN".equals(role)) {
        throw new RuntimeException("Nincs jogosultság!");
    }

    return adminController.deleteUser(id);
}
    }
      

        
    
