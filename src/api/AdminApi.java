/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import controller.AdminController;
import model.Event;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminApi {


private AdminController adminController;


public AdminApi(AdminController adminController) {
this.adminController = adminController;
}


@GetMapping("/events")
public List<Event> listAllEvents() {
return adminController.listAllEvents();
}
}