package pl.codema.playermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.codema.playermarket.model.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
