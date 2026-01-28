package com.autohub.api.dto;

import java.util.List;

public class StationResponse {
    private Long id;
    private String name;
    private String address;
    private Double lat;
    private Double lng;
    private Long ownerId;
    private List<Long> serviceIds;
    private Double rating;

    public StationResponse(Long id, String name, String address, Double lat, Double lng,
                           Long ownerId, List<Long> serviceIds, Double rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.ownerId = ownerId;
        this.serviceIds = serviceIds;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public List<Long> getServiceIds() {
        return serviceIds;
    }

    public Double getRating() {
        return rating;
    }
}
