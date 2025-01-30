package com.mentorship.food_delivery;

import com.mentorship.food_delivery.configuration.environment.Databases;
import com.mentorship.food_delivery.configuration.environment.EnvironmentVariables;
import com.mentorship.food_delivery.configuration.environment.ProfileConfiguration;
import com.mentorship.food_delivery.configuration.environment.factories.DatasourceUrlFactory;
import com.mentorship.food_delivery.configuration.environment.factories.ServerPortFactory;
import com.mentorship.food_delivery.configuration.initializer.DatabaseInitializer;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

public class AppRunListener implements SpringApplicationRunListener {

    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        System.out.println(EnvironmentVariables.toStaticString());
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        ProfileConfiguration.initializeProfiles(environment);
        overrideApplicationProperties(environment);
    }

    private static void overrideApplicationProperties(ConfigurableEnvironment environment) {
        DatasourceUrlFactory datasourceUrlFactory = new DatasourceUrlFactory(Databases.MAIN);
        ServerPortFactory serverPortFactory = new ServerPortFactory();

        System.out.println(datasourceUrlFactory);
        System.out.println(serverPortFactory);

        Map<String, Object> overrides = new HashMap<>();
        overrides.put("server.port", serverPortFactory.getServerPort());
        overrides.put("spring.datasource.url", datasourceUrlFactory.getDatasourceUrl());
        overrides.put("spring.datasource.username", EnvironmentVariables.datasourceUsername.value());
        overrides.put("spring.datasource.password", EnvironmentVariables.datasourcePassword.value());

        environment.getPropertySources()
                .addFirst(new MapPropertySource("prepare_datasource", overrides));
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        DatabaseInitializer.init();
    }
}
