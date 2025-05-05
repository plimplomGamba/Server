package de.plimplom.gamba.server.data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientData {
    private String accountId;
    private List<HighLowData> highLowDataList;
    private List<DeathrollData> deathrollDataList;
}
