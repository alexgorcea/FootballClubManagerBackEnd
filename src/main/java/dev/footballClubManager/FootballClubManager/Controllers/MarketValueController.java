package dev.footballClubManager.FootballClubManager.Controllers;

import dev.footballClubManager.FootballClubManager.Services.MarketValueService;
import dev.footballClubManager.FootballClubManager.Models.MarketValue.PlayerMarketValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/marketvalue")
public class MarketValueController {

    @Autowired
    MarketValueService marketValueService;

    @GetMapping("/{playerId}")
    public PlayerMarketValue getPlayerMarketValue(@PathVariable String playerId) {
        return marketValueService.findPlayerMarketValue(playerId);
    }
}
