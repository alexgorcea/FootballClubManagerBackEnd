package dev.footballClubManager.FootballClubManager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamPlayers {

    @Id
    private String objectId;

    private String teamId;

    private List<Player> players;

}
