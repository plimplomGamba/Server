package de.plimplom.gamba.server.data;

import de.plimplom.gamba.server.deathroll.DeathrollEntity;
import de.plimplom.gamba.server.deathroll.DeathrollRepository;
import de.plimplom.gamba.server.highlow.HighLowEntity;
import de.plimplom.gamba.server.highlow.HighLowOpponent;
import de.plimplom.gamba.server.highlow.HighLowRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClientDataService {

    private final HighLowRepository highLowRepository;
    private final DeathrollRepository deathrollRepository;

    public ClientDataService(HighLowRepository highLowRepository, DeathrollRepository deathrollRepository) {
        this.highLowRepository = highLowRepository;
        this.deathrollRepository = deathrollRepository;
    }

    @EventListener
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void processClientData(ClientDataEvent event) {
        processHighLowData(event.getData().getAccountId(), event.getData().getHighLowDataList());
        processDeathrollData(event.getData().getDeathrollDataList());
    }

    private void processDeathrollData(List<DeathrollData> deathrollList) {
        var allDeathrolls = deathrollRepository.findAll();

        var newDeathrolls = deathrollList.stream().map(deathrollData -> new DeathrollEntity(deathrollData.getTimestamp(), deathrollData.getWinner(), deathrollData.getLoser(), deathrollData.getAmount())).filter(deathrollEntity -> !allDeathrolls.contains(deathrollEntity)).toList();

        deathrollRepository.saveAll(newDeathrolls);
    }

    protected void processHighLowData(String accountId, List<HighLowData> highLowDataList) {
        // First, fetch existing entities for this account
        List<HighLowEntity> existingEntities = highLowRepository.findByAccount(accountId);
        Map<String, HighLowEntity> existingEntityMap = existingEntities.stream()
                .collect(Collectors.toMap(HighLowEntity::getPlayer, entity -> entity));

        List<HighLowEntity> entitiesToSave = highLowDataList.stream().map(highLowData -> {
            String playerName = highLowData.getPlayer();
            HighLowEntity highlowEntity;

            // Check if entity already exists for this player
            if (existingEntityMap.containsKey(playerName)) {
                // Update existing entity
                highlowEntity = existingEntityMap.get(playerName);

                // Create a map of existing opponents for quick lookup
                Map<String, HighLowOpponent> existingOpponents = highlowEntity.getOpponentList().stream()
                        .collect(Collectors.toMap(HighLowOpponent::getOpponent, opponent -> opponent));

                // Process each opponent
                for (HighLowPlayerData highLowOpponent : highLowData.getHighLowPlayerData()) {
                    String opponentName = highLowOpponent.getPlayer();

                    if (existingOpponents.containsKey(opponentName)) {
                        // Update amount for existing opponent
                        existingOpponents.get(opponentName).setAmount(highLowOpponent.getAmount());
                    } else {
                        // Add new opponent
                        var opponent = new HighLowOpponent();
                        opponent.setPlayer(highlowEntity);
                        opponent.setOpponent(opponentName);
                        opponent.setAmount(highLowOpponent.getAmount());
                        highlowEntity.getOpponentList().add(opponent);
                    }
                }
            } else {
                // Create new entity
                highlowEntity = new HighLowEntity();
                highlowEntity.setAccount(accountId);
                highlowEntity.setPlayer(playerName);
                highlowEntity.setOpponentList(new ArrayList<>());

                // Add all opponents
                for (HighLowPlayerData highLowOpponent : highLowData.getHighLowPlayerData()) {
                    var opponent = new HighLowOpponent();
                    opponent.setPlayer(highlowEntity);
                    opponent.setOpponent(highLowOpponent.getPlayer());
                    opponent.setAmount(highLowOpponent.getAmount());
                    highlowEntity.getOpponentList().add(opponent);
                }
            }

            return highlowEntity;
        }).collect(Collectors.toList());

        highLowRepository.saveAll(entitiesToSave);
        highLowRepository.flush();
    }
}
