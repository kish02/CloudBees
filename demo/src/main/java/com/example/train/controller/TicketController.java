package com.example.train.controller;

import com.example.train.model.Ticket;
import com.example.train.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestParam String from,
                                            @RequestParam String to,
                                            @RequestParam String firstName,
                                            @RequestParam String lastName,
                                            @RequestParam String email,
                                            @RequestParam String seat,
                                            @RequestParam String section) {
        try {
            Ticket ticket = ticketService.purchaseTicket(from, to, firstName, lastName, email, seat, section);
            return ResponseEntity.ok(ticket);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
        }
    }

    @GetMapping("/receipt/{id}")
    public ResponseEntity<?> getReceipt(@PathVariable int id) {
        try {
            Optional<Ticket> ticket = ticketService.getReceipt(id);
            if (ticket.isPresent()) {
                return ResponseEntity.ok(ticket.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsersBySection(@RequestParam String section) {
        try {
            List<Ticket> users = ticketService.getUsersBySection(section);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeUser(@PathVariable int id) {
        try {
            ticketService.removeUser(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
        }
    }

    @PutMapping("/modify")
    public ResponseEntity<?> modifySeat(@RequestParam int id,
                                        @RequestParam String newSeat,
                                        @RequestParam String newSection) {
        try {
            ticketService.modifySeat(id, newSeat, newSection);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
        }
    }
}
