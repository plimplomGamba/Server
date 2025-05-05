package de.plimplom.gamba.server.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NonNull;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StatsWrapper {
    @NonNull
    private List<@NonNull StatsDTO> statsDTO;
    @NonNull
    private Long totalGoldGambled;
}
