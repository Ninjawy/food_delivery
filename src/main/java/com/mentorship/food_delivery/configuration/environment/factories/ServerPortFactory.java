package com.mentorship.food_delivery.configuration.environment.factories;

import com.mentorship.food_delivery.configuration.environment.EnvironmentVariables;
import com.mentorship.food_delivery.configuration.environment.ProfileConfiguration;

import java.util.Set;

public class ServerPortFactory {

    public String getServerPort() {
        Set<String> profiles = ProfileConfiguration.getInstance();

        if (profiles.contains(ProfileConfiguration.DOCKER)) {
            return EnvironmentVariables.dockerPort.value();
        }
        return EnvironmentVariables.localPort.value();
    }

    @Override
    public String toString() {
        return String.format("%s{%s}", ServerPortFactory.class.getSimpleName(), getServerPort());
    }
}