package dev.footballClubManager.FootballClubManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/players")
public class PlayersController {

    @Autowired
    private PlayersService playersService;

    @GetMapping
    public ResponseEntity<List<TeamPlayers>> getAllPlayers() {
        return new ResponseEntity<List<TeamPlayers>>(playersService.findAllPlayers(), HttpStatus.OK);
    }
}
