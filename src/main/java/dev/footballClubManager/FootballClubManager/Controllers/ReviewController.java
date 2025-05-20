package dev.footballClubManager.FootballClubManager.Controllers;

import dev.footballClubManager.FootballClubManager.Models.Review;
import dev.footballClubManager.FootballClubManager.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:5173")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(){
        return new ResponseEntity<>(reviewService.allReviews(), HttpStatus.OK);
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<Optional<Review>> getMatchReview(@PathVariable String matchId){
        return new ResponseEntity<>(reviewService.getReview(matchId), HttpStatus.OK);
    }

    @PostMapping("/add-to/{matchId}")
    public ResponseEntity<Review> addReview(@RequestBody Review review, @PathVariable String matchId){
        return new ResponseEntity<>(reviewService.createReview(review,matchId),HttpStatus.CREATED);
    }

    @PostMapping("/edit/{reviewId}")
    public ResponseEntity<Review> editReview(@RequestBody Review review, @PathVariable String reviewId){
        return new ResponseEntity<>(reviewService.updateReview(review,reviewId),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable String reviewId){
        reviewService.removeReview(reviewId);
        return new ResponseEntity<>("Review deleted",HttpStatus.OK);
    }
}
