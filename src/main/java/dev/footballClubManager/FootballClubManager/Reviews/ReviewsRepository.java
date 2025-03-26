package dev.footballClubManager.FootballClubManager.Reviews;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepository extends MongoRepository<Review, ObjectId> {
}
