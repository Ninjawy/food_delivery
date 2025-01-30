package com.mentorship.food_delivery.utils;

import java.util.function.Consumer;

public interface ObjectConfigurator {
    static <T> T apply(T object, Consumer<T> block) {
        block.accept(object);
        return object;
    }
}