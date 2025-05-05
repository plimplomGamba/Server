package de.plimplom.gamba.server.highlow;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "high_low_players")
public class HighLowOpponent {
    @Id
//    @SequenceGenerator(name="seq",sequenceName="high_low_players_seq",allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "player")
    private HighLowEntity player;
    private String opponent;
    private Long amount;

}