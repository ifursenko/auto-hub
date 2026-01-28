package com.autohub.repo;

import com.autohub.domain.ServiceStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceStationRepository extends JpaRepository<ServiceStation, Long> {
}
