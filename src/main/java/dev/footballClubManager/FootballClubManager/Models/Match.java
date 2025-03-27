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
    private ObjectId objectId;

    private String matchId;

    private String homeTeamId;

    private String awayTeamId;

    private Date date;

    private double prize;

    private int ticketPrice;

    private Review review;

}
