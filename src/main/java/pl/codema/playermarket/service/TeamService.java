package pl.codema.playermarket.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.codema.playermarket.exception.TeamNotExistException;
import pl.codema.playermarket.model.TeamDto;
import pl.codema.playermarket.model.entity.Team;
import pl.codema.playermarket.repository.TeamRepository;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ModelMapper modelMapper;

    public void createTeam(TeamDto teamDto) {
        Team team = modelMapper.map(teamDto, Team.class);
        teamRepository.save(team);
    }

    public TeamDto findById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotExistException(id));
        TeamDto teamDto = modelMapper.map(team, TeamDto.class);
        return teamDto;
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Team update(Long id, TeamDto teamDto) {
        Team team = new Team(id, teamDto.getName(), teamDto.getCurrency(), teamDto.getTeamPlayers());
        teamRepository.save(team);
        return team;
    }

    public void delete(Long id) {
        teamRepository.deleteById(id);
    }
}
