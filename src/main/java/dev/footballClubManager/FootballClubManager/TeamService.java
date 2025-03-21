package dev.footballClubManager.FootballClubManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService{

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> allTeams(){
        return teamRepository.findAll();
    }

    public Optional<Team> getTeamById(String id){
        return teamRepository.findTeamById(id);
    }

}
