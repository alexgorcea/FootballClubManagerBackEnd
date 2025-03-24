package dev.footballClubManager.FootballClubManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
