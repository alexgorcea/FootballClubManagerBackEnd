package dev.footballClubManager.FootballClubManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService{

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> allTeams(){
        return teamRepository.findAll();
    }

}
