package dev.footballClubManager.FootballClubManager.Players;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamPlayers {

    @Id
    private ObjectId objectId;

    private String teamId;

    private List<Player> players;

}
