package de.plimplom.gamba.server.stats;

import de.plimplom.gamba.server.alias.AliasEntity;
import de.plimplom.gamba.server.alias.AliasRepository;
import de.plimplom.gamba.server.deathroll.DeathrollEntity;
import de.plimplom.gamba.server.deathroll.DeathrollRepository;
import de.plimplom.gamba.server.highlow.HighLowEntity;
import de.plimplom.gamba.server.highlow.HighLowOpponent;
import de.plimplom.gamba.server.highlow.HighLowOpponentRepository;
import de.plimplom.gamba.server.highlow.HighLowRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatsService {

    private final HighLowRepository highLowRepository;
    private final DeathrollRepository deathrollRepository;
    private final AliasRepository aliasRepository;
    private final HighLowOpponentRepository highLowOpponentRepository;

    public StatsService(HighLowRepository highLowRepository, DeathrollRepository deathrollRepository, AliasRepository aliasRepository, HighLowOpponentRepository highLowOpponentRepository) {
        this.highLowRepository = highLowRepository;
        this.deathrollRepository = deathrollRepository;
        this.aliasRepository = aliasRepository;
        this.highLowOpponentRepository = highLowOpponentRepository;
    }

    public StatsWrapper getFullData() {
        var highLow = getFullHighLowData();
        var deathroll = getFullDeathrollData();

        var combined = new HashMap<>(highLow);

        deathroll.forEach((key, _) -> {
            var highLowValue = combined.get(key);
            var deathrollValue = deathroll.get(key);

            if (highLowValue != null) {
                deathrollValue.getDetailedStats().addAll(highLowValue.getDetailedStats());
                deathrollValue.setFullAmount(highLowValue.getFullAmount() + deathrollValue.getFullAmount());
            }
            deathrollValue.getDetailedStats().sort(DetailedStats::compareTo);
            combined.put(key, deathrollValue);
        });

        var data = combined.values().stream().sorted((o1, o2) -> o2.getFullAmount().compareTo(o1.getFullAmount())).toList();

        return new StatsWrapper(data, deathrollRepository.getTotalGoldGambled() + highLowOpponentRepository.getHighLowGoldAmountGambled());
    }

    public StatsWrapper getDeathrollData() {
        var data = getFullDeathrollData().values().stream().sorted((o1, o2) -> o2.getFullAmount().compareTo(o1.getFullAmount())).toList();
        return new StatsWrapper(data, deathrollRepository.getTotalGoldGambled());
    }

    public StatsWrapper getHighLowData() {
        var data = getFullHighLowData().values().stream().sorted((o1, o2) -> o2.getFullAmount().compareTo(o1.getFullAmount())).toList();
        return new StatsWrapper(data, highLowOpponentRepository.getHighLowGoldAmountGambled());
    }

    private Map<String, StatsDTO> getFullHighLowData() {
        var allAliases = aliasRepository.findAll();
        var allStats = highLowRepository.findAll();
        var map = new HashMap<String, StatsDTO>();
        for (HighLowEntity stat : allStats) {
            var playerName = allAliases.stream().filter(aliasEntity -> aliasEntity.getAlias().equals(stat.getPlayer())).map(AliasEntity::getPlayer).findFirst().orElse(stat.getPlayer());
            var entry = map.getOrDefault(playerName, new StatsDTO());
            entry.setPlayerName(playerName);
//            var fullAmount = 0L;
            for (HighLowOpponent opponent : stat.getOpponentList()) {
                var opponentName = allAliases.stream().filter(aliasEntity -> aliasEntity.getAlias().equals(opponent.getOpponent())).map(AliasEntity::getPlayer).findFirst().orElse(opponent.getOpponent());
                if (entry.getDetailedStats().stream().anyMatch(detailedStats -> detailedStats.getPlayerName().equals(opponentName))) {
                    entry.getDetailedStats().stream().filter(detailedStats -> detailedStats.getPlayerName().equals(opponentName)).forEach(detailedStats -> detailedStats.setAmount(detailedStats.getAmount() + opponent.getAmount()));
                } else {
                    entry.getDetailedStats().add(new DetailedStats(opponentName, opponent.getAmount(), GameMode.HIGH_LOW));
                }
//                fullAmount += opponent.getAmount();
            }
//            entry.setFullAmount(fullAmount);

            map.put(playerName, entry);
        }

        map.values().forEach(statsDTO -> statsDTO.setFullAmount(statsDTO.getDetailedStats().stream().mapToLong(DetailedStats::getAmount).sum()));
        map.values().forEach(statsDTO -> statsDTO.getDetailedStats().sort((o1, o2) -> o2.getAmount().compareTo(o1.getAmount())));

        return map.values().stream().collect(Collectors.toMap(StatsDTO::getPlayerName, statsDTO -> statsDTO));
    }

    private Map<String, StatsDTO> getFullDeathrollData() {
        var allAliases = aliasRepository.findAll();
        var allStats = deathrollRepository.findAll();

        var map = new HashMap<String, StatsDTO>();
        for (DeathrollEntity stat : allStats) {
            var winnerName = allAliases.stream().filter(aliasEntity -> aliasEntity.getAlias().equals(stat.getWinner())).map(AliasEntity::getPlayer).findFirst().orElse(stat.getWinner());
            var loserName = allAliases.stream().filter(aliasEntity -> aliasEntity.getAlias().equals(stat.getLoser())).map(AliasEntity::getPlayer).findFirst().orElse(stat.getLoser());
            var winner = map.getOrDefault(winnerName, new StatsDTO());
            var loser = map.getOrDefault(loserName, new StatsDTO());

            winner.setPlayerName(winnerName);
            winner.getDetailedStats().add(new DetailedStats(loserName, stat.getAmount(), GameMode.DEATH_ROLL));
            winner.setFullAmount(stat.getAmount() + winner.getFullAmount());
            map.put(winnerName, winner);

            loser.setPlayerName(loserName);
            loser.getDetailedStats().add(new DetailedStats(winnerName, -stat.getAmount(), GameMode.DEATH_ROLL));
            loser.setFullAmount(stat.getAmount() - loser.getFullAmount());
            map.put(loserName, loser);
        }

        for (StatsDTO value : map.values()) {
            value.setFullAmount(value.getDetailedStats().stream().mapToLong(DetailedStats::getAmount).sum());
        }

//        map.values().forEach(statsDTO -> statsDTO.setFullAmount(statsDTO.getDetailedStats().stream().mapToLong(DetailedStats::getAmount).sum()));
        map.values().forEach(statsDTO -> statsDTO.getDetailedStats().sort((o1, o2) -> o2.getAmount().compareTo(o1.getAmount())));

        return map;
    }
}
