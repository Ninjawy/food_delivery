package com.mentorship.food_delivery.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class LoginResponse<T> {
    T data;
    String token;
    Instant tokenExpirationDate;

    public static <T> LoginResponse<T> create(T data, String token, Instant tokenExpirationDate) {
        return LoginResponse.<T>builder()
                .data(data)
                .token(token)
                .tokenExpirationDate(tokenExpirationDate)
                .build();
    }
}