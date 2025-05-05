package de.plimplom.gamba.server.stats;

import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StatsDTO {
    private @NonNull String playerName;
    private @NonNull List<@NonNull DetailedStats> detailedStats = new ArrayList<>();
    private @NonNull Long fullAmount;

    public StatsDTO() {
        this.fullAmount = 0L;
    }
}
