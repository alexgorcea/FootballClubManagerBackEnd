package dev.footballClubManager.FootballClubManager.Controllers;

import dev.footballClubManager.FootballClubManager.Models.Match;
import dev.footballClubManager.FootballClubManager.Models.Team;
import dev.footballClubManager.FootballClubManager.Models.Ticket;
import dev.footballClubManager.FootballClubManager.Models.User;
import dev.footballClubManager.FootballClubManager.Repositories.MatchRepository;
import dev.footballClubManager.FootballClubManager.Repositories.TeamRepository;
import dev.footballClubManager.FootballClubManager.Repositories.TicketRepository;
import dev.footballClubManager.FootballClubManager.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;


    @PostMapping("/buy")
    public ResponseEntity<Ticket> buyTicket(@RequestBody Ticket ticket, Principal principal) {
        Optional<User> optionalUser = userRepository.findByUsername(principal.getName());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        User user = optionalUser.get();

        Optional<Match> optionalMatch = matchRepository.findById(ticket.getMatchId());
        if (optionalMatch.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Match match = optionalMatch.get();

        Optional<Team> homeTeamOpt = teamRepository.findTeamById(match.getHomeTeamId());
        Optional<Team> awayTeamOpt = teamRepository.findTeamById(match.getAwayTeamId());

        if (homeTeamOpt.isEmpty() || awayTeamOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        String matchTitle = homeTeamOpt.get().getName() + " vs " + awayTeamOpt.get().getName();

        ticket.setUserId(user.getId());
        ticket.setMatchTitle(matchTitle);
        Ticket saved = ticketRepository.save(ticket);

        if (user.getTicketIds() == null) {
            user.setTicketIds(new ArrayList<>());
        }
        user.getTicketIds().add(saved.getId());
        userRepository.save(user);

        return ResponseEntity.ok(saved);
    }


    @GetMapping("/my")
    public ResponseEntity<List<Ticket>> getMyTickets(Principal principal) {
        Optional<User> optionalUser = userRepository.findByUsername(principal.getName());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        User user = optionalUser.get();
        List<String> ticketIds = user.getTicketIds();

        if (ticketIds == null || ticketIds.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<Ticket> tickets = ticketRepository.findAllById(ticketIds);
        return ResponseEntity.ok(tickets);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable String id, Principal principal) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Ticket ticket = optionalTicket.get();

        Optional<User> optionalUser = userRepository.findByUsername(principal.getName());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = optionalUser.get();
        if (!ticket.getUserId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        ticketRepository.deleteById(id);

        if (user.getTicketIds() != null) {
            user.getTicketIds().remove(id);
            userRepository.save(user);
        }

        return ResponseEntity.ok().build();
    }

}

