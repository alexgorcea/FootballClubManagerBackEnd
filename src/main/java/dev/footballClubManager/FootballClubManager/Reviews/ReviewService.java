package dev.footballClubManager.FootballClubManager.Reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewsRepository reviewsRepository;

    public List<Review> allReviews(){
        return reviewsRepository.findAll();
    }
}
