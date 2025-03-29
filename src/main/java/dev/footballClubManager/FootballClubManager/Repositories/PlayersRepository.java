package dev.footballClubManager.FootballClubManager.Repositories;

import dev.footballClubManager.FootballClubManager.Models.Players.Player;
import dev.footballClubManager.FootballClubManager.Models.Players.TeamPlayers;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayersRepository extends MongoRepository<TeamPlayers, ObjectId> {

    Optional<TeamPlayers> findByTeamId(String teamId);
}
