package dev.footballClubManager.FootballClubManager.Controllers;

import dev.footballClubManager.FootballClubManager.Models.Ticket;
import dev.footballClubManager.FootballClubManager.Repositories.MatchRepository;
import dev.footballClubManager.FootballClubManager.Repositories.TicketRepository;
import dev.footballClubManager.FootballClubManager.Repositories.UserRepository;
import dev.footballClubManager.FootballClubManager.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;


    @PostMapping("/buy")
    public ResponseEntity<?> buyTicket(@RequestBody Ticket ticket, Principal principal) {
        try {
            Ticket result = ticketService.prepareToBuyTicket(ticket, principal);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }


    @GetMapping("/my")
    public ResponseEntity<?> getMyTickets(Principal principal) {
        try {
            List<Ticket> tickets = ticketService.getTickets(principal);
            return ResponseEntity.ok(tickets);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable String id, Principal principal) {
        try {
            ticketService.removeTicket(id, principal);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e instanceof SecurityException) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", e.getMessage()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
            }
        }
    }
}

