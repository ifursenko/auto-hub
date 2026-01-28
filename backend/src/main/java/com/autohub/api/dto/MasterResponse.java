package com.autohub.api.dto;

public class MasterResponse {
    private Long id;
    private Long userId;
    private Long stationId;
    private Double rating;
    private String name;

    public MasterResponse(Long id, Long userId, Long stationId, Double rating, String name) {
        this.id = id;
        this.userId = userId;
        this.stationId = stationId;
        this.rating = rating;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getStationId() {
        return stationId;
    }

    public Double getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }
}
