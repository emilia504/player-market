package pl.codema.playermarket.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.codema.playermarket.exception.PlayerNotExistException;
import pl.codema.playermarket.model.PlayerDto;
import pl.codema.playermarket.model.entity.Player;
import pl.codema.playermarket.repository.PlayerRepository;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    ModelMapper modelMapper;

    public void createPlayer(PlayerDto playerDto) {
        Player player = modelMapper.map(playerDto, Player.class);
        playerRepository.save(player);
    }

    public PlayerDto findById(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new PlayerNotExistException(id));
        PlayerDto playerDto = modelMapper.map(player, PlayerDto.class);
        return playerDto;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Player update(Long id, PlayerDto playerDto) {
        Player player = new Player(id, playerDto.getName(), playerDto.getSurname(), playerDto.getBirthdate(),
                playerDto.getMonthsOfExperience(), playerDto.getTeamPlayers());
        playerRepository.save(player);
        return player;
    }

    public void delete(Long id) {
        playerRepository.deleteById(id);
    }
}
