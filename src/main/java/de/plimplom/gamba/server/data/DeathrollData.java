package de.plimplom.gamba.server.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeathrollData {
    private Long timestamp;
    private String winner;
    private String loser;
    private Long amount;
}
