package dev.footballClubManager.FootballClubManager.Models.Players;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Field("id")
    private String playerId;

    private String name;

    private String position;

    private String dateOfBirth;

    private int age;

    private List<String> nationality;

    private int height;

    private String foot;

    private double marketValue;

    private String status;

}

