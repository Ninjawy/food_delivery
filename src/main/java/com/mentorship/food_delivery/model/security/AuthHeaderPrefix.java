package com.mentorship.food_delivery.model.security;

import lombok.Getter;

@Getter
public enum AuthHeaderPrefix {
    BASIC("Basic "),
    BEARER("Bearer ");

    private final String prefix;

    AuthHeaderPrefix(String prefix) {
        this.prefix = prefix;
    }
}
