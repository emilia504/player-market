package pl.codema.playermarket.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;
    @Setter
    private String surname;
    @Setter
    private LocalDate birthdate;
    @Setter
    private Integer monthsOfExperience;

    @Setter
    @OneToMany(mappedBy = "player", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<TeamPlayer> playerTeams;

    public Player(String name, String surname, LocalDate birthdate, Integer monthsOfExperience) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.monthsOfExperience = monthsOfExperience;
    }

}
