package com.mentorship.food_delivery.utils;

public record KeValuePair<T, R>(T key, R value) {
    public static <T, R> KeValuePair<T, R> create(T key, R value) {
        return new KeValuePair<>(key, value);
    }
}