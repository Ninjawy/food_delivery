package com.mentorship.food_delivery.configuration.environment;

import lombok.Getter;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
public final class ProfileConfiguration {
    public static final String DOCKER = "docker";
    private static Set<String> profile;

    public static synchronized void initializeProfiles(ConfigurableEnvironment environment) {
        if (profile == null) {
            profile = Arrays.stream(environment.getActiveProfiles())
                    .collect(Collectors.toSet());
        } else {
            throw new IllegalStateException(String.format("%s already initialized",
                    ProfileConfiguration.class.getSimpleName()));
        }
    }

    public static Set<String> getInstance() {
        return profile;
    }
}

