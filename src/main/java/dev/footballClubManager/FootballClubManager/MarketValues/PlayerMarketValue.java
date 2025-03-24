package dev.footballClubManager.FootballClubManager.MarketValues;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerMarketValue {

    @JsonProperty("id")
    private String playerId;

    @JsonProperty("marketValue")
    private double currentMarketValue;

    @JsonProperty("marketValueHistory")
    private List<MarketValueData> marketValueHistory;

    private Map<String,Integer> ranking;
}
