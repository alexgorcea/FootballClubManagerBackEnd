package dev.footballClubManager.FootballClubManager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private String id;

    private String name;

    private String position;

    private String dateOfBirth;

    private int age;

    private List<String> nationality;

    private int height;

    private String foot;

    private int marketValue;

}
