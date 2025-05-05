package de.plimplom.gamba.server.data;

import de.plimplom.gamba.server.highlow.HighLowRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HighLowTests {
    @Autowired
    private HighLowRepository repository;
    @Autowired
    private ClientDataService clientDataService;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        repository.flush();
    }

    @Test
    void testNewHighLowData() throws Exception {
        var clientData = new ClientData();

        clientData.setAccountId("123");
        clientData.setHighLowDataList(new ArrayList<>());
        clientData.getHighLowDataList().add(buildHighLowData("Test", List.of(new OpponentData("Test2", 100L))));
        clientData.getHighLowDataList().add(buildHighLowData("Test2", List.of(new OpponentData("Test", -100L))));

        clientDataService.processClientData(new ClientDataEvent(this, clientData));

        var res = repository.findAll();

        assertEquals(2, res.size());
        assertEquals("123", res.getFirst().getAccount());
        assertEquals("Test", res.getFirst().getPlayer());
        assertEquals(1, res.getFirst().getOpponentList().size());
        assertEquals("Test2", res.getFirst().getOpponentList().getFirst().getOpponent());
        assertEquals(100L, res.getFirst().getOpponentList().getFirst().getAmount());

        assertEquals("123", res.getLast().getAccount());
        assertEquals("Test2", res.getLast().getPlayer());
        assertEquals(1, res.getLast().getOpponentList().size());
        assertEquals("Test", res.getLast().getOpponentList().getFirst().getOpponent());
        assertEquals(-100L, res.getLast().getOpponentList().getFirst().getAmount());
    }

    @Test
    void testSameHighLowDataOtherAccounts() throws Exception {
        var clientData = new ClientData();

        clientData.setAccountId("123");
        clientData.setHighLowDataList(new ArrayList<>());
        clientData.getHighLowDataList().add(buildHighLowData("Test", List.of(new OpponentData("Test2", 100L))));
        clientData.getHighLowDataList().add(buildHighLowData("Test2", List.of(new OpponentData("Test", -100L))));
        var clientData2 = new ClientData();

        clientData2.setAccountId("456");
        clientData2.setHighLowDataList(new ArrayList<>());
        clientData2.getHighLowDataList().add(buildHighLowData("Test", List.of(new OpponentData("Test2", 100L))));
        clientData2.getHighLowDataList().add(buildHighLowData("Test2", List.of(new OpponentData("Test", -100L))));

        clientDataService.processClientData(new ClientDataEvent(this, clientData));
        clientDataService.processClientData(new ClientDataEvent(this, clientData2));

        var res = repository.findAll();

        assertEquals(4, res.size());
        assertEquals("123", res.getFirst().getAccount());
        assertEquals("Test", res.getFirst().getPlayer());
        assertEquals(1, res.getFirst().getOpponentList().size());
        assertEquals("Test2", res.getFirst().getOpponentList().getFirst().getOpponent());
        assertEquals(100L, res.getFirst().getOpponentList().getFirst().getAmount());

        assertEquals("123", res.get(1).getAccount());
        assertEquals("Test2", res.get(1).getPlayer());
        assertEquals(1, res.get(1).getOpponentList().size());
        assertEquals("Test", res.get(1).getOpponentList().getFirst().getOpponent());
        assertEquals(-100L, res.get(1).getOpponentList().getFirst().getAmount());

        assertEquals("456", res.get(2).getAccount());
        assertEquals("Test", res.get(2).getPlayer());
        assertEquals(1, res.get(2).getOpponentList().size());
        assertEquals("Test2", res.get(2).getOpponentList().getFirst().getOpponent());
        assertEquals(100L, res.get(2).getOpponentList().getFirst().getAmount());

        assertEquals("456", res.get(3).getAccount());
        assertEquals("Test2", res.get(3).getPlayer());
        assertEquals(1, res.get(3).getOpponentList().size());
        assertEquals("Test", res.get(3).getOpponentList().getFirst().getOpponent());
        assertEquals(-100L, res.get(3).getOpponentList().getFirst().getAmount());
    }

    @Test
    void updateExistingData() throws Exception {
        var clientData = new ClientData();

        clientData.setAccountId("123");
        clientData.setHighLowDataList(new ArrayList<>());
        clientData.getHighLowDataList().add(buildHighLowData("Test", List.of(new OpponentData("Test2", 100L))));
        clientData.getHighLowDataList().add(buildHighLowData("Test2", List.of(new OpponentData("Test", -100L))));

        var clientData2 = new ClientData();

        clientData2.setAccountId("123");
        clientData2.setHighLowDataList(new ArrayList<>());
        clientData2.getHighLowDataList().add(buildHighLowData("Test", List.of(new OpponentData("Test2", 500L))));
        clientData2.getHighLowDataList().add(buildHighLowData("Test2", List.of(new OpponentData("Test", -500L))));


        clientDataService.processClientData(new ClientDataEvent(this, clientData));
        var res = repository.findAll();
        assertEquals(2, res.size());
        assertEquals("123", res.getFirst().getAccount());
        assertEquals("Test", res.getFirst().getPlayer());
        assertEquals(1, res.getFirst().getOpponentList().size());
        assertEquals("Test2", res.getFirst().getOpponentList().getFirst().getOpponent());
        assertEquals(100L, res.getFirst().getOpponentList().getFirst().getAmount());

        assertEquals("123", res.getLast().getAccount());
        assertEquals("Test2", res.getLast().getPlayer());
        assertEquals(1, res.getLast().getOpponentList().size());
        assertEquals("Test", res.getLast().getOpponentList().getFirst().getOpponent());
        assertEquals(-100L, res.getLast().getOpponentList().getFirst().getAmount());


        clientDataService.processClientData(new ClientDataEvent(this, clientData2));
        res = repository.findAll();

        assertEquals(2, res.size());
        assertEquals("123", res.getFirst().getAccount());
        assertEquals("Test", res.getFirst().getPlayer());
        assertEquals(1, res.getFirst().getOpponentList().size());
        assertEquals("Test2", res.getFirst().getOpponentList().getFirst().getOpponent());
        assertEquals(500L, res.getFirst().getOpponentList().getFirst().getAmount());

        assertEquals("123", res.getLast().getAccount());
        assertEquals("Test2", res.getLast().getPlayer());
        assertEquals(1, res.getLast().getOpponentList().size());
        assertEquals("Test", res.getLast().getOpponentList().getFirst().getOpponent());
        assertEquals(-500L, res.getLast().getOpponentList().getFirst().getAmount());
    }

    @Test
    void addDataToExisting() throws Exception {
        var clientData = new ClientData();

        clientData.setAccountId("123");
        clientData.setHighLowDataList(new ArrayList<>());
        clientData.getHighLowDataList().add(buildHighLowData("Test", List.of(new OpponentData("Test2", 100L))));
        clientData.getHighLowDataList().add(buildHighLowData("Test2", List.of(new OpponentData("Test", -100L))));

        var clientData2 = new ClientData();

        clientData2.setAccountId("123");
        clientData2.setHighLowDataList(new ArrayList<>());
        clientData2.getHighLowDataList().add(buildHighLowData("Test", List.of(new OpponentData("Test2", 1000L), new OpponentData("Test3", 500L))));
//        clientData2.getHighLowDataList().add(buildHighLowData("Test", List.of(new OpponentData("Test3", 500L))));
        clientData2.getHighLowDataList().add(buildHighLowData("Test2", List.of(new OpponentData("Test", -1000L))));
        clientData2.getHighLowDataList().add(buildHighLowData("Test3", List.of(new OpponentData("Test", -500L))));


        clientDataService.processClientData(new ClientDataEvent(this, clientData));
        var res = repository.findAll();
        assertEquals(2, res.size());
        assertEquals("123", res.getFirst().getAccount());
        assertEquals("Test", res.getFirst().getPlayer());
        assertEquals(1, res.getFirst().getOpponentList().size());
        assertEquals("Test2", res.getFirst().getOpponentList().getFirst().getOpponent());
        assertEquals(100L, res.getFirst().getOpponentList().getFirst().getAmount());

        assertEquals("123", res.getLast().getAccount());
        assertEquals("Test2", res.getLast().getPlayer());
        assertEquals(1, res.getLast().getOpponentList().size());
        assertEquals("Test", res.getLast().getOpponentList().getFirst().getOpponent());
        assertEquals(-100L, res.getLast().getOpponentList().getFirst().getAmount());


        clientDataService.processClientData(new ClientDataEvent(this, clientData2));
        res = repository.findAll();

        assertEquals(3, res.size());
        assertEquals("123", res.getFirst().getAccount());
        assertEquals("Test", res.getFirst().getPlayer());
        assertEquals(2, res.getFirst().getOpponentList().size());
        assertEquals("Test2", res.getFirst().getOpponentList().getFirst().getOpponent());
        assertEquals(1000L, res.getFirst().getOpponentList().getFirst().getAmount());
        assertEquals("Test3", res.getFirst().getOpponentList().getLast().getOpponent());
        assertEquals(500L, res.getFirst().getOpponentList().getLast().getAmount());

        assertEquals("123", res.get(1).getAccount());
        assertEquals("Test2", res.get(1).getPlayer());
        assertEquals(1, res.get(1).getOpponentList().size());
        assertEquals("Test", res.get(1).getOpponentList().getFirst().getOpponent());
        assertEquals(-1000L, res.get(1).getOpponentList().getFirst().getAmount());

        assertEquals("123", res.get(2).getAccount());
        assertEquals("Test3", res.get(2).getPlayer());
        assertEquals(1, res.get(2).getOpponentList().size());
        assertEquals("Test", res.get(2).getOpponentList().getFirst().getOpponent());
        assertEquals(-500L, res.get(2).getOpponentList().getFirst().getAmount());

    }

    private HighLowData buildHighLowData(String player, List<OpponentData> opponentList) {
        var highLow = new HighLowData();
        highLow.setPlayer(player);

        for (OpponentData opponentData : opponentList) {
            var playerData = new HighLowPlayerData();
            playerData.setPlayer(opponentData.opponent);
            playerData.setAmount(opponentData.amount);
            highLow.getHighLowPlayerData().add(playerData);
        }

        return highLow;
    }

    private record OpponentData(String opponent, Long amount) {
    }
}