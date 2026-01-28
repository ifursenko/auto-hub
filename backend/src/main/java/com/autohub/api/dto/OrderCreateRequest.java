package com.autohub.api.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class OrderCreateRequest {
    @NotNull
    private Long masterId;

    @NotNull
    private Long carId;

    @NotNull
    private Long serviceId;

    @NotNull
    private LocalDateTime startTime;

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
