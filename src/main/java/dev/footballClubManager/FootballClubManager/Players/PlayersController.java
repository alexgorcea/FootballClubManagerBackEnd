package dev.footballClubManager.FootballClubManager.Players;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayersController {

    @Autowired
    private PlayersService playersService;

    @GetMapping
    public ResponseEntity<List<TeamPlayers>> getAllPlayers() {
        return new ResponseEntity<>(playersService.findAllPlayers(), HttpStatus.OK);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<List<Player>> getTeamPlayers(@PathVariable String teamId){
        return new ResponseEntity<>(playersService.getPlayersByTeamId(teamId), HttpStatus.OK);


    }
}
