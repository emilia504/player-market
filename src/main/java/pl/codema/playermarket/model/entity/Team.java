package pl.codema.playermarket.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Currency;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter
    String name;

    @Setter
    Currency currency;

    @Setter
    @OneToMany(mappedBy = "team", orphanRemoval = true, cascade = CascadeType.ALL)
    Set<TeamPlayer> playerTeams;

}
