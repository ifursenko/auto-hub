package com.autohub.repo;

import com.autohub.domain.Car;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByUserId(Long userId);
}
