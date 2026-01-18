/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import controller.TicketController;
import model.Booking;
import user.User;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/tickets")
public class TicketApi {


private TicketController ticketController;


public TicketApi(TicketController ticketController) {
this.ticketController = ticketController;
}


@PostMapping("/book")
public Booking bookTicket(@RequestParam int eventId,
@RequestParam int seatCount) {
User user = new User(1, "Teszt Elek", "teszt@email.hu");
return ticketController.bookTicket(eventId, user, seatCount);
}
}