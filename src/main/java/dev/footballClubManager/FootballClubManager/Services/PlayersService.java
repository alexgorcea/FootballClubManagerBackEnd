package dev.footballClubManager.FootballClubManager.Services;

import dev.footballClubManager.FootballClubManager.Models.Players.Player;
import dev.footballClubManager.FootballClubManager.Models.Players.TeamPlayers;
import dev.footballClubManager.FootballClubManager.Repositories.PlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayersService {

    @Autowired
    private PlayersRepository playersRepository;

    public List<TeamPlayers> findAllPlayers() {
        return playersRepository.findAll();
    }

    public List<Player> getPlayersByTeamId(String teamId) {
        return playersRepository.findByTeamId(teamId)
                .map(TeamPlayers::getPlayers)
                .orElseThrow(() -> new RuntimeException("Team not found with ID: " + teamId));
    }
}
