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
        review.setWinnerTeamId(calculateWinningTeam(review, matchId));
        review.setTicketEarning(calculateTicketEarning(review, matchId));

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

    private Review.TicketEarning calculateTicketEarning(Review review, String matchId){
        Match.TicketPrice ticketPrice = mongoTemplate.findById(matchId, Match.class).getTicketPrices();
        Match.TicketsSold ticketsSold = mongoTemplate.findById(matchId, Match.class).getTicketsSold();
        Review.Spectators spectatorsToSet = new Review.Spectators(
                ticketsSold.getNorthSeats(),
                ticketsSold.getEastSeats(),
                ticketsSold.getSouthSeats(),
                ticketsSold.getWestSeats(),
                ticketsSold.getVipSeats()
        );
        review.setSpectators(spectatorsToSet);

        double northSeatsEarning = ticketPrice.getNorthSeats() * review.getSpectators().getNorthSeats();
        double eastSeatsEarning = ticketPrice.getEastSeats() * review.getSpectators().getEastSeats();
        double southSeatsEarning = ticketPrice.getSouthSeats() * review.getSpectators().getSouthSeats();
        double westSeatsEarning = ticketPrice.getWestSeats() * review.getSpectators().getWestSeats();
        double vipSeatsEarning = ticketPrice.getVipSeats() * review.getSpectators().getVipSeats();

        Review.TicketEarning ticketEarning = new Review.TicketEarning();

        ticketEarning.setNorthSeatsEarning(northSeatsEarning);
        ticketEarning.setEastSeatsEarning(eastSeatsEarning);
        ticketEarning.setSouthSeatsEarning(southSeatsEarning);
        ticketEarning.setWestSeatsEarning(westSeatsEarning);
        ticketEarning.setVipSeatsEarning(vipSeatsEarning);

        return ticketEarning;

    }

}
