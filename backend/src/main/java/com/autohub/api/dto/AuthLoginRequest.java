package com.autohub.api.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthLoginRequest {
    @NotBlank
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
