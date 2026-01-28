package com.autohub.api.dto;

public class CarResponse {
    private Long id;
    private String brand;
    private String model;
    private Integer year;
    private String vin;

    public CarResponse(Long id, String brand, String model, Integer year, String vin) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.vin = vin;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public String getVin() {
        return vin;
    }
}
