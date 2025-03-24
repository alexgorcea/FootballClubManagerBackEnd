package dev.footballClubManager.FootballClubManager;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayersRepository extends MongoRepository<TeamPlayers, Id> {

    Optional<TeamPlayers> findByTeamId(String teamId);
}
