package pl.codema.playermarket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.codema.playermarket.model.entity.Player;
import pl.codema.playermarket.model.entity.TeamPlayer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Currency;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransferResult {

    private Player player;
    private BigDecimal transferFee;
    private BigDecimal teamCommision;
    private BigDecimal contractFee;
    private Currency currency;

    public TransferResult(Player player, TeamPlayer previousTeam, Integer teamCommisionPercent) {
        this.player = player;
        this.currency = previousTeam.getTeam().getCurrency();
        long monthsOfExperience = ChronoUnit.MONTHS.between(previousTeam.getMemberFrom(), previousTeam.getMemberTo());
        long age = ChronoUnit.YEARS.between(player.getBirthdate(), LocalDate.now());
        BigDecimal bigAge = BigDecimal.valueOf(age);
        this.transferFee = BigDecimal.valueOf(monthsOfExperience * 100_000).divide(bigAge, 2, RoundingMode.FLOOR);
        BigDecimal teamCommisionFrac = BigDecimal.valueOf(teamCommisionPercent).divide(BigDecimal.valueOf(100), 2, RoundingMode.FLOOR);
        teamCommision = transferFee.multiply(teamCommisionFrac).setScale(2, RoundingMode.FLOOR);
        this.contractFee = this.transferFee.add(teamCommision);
    }

}
