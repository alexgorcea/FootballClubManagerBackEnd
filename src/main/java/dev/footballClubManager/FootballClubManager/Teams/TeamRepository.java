package dev.footballClubManager.FootballClubManager.Teams;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TeamRepository extends MongoRepository<Team, Id> {

    Optional<Team> findTeamById(String id);

}
