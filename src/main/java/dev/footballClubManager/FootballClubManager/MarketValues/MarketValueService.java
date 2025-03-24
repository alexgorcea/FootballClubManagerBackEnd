package dev.footballClubManager.FootballClubManager.MarketValues;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MarketValueService {

    private RestTemplate restTemplate;

    public MarketValueService() {
        this.restTemplate = new RestTemplate();
    }

    public PlayerMarketValue findPlayerMarketValue(String playerId) {
        String url = "https://transfermarkt-api.fly.dev/players/" + playerId + "/market_value";

        return restTemplate.getForObject(url, PlayerMarketValue.class);
    }
}
