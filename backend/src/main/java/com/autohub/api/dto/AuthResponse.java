package com.autohub.api.dto;

public class AuthResponse {
    private Long userId;
    private String role;
    private String name;
    private String token;

    public AuthResponse(Long userId, String role, String name, String token) {
        this.userId = userId;
        this.role = role;
        this.name = name;
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }
}
