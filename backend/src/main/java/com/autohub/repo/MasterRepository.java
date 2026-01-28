package com.autohub.repo;

import com.autohub.domain.Master;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<Master, Long> {
    List<Master> findByStationId(Long stationId);
    Optional<Master> findByUserId(Long userId);
}
