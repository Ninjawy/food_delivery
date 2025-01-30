package com.mentorship.food_delivery.model.security;

import lombok.Getter;

@Getter
public enum HeaderType {
    AUTHORIZATION("Authorization");
    private final String headerKey;

    HeaderType(String headerKey) {
        this.headerKey = headerKey;
    }
}
