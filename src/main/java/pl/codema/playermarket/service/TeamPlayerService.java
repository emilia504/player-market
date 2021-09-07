package pl.codema.playermarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.codema.playermarket.exception.PlayerNotExistException;
import pl.codema.playermarket.exception.TeamNotExistException;
import pl.codema.playermarket.exception.TransferException;
import pl.codema.playermarket.model.TransferResult;
import pl.codema.playermarket.model.entity.Player;
import pl.codema.playermarket.model.entity.Team;
import pl.codema.playermarket.model.entity.TeamPlayer;
import pl.codema.playermarket.repository.PlayerRepository;
import pl.codema.playermarket.repository.TeamRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeamPlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    public Player addPlayerToTeam(Long playerId, Long teamId) {
        final Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotExistException(playerId));
        Optional<Team> playerTeam = findPlayerTeam(player);
        if (playerTeam.isPresent()) {
            throw new TransferException("Could not add player to team. Player already assigned to team " + playerTeam.get().getId() + ". You should use transfer method.");
        }
        final Team team = teamRepository.findById(teamId).orElseThrow(() -> new TeamNotExistException(teamId));
        final TeamPlayer teamPlayer = new TeamPlayer(null, player, team, LocalDate.now(), null);
        player.getPlayerTeams().add(teamPlayer);
        return playerRepository.save(player);
    }

    private Optional<Team> findPlayerTeam(Player player) {
        Set<Team> teams = player.getPlayerTeams().stream()
                .filter(teamPlayer -> teamPlayer.getMemberTo() == null).map(TeamPlayer::getTeam)
                .collect(Collectors.toSet());
        if (teams.size() > 1)
            throw new TransferException("Unexpected exception");
        if (teams.size() == 1) {
            return teams.stream().findFirst();
        }
        return Optional.empty();
    }

    public Player removePlayerFromTeam(Long playerId, Long teamId) {
        final Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotExistException(playerId));
        final boolean removed = player.getPlayerTeams().removeIf(team -> teamId.equals(team.getId()));
        if (!removed) {
            throw new TeamNotExistException(teamId);
        }
        return playerRepository.save(player);
    }

    public TransferResult transferPlayer(Long playerId, Long teamId, Integer teamCommisionPercent) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotExistException(playerId));
        Team teamTo = teamRepository.findById(teamId).orElseThrow(() -> new TeamNotExistException(teamId));

        Optional<Team> currentTeam = findPlayerTeam(player);

        currentTeam.ifPresent(team -> player.getPlayerTeams().stream()
                .filter(teamPlayer -> teamPlayer.getMemberTo() == null).findFirst().get().setMemberTo(LocalDate.now()));

        TeamPlayer newTeamPlayer = new TeamPlayer(null, player, teamTo, LocalDate.now(), null);

        player.getPlayerTeams().add(newTeamPlayer);
        Player updatedPlayer = playerRepository.save(player);
        return new TransferResult(updatedPlayer, newTeamPlayer, teamCommisionPercent);
    }

}
