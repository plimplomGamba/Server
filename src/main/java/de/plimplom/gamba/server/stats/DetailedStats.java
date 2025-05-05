package de.plimplom.gamba.server.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

@Getter
@Setter
@AllArgsConstructor
public class DetailedStats {
    @NonNull
    private String playerName;
    @NonNull
    private Long amount;
    @Nullable
    private GameMode gameMode;

    public int compareTo(@NonNull DetailedStats other) {
//        var gameModeComparison = 0;
//
//        if (gameMode != null && other.gameMode != null) {
//            gameModeComparison = gameMode.compareTo(other.gameMode);
//        } else if (gameMode != null) {
//            gameModeComparison = 1;
//        } else if (other.gameMode != null) {
//            gameModeComparison = -1;
//        }
//
//        if (gameModeComparison == 0) {
//            return other.getAmount().compareTo(amount);
//        } else {
//            return gameModeComparison;
//        }

        return other.getAmount().compareTo(amount);
    }
}
