package dev.footballClubManager.FootballClubManager.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tickets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    private String id;
    private String matchId;
    private String matchTitle;
    private String seat;
    private int quantity;
    private double price;

    private String userId;
}