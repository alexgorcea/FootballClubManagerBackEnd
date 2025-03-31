package dev.footballClubManager.FootballClubManager.Services;

import dev.footballClubManager.FootballClubManager.Models.Match;
import dev.footballClubManager.FootballClubManager.Models.Review;
import dev.footballClubManager.FootballClubManager.Repositories.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
        String winnerTeam = calculateWinningTeam(review, matchId);
        review.setWinnerTeamId(winnerTeam);

        Review savedReview = reviewsRepository.save(review);

        mongoTemplate.update(Match.class)
                .matching(Criteria.where("matchId").is(matchId))
                .apply(new Update().set("reviewId", review.getReviewId()))
                .first();

        return savedReview;
    }

    public Review updateReview(Review updatedReview, String reviewId){
        updatedReview.setReviewId(reviewId);

        Query query = new Query(Criteria.where("reviewId").is(reviewId));
        String matchId = mongoTemplate.findOne(query,Match.class).getMatchId();

        updatedReview.setWinnerTeamId(calculateWinningTeam(updatedReview, matchId));

        return reviewsRepository.save(updatedReview);
    }

    public void removeReview(String reviewId){
        Query query = new Query(Criteria.where("reviewId").is(reviewId));
        Update update = new Update().set("reviewId",null);
        mongoTemplate.updateFirst(query, update, Match.class);

        reviewsRepository.deleteByReviewId(reviewId);
    }

    private String calculateWinningTeam(Review review, String matchId){
        String awayTeamId = mongoTemplate.findById(matchId, Match.class).getAwayTeamId();
        String homeTeamId = mongoTemplate.findById(matchId, Match.class).getHomeTeamId();

        if(review.getHomeTeamScore() > review.getAwayTeamScore()){
            return homeTeamId;
        } else if (review.getAwayTeamScore() > review.getHomeTeamScore()) {
            return awayTeamId;
        }

        return "Draw";
    }

}
