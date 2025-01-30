package com.mentorship.food_delivery.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}