package pl.codema.playermarket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.codema.playermarket.exception.PlayerNotExistException;
import pl.codema.playermarket.model.PlayerDto;
import pl.codema.playermarket.model.entity.Player;
import pl.codema.playermarket.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    PlayerService playerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPlayer(@RequestBody PlayerDto player) {
        playerService.createPlayer(player);
    }

    @GetMapping("/{id}")
    public PlayerDto getPlayer(@PathVariable("id") Long id) {
        return playerService.findById(id);
    }

    @GetMapping("/all")
    public List<Player> getAll() {
        return playerService.findAll();
    }

    @PutMapping("/{id}")
    public Player update(@PathVariable("id") Long id, @RequestBody PlayerDto playerDto) {
        return playerService.update(id, playerDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        playerService.delete(id);
    }

    @ExceptionHandler(PlayerNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handlePlayerException(PlayerNotExistException playerNotExistException) {
        LOGGER.error("Player not found ", playerNotExistException);
        return playerNotExistException.getMessage();
    }

}
