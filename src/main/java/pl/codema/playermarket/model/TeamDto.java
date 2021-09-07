package pl.codema.playermarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.codema.playermarket.model.entity.TeamPlayer;

import java.util.Currency;
import java.util.Set;

@Data
@AllArgsConstructor
public class TeamDto {

    private String name;
    private Currency currency;
    private Set<TeamPlayer> teamPlayers;

}
