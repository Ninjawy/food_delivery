package com.mentorship.food_delivery.configuration.environment;

import com.mentorship.food_delivery.utils.KeValuePair;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.List;
import java.util.stream.Collectors;


public class EnvironmentVariables {

    private static final String DATASOURCE_USERNAME = "DATABASE_USERNAME";
    private static final String DATASOURCE_PASSWORD = "DATABASE_PASSWORD";
    private static final String DOCKER_DATASOURCE_URL_DOMAIN = "DOCKER_DATASOURCE_URL_DOMAIN";
    private static final String LOCAL_DATASOURCE_URL_DOMAIN = "LOCAL_DATASOURCE_URL_DOMAIN";
    private static final String DOCKER_SERVER_PORT = "DOCKER_SERVER_PORT";
    private static final String LOCAL_SERVER_PORT = "LOCAL_SERVER_PORT";
    private static final String APP_DATASOURCE = "APP_DATASOURCE";

    private static final Dotenv dotenv = Dotenv.load();

    public static final KeValuePair<String, String> datasourceUsername =
            KeValuePair.create(DATASOURCE_USERNAME, dotenv.get(DATASOURCE_USERNAME));

    public static final KeValuePair<String, String> dockerPort = KeValuePair.create(DOCKER_SERVER_PORT, dotenv.get(DOCKER_SERVER_PORT));

    public static final KeValuePair<String, String> localPort = KeValuePair.create(LOCAL_SERVER_PORT, dotenv.get(LOCAL_SERVER_PORT));

    public static final KeValuePair<String, String> datasourcePassword =
            KeValuePair.create(DATASOURCE_PASSWORD, dotenv.get(DATASOURCE_PASSWORD));

    public static final KeValuePair<String, String> dockerDatasourceDomain =
            KeValuePair.create(DOCKER_DATASOURCE_URL_DOMAIN, dotenv.get(DOCKER_DATASOURCE_URL_DOMAIN));

    public static final KeValuePair<String, String> localDatasourceDomain =
            KeValuePair.create(LOCAL_DATASOURCE_URL_DOMAIN, dotenv.get(LOCAL_DATASOURCE_URL_DOMAIN));

    public static final KeValuePair<String, String> appDatasource =
            KeValuePair.create(APP_DATASOURCE, dotenv.get(APP_DATASOURCE));

    public static final List<KeValuePair<String, String>> environmentVariables =
            List.of(datasourceUsername,
                    datasourcePassword,
                    dockerDatasourceDomain,
                    localDatasourceDomain,
                    appDatasource,
                    dockerPort,
                    localPort);

    private EnvironmentVariables() {
    }

    public static String toStaticString() {
        String s = environmentVariables.stream()
                .map(pair -> pair.key() + '=' + pair.value())
                .collect(Collectors.joining(", "));
        return String.format("%s{%s}", EnvironmentVariables.class.getSimpleName(), s);
    }
}
