package dev.footballClubManager.FootballClubManager.Services;

import dev.footballClubManager.FootballClubManager.Models.Match;
import dev.footballClubManager.FootballClubManager.Models.Review;
import dev.footballClubManager.FootballClubManager.Repositories.MatchRepository;
import dev.footballClubManager.FootballClubManager.Repositories.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Match> allMatches(){
        return matchRepository.findAll();
    }

    public Optional<Match> getMatchById(String matchId){
        return matchRepository.findByMatchId(matchId);
    }

    public Match createMatch(Match match){
        return matchRepository.save(match);
    }

    public Match updateMatch(Match updatedMatch,String matchId){
        String reviewId = matchRepository.findByMatchId(matchId).get().getReviewId();

        updatedMatch.setReviewId(reviewId);
        updatedMatch.setMatchId(matchId);

        return matchRepository.save(updatedMatch);
    }

    public void removeMatch(String matchId){
        String reviewId = mongoTemplate.findById(matchId, Match.class).getReviewId();

        mongoTemplate.remove(new Query(Criteria.where("reviewId").is(reviewId)), Review.class);
        matchRepository.deleteByMatchId(matchId);
    }


}
