package dev.footballClubManager.FootballClubManager.Teams;

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

    public Optional<Team> singleTeam(String id){
        return teamRepository.findTeamById(id);
    }

}
