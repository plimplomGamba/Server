package de.plimplom.gamba.server.deathroll;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity(name = "deathroll")
@Getter
@Setter
@NoArgsConstructor
public class DeathrollEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long timestamp;
    private String winner;
    private String loser;
    private Long amount;

    public DeathrollEntity(Long timestamp, String winner, String loser, Long amount) {
        this.timestamp = timestamp;
        this.winner = winner;
        this.loser = loser;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DeathrollEntity that = (DeathrollEntity) o;
        return Objects.equals(timestamp, that.timestamp) && Objects.equals(winner, that.winner) && Objects.equals(loser, that.loser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, winner, loser);
    }
}
