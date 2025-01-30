package com.mentorship.food_delivery.utils;

import java.util.Locale;

public final class StringUtils {
    public static String normalizeString(String s) {
        return s.toLowerCase(Locale.ROOT).trim();
    }
}
