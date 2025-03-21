package dev.footballClubManager.FootballClubManager;

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
}
