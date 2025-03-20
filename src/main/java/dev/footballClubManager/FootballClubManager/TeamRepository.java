package dev.footballClubManager.FootballClubManager;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamRepository extends MongoRepository<Team, Id> {

}
