package dev.footballClubManager.FootballClubManager.Repositories;

import dev.footballClubManager.FootballClubManager.Models.Team;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TeamRepository extends MongoRepository<Team, ObjectId> {

    Optional<Team> findTeamById(String id);


}
