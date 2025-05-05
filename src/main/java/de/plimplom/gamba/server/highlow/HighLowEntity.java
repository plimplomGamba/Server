package de.plimplom.gamba.server.highlow;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "high_low")
@Getter
@Setter
public class HighLowEntity {
    @Id
//    @SequenceGenerator(name="seq",sequenceName="high_low_seq",allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private String player;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "player", cascade = CascadeType.ALL)
    private List<HighLowOpponent> opponentList;
}
