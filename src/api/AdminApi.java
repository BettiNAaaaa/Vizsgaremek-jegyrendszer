package api;

import controller.AdminController;
import security.JwtUtil;

public class AdminApi {

    private final AdminController adminController;

    public AdminApi(AdminController adminController) {
        this.adminController = adminController;
    }

    public boolean deleteUser(String token, int id) {
        JwtUtil.DecodedToken decoded = JwtUtil.verifyToken(token);

        if (!"ADMIN".equals(decoded.role)) {
            throw new RuntimeException("Nincs jogosultság! Csak ADMIN törölhet.");
        }

        return adminController.deleteUser(id);
    }
}