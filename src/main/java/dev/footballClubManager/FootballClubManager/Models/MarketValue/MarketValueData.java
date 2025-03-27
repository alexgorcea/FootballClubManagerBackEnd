package dev.footballClubManager.FootballClubManager.Models.MarketValue;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketValueData {

    private int age;

    private String date;

    @JsonProperty("clubId")
    private String teamId;

    @JsonProperty("clubName")
    private String teamName;

    private double marketValue;
}
