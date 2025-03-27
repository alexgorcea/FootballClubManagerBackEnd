package dev.footballClubManager.FootballClubManager.Services;

import dev.footballClubManager.FootballClubManager.Models.Match;
import dev.footballClubManager.FootballClubManager.Models.Review;
import dev.footballClubManager.FootballClubManager.Repositories.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Review> allReviews(){
        return reviewsRepository.findAll();
    }

    public Optional<Review> getReview(String matchId){
        String reviewId = mongoTemplate.findById(matchId, Match.class).getReviewId();
        return reviewsRepository.findByReviewId(reviewId);
    }

    public Review createReview(Review review, String matchId){
        Review savedReview = reviewsRepository.save(review);
        mongoTemplate.update(Match.class)
                .matching(Criteria.where("matchId").is(matchId))
                .apply(new Update().set("reviewId", review.getReviewId()))
                .first();

        return savedReview;
    }

    public Review updateReview(Review updatedReview, String reviewId){
        updatedReview.setReviewId(reviewId);
        return reviewsRepository.save(updatedReview);
    }

    public void removeReview(String reviewId){
        reviewsRepository.deleteByReviewId(reviewId);
    }

}
