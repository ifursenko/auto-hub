package com.autohub.api.dto;

public class ServiceResponse {
    private Long id;
    private String name;
    private String description;
    private Integer durationMinutes;
    private Integer basePrice;

    public ServiceResponse(Long id, String name, String description, Integer durationMinutes, Integer basePrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.basePrice = basePrice;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public Integer getBasePrice() {
        return basePrice;
    }
}
