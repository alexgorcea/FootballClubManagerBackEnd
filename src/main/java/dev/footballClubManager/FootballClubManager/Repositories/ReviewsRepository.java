package dev.footballClubManager.FootballClubManager.Repositories;

import dev.footballClubManager.FootballClubManager.Models.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewsRepository extends MongoRepository<Review, String> {

    Optional<Review> findByReviewId(String reviewId);
    void deleteByReviewId(String reviewId);
}
