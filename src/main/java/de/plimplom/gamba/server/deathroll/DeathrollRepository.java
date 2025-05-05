package de.plimplom.gamba.server.deathroll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeathrollRepository extends JpaRepository<DeathrollEntity, Long> {

    @Query("SELECT sum(o.amount) FROM deathroll o WHERE o.amount > 0")
    Long getTotalGoldGambled();
}
