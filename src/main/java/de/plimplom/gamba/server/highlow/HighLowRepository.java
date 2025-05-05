package de.plimplom.gamba.server.highlow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HighLowRepository extends JpaRepository<HighLowEntity, Long> {
    List<HighLowEntity> findByAccount(String account);

    List<HighLowEntity> findAllByAccount(String account);

}
