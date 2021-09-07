package pl.codema.playermarket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.codema.playermarket.exception.TeamNotExistException;
import pl.codema.playermarket.model.TeamDto;
import pl.codema.playermarket.model.entity.Team;
import pl.codema.playermarket.service.TeamService;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    TeamService teamService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTeam(@RequestBody TeamDto teamDto) {
        teamService.createTeam(teamDto);
    }

    @GetMapping("/{id}")
    public TeamDto getTeam(@PathVariable("id") Long id) {
        return teamService.findById(id);
    }

    @GetMapping("/all")
    public List<Team> getAll() {
        return teamService.findAll();
    }

    @PutMapping("/{id}")
    public Team update(@PathVariable("id") Long id, @RequestBody TeamDto teamDto) {
        return teamService.update(id, teamDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        teamService.delete(id);
    }

    @ExceptionHandler(TeamNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleTeamException(TeamNotExistException teamNotExistException) {
        LOGGER.error("Team not found ", teamNotExistException);
        return teamNotExistException.getMessage();
    }

}
