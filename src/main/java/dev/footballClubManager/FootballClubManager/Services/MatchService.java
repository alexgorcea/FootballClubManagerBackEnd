package dev.footballClubManager.FootballClubManager.Services;

import dev.footballClubManager.FootballClubManager.Models.Match;
import dev.footballClubManager.FootballClubManager.Repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public List<Match> allMatches(){
        return matchRepository.findAll();
    }

    public Optional<Match> getMatchById(String matchId){
        return matchRepository.findByMatchId(matchId);
    }

    public Match createMatch(Match match){
        return matchRepository.save(match);
    }

    public void removeMatch(String matchId){
        matchRepository.deleteByMatchId(matchId);
    }


}
