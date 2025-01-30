package com.mentorship.food_delivery.configuration.environment;

import lombok.Getter;

@Getter
public enum Databases {
    MAIN,
    INITIALIZER;

    public static String getInitializerDatabase() {
        return "template1";
    }
}
