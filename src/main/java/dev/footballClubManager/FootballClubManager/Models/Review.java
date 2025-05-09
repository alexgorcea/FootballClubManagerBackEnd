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

    private Spectators spectators;

    private TicketEarning ticketEarning;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Spectators{
        private double northSeats;
        private double eastSeats;
        private double southSeats;
        private double westSeats;
        private double vipSeats;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TicketEarning{
        private double northSeatsEarning;
        private double eastSeatsEarning;
        private double southSeatsEarning;
        private double westSeatsEarning;
        private double vipSeatsEarning;
    }
}
