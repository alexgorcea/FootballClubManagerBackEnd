package dev.footballClubManager.FootballClubManager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "teams")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    private String objectId;

    private String id;

    private String name;

    private String image;

    private String stadiumName;

    private int currentMarketValue;

    private Squad squad;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Squad {
        private int size;
        private int averageAge;
    }
}
