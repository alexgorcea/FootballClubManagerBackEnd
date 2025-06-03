package dev.footballClubManager.FootballClubManager.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "matches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    @Id
    private String matchId;

    private String homeTeamId;

    private String awayTeamId;

    private Date date;

    private double prize;

    private TicketPrice ticketPrices;

    private TicketsSold ticketsSold;

    private String reviewId;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TicketPrice{
        private int northSeats;
        private int eastSeats;
        private int southSeats;
        private int westSeats;
        private int vipSeats;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TicketsSold{
        private double northSeats;
        private double eastSeats;
        private double southSeats;
        private double westSeats;
        private double vipSeats;
    }

}
