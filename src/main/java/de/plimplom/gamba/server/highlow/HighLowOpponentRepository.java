package de.plimplom.gamba.server.highlow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HighLowOpponentRepository extends JpaRepository<HighLowOpponent, Long> {

    @Query("SELECT sum(o.amount) FROM HighLowOpponent o WHERE o.amount > 0")
    Long getHighLowGoldAmountGambled();
}
