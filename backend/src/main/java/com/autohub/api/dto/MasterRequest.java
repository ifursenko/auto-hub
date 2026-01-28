package com.autohub.api.dto;

import jakarta.validation.constraints.NotNull;

public class MasterRequest {
    @NotNull
    private Long userId;

    private Double rating;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
