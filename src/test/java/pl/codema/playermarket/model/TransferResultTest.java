package pl.codema.playermarket.model;

import org.junit.jupiter.api.Test;
import pl.codema.playermarket.model.entity.Player;
import pl.codema.playermarket.model.entity.Team;
import pl.codema.playermarket.model.entity.TeamPlayer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TransferResultTest {

    @Test
    void test() {
        Player player = new Player(null, "John", "Doe", LocalDate.of(2000, 7, 21), 10, new HashSet<>());
        Team teamTo = mock(Team.class);
        Team teamFrom = mock(Team.class);
        TeamPlayer currentTeamPlayer = new TeamPlayer(null, player, teamFrom, LocalDate.of(2020, 8, 15), LocalDate.now());
        player.getPlayerTeams().add(currentTeamPlayer);
        teamFrom.getPlayerTeams().add(currentTeamPlayer);
        TeamPlayer newTeamPlayer = new TeamPlayer(null, player, teamTo, LocalDate.now(), null);
        player.getPlayerTeams().add(newTeamPlayer);
        TransferResult transferResult = new TransferResult(player, currentTeamPlayer, 10);

        assertAll(
                () -> assertNotNull(transferResult),
                () -> assertEquals(BigDecimal.valueOf(62857.13), transferResult.getContractFee()),
                () -> assertEquals(BigDecimal.valueOf(57142.85), transferResult.getTransferFee())
        );
    }

}