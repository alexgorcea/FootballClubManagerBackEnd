package dev.footballClubManager.FootballClubManager.Services;

import dev.footballClubManager.FootballClubManager.Models.Match;
import dev.footballClubManager.FootballClubManager.Models.Team;
import dev.footballClubManager.FootballClubManager.Models.Ticket;
import dev.footballClubManager.FootballClubManager.Models.User;
import dev.footballClubManager.FootballClubManager.Repositories.MatchRepository;
import dev.footballClubManager.FootballClubManager.Repositories.TeamRepository;
import dev.footballClubManager.FootballClubManager.Repositories.TicketRepository;
import dev.footballClubManager.FootballClubManager.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    public Ticket prepareToBuyTicket(Ticket ticket, Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Match match = matchRepository.findById(ticket.getMatchId())
                .orElseThrow(() -> new RuntimeException("Match not found"));

        Team homeTeam = teamRepository.findTeamById(match.getHomeTeamId())
                .orElseThrow(() -> new RuntimeException("Home team not found"));

        Team awayTeam = teamRepository.findTeamById(match.getAwayTeamId())
                .orElseThrow(() -> new RuntimeException("Away team not found"));

        String matchTitle = homeTeam.getName() + " vs " + awayTeam.getName();
        ticket.setUserId(user.getId());
        ticket.setMatchTitle(matchTitle);
        Ticket saved = ticketRepository.save(ticket);

        if (user.getTicketIds() == null) {
            user.setTicketIds(new ArrayList<>());
        }
        user.getTicketIds().add(saved.getId());
        userRepository.save(user);

        return saved;
    }

    public List<Ticket> getTickets(Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<String> ticketIds = user.getTicketIds();

        if (ticketIds == null || ticketIds.isEmpty()) {
            return Collections.emptyList();
        }

        return ticketRepository.findAllById(ticketIds);
    }

    public void removeTicket(String id, Principal principal) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Unauthorized"));

        if (!ticket.getUserId().equals(user.getId())) {
            throw new SecurityException("Access denied: ticket does not belong to user.");
        }

        matchRepository.findById(ticket.getMatchId()).ifPresent(match -> {
            Match.TicketsSold sold = match.getTicketsSold();
            if (sold != null) {
                String tribune = ticket.getSeat().toLowerCase();
                int quantity = ticket.getQuantity();

                switch (tribune) {
                    case "north" -> sold.setNorthSeats(sold.getNorthSeats() - quantity);
                    case "south" -> sold.setSouthSeats(sold.getSouthSeats() - quantity);
                    case "east" -> sold.setEastSeats(sold.getEastSeats() - quantity);
                    case "west" -> sold.setWestSeats(sold.getWestSeats() - quantity);
                    case "vip" -> sold.setVipSeats(sold.getVipSeats() - quantity);
                }

                match.setTicketsSold(sold);
                matchRepository.save(match);
            }
        });

        ticketRepository.deleteById(id);

        if (user.getTicketIds() != null) {
            user.getTicketIds().remove(id);
            userRepository.save(user);
        }
    }



}
