package com.autohub.api.dto;

import java.time.Instant;

public class ProfileResponse {
    private Long id;
    private String phone;
    private String role;
    private String name;
    private Instant createdAt;

    public ProfileResponse(Long id, String phone, String role, String name, Instant createdAt) {
        this.id = id;
        this.phone = phone;
        this.role = role;
        this.name = name;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
