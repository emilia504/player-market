package pl.codema.playermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.codema.playermarket.model.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
