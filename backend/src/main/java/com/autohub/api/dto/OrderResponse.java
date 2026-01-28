package com.autohub.api.dto;

import java.time.LocalDateTime;

public class OrderResponse {
    private Long id;
    private Long clientId;
    private Long masterId;
    private Long carId;
    private Long serviceId;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer totalPrice;
    private Integer clientRating;

    public OrderResponse(Long id, Long clientId, Long masterId, Long carId, Long serviceId, String status,
                         LocalDateTime startTime, LocalDateTime endTime, Integer totalPrice, Integer clientRating) {
        this.id = id;
        this.clientId = clientId;
        this.masterId = masterId;
        this.carId = carId;
        this.serviceId = serviceId;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalPrice = totalPrice;
        this.clientRating = clientRating;
    }

    public Long getId() {
        return id;
    }

    public Long getClientId() {
        return clientId;
    }

    public Long getMasterId() {
        return masterId;
    }

    public Long getCarId() {
        return carId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public Integer getClientRating() {
        return clientRating;
    }
}
