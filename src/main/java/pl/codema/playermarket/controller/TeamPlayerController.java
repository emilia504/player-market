package pl.codema.playermarket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.codema.playermarket.exception.TransferException;
import pl.codema.playermarket.model.TransferResult;
import pl.codema.playermarket.model.entity.Player;
import pl.codema.playermarket.service.TeamPlayerService;

@RestController
@RequestMapping("/teamplayer")
public class TeamPlayerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamPlayerController.class);

    @Autowired
    TeamPlayerService teamPlayerService;

    @PutMapping("/addToTeam")
    @ResponseStatus(HttpStatus.OK)
    public void addTeam(
            @RequestParam(value = "playerId") Long playerId,
            @RequestParam(value = "teamId") Long teamId) {
        teamPlayerService.addPlayerToTeam(playerId, teamId);
    }

    @PutMapping("/transfer")
    public TransferResult transfer(
            @RequestParam(value = "playerId") Long playerId,
            @RequestParam(value = "teamId") Long teamId,
            @RequestParam(value = "teamCommision") Integer teamCommision) {
        return teamPlayerService.transferPlayer(playerId, teamId, teamCommision);
    }

    @DeleteMapping("/removeFromTeam")
    public Player removeTeam(
            @RequestParam(value = "playerId") Long playerId,
            @RequestParam(value = "teamId") Long teamId) {
        return teamPlayerService.removePlayerFromTeam(playerId, teamId);
    }

    @ExceptionHandler(TransferException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleTransferException(TransferException transferException) {
        LOGGER.error(transferException.getMessage());
        return transferException.getMessage();
    }

}
