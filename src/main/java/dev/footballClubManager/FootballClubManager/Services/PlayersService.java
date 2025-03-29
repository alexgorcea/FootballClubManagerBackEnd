package dev.footballClubManager.FootballClubManager.Services;

import dev.footballClubManager.FootballClubManager.Models.Players.Player;
import dev.footballClubManager.FootballClubManager.Models.Players.TeamPlayers;
import dev.footballClubManager.FootballClubManager.Repositories.PlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayersService {

    @Autowired
    private PlayersRepository playersRepository;

    public List<TeamPlayers> findAllTeamPlayers() {
        return playersRepository.findAll();
    }

    public List<Player> findAllPlayers() {
        List<Player> players = new ArrayList<>();
        List<TeamPlayers> teamPlayers = findAllTeamPlayers();

        for (TeamPlayers teamPlayer : teamPlayers) {
            players.addAll(teamPlayer.getPlayers());
        }

        return players;
    }

    public List<Player> findPlayersByTeamId(String teamId) {
        return playersRepository.findByTeamId(teamId)
                .map(TeamPlayers::getPlayers)
                .orElseThrow(() -> new RuntimeException("Team not found with ID: " + teamId));
    }
}
