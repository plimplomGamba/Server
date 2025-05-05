package de.plimplom.gamba.server.data;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HighLowData {
    private String player;
    private List<HighLowPlayerData> highLowPlayerData=new ArrayList<>();
}
