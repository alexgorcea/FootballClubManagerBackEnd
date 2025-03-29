package dev.footballClubManager.FootballClubManager.Controllers;

import dev.footballClubManager.FootballClubManager.Models.Players.Player;
import dev.footballClubManager.FootballClubManager.Models.Players.TeamPlayers;
import dev.footballClubManager.FootballClubManager.Services.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayersController {

    @Autowired
    private PlayersService playersService;

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        return new ResponseEntity<>(playersService.findAllPlayers(), HttpStatus.OK);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<List<Player>> getTeamPlayers(@PathVariable String teamId){
        return new ResponseEntity<>(playersService.findPlayersByTeamId(teamId), HttpStatus.OK);

    }
}
