package pl.codema.playermarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.codema.playermarket.model.entity.TeamPlayer;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
public class PlayerDto {

    private String name;
    private String surname;
    private LocalDate birthdate;
    private Integer monthsOfExperience;
    private Set<TeamPlayer> teamPlayers;

}
