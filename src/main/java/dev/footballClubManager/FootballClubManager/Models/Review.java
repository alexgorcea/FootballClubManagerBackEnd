package dev.footballClubManager.FootballClubManager.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    private String reviewId;

    private int homeTeamScore;

    private int awayTeamScore;

    private String winnerTeamId;

    private double spectators;
}
