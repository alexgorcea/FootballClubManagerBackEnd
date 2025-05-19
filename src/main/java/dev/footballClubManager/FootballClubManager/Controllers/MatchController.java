package dev.footballClubManager.FootballClubManager.Controllers;


import dev.footballClubManager.FootballClubManager.Services.MatchService;
import dev.footballClubManager.FootballClubManager.Models.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matches")
@CrossOrigin(origins = "http://localhost:5173")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches(){
        return new ResponseEntity<>(matchService.allMatches(),HttpStatus.OK);
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<Optional<Match>> getMatch(@PathVariable String matchId){
        return new ResponseEntity<>(matchService.getMatchById(matchId),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Match> addMatch(@RequestBody Match match){
        return new ResponseEntity<>(matchService.createMatch(match),HttpStatus.OK);
    }

    @PostMapping("/edit/{matchId}")
    public ResponseEntity<Match> editMatch(@RequestBody Match updatedMatch, @PathVariable String matchId){
        return new ResponseEntity<>(matchService.updateMatch(updatedMatch,matchId),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{matchId}")
    public ResponseEntity<String> deleteMatch(@PathVariable String matchId){
        matchService.removeMatch(matchId);
        return new ResponseEntity<>("Match deleted",HttpStatus.OK);
    }
}
