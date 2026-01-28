package com.autohub.repo;

import com.autohub.domain.Order;
import com.autohub.domain.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByClientId(Long clientId);
    List<Order> findByMasterId(Long masterId);

    List<Order> findByMasterIdAndStartTimeBetween(Long masterId, LocalDateTime from, LocalDateTime to);

    List<Order> findByMasterIdAndStatusAndStartTimeBetween(Long masterId, OrderStatus status, LocalDateTime from, LocalDateTime to);
}
