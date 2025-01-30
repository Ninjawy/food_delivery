package com.mentorship.food_delivery.configuration.environment.factories;

import com.mentorship.food_delivery.configuration.environment.Databases;
import com.mentorship.food_delivery.configuration.environment.EnvironmentVariables;
import com.mentorship.food_delivery.configuration.environment.ProfileConfiguration;

import java.util.Set;

public class DatasourceUrlFactory {
    private final Databases databases;

    public DatasourceUrlFactory(Databases databases) {
        this.databases = databases;
    }

    public String getDatasourceUrl() {
        Set<String> profiles = ProfileConfiguration.getInstance();

        if (profiles.contains(ProfileConfiguration.DOCKER)) {
            return prepare(EnvironmentVariables.dockerDatasourceDomain.value());
        }
        return prepare(EnvironmentVariables.localDatasourceDomain.value());
    }

    private String prepare(String domain) {
        String datasource = (databases == Databases.MAIN)
                ? EnvironmentVariables.appDatasource.value()
                : Databases.getInitializerDatabase();
        return domain + '/' + datasource;
    }

    @Override
    public String toString() {
        return String.format("%s{%s}", DatasourceUrlFactory.class.getSimpleName(), getDatasourceUrl());
    }
}