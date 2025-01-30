package com.mentorship.food_delivery.configuration.initializer;

import com.mentorship.food_delivery.configuration.environment.Databases;
import com.mentorship.food_delivery.configuration.environment.factories.DatasourceUrlFactory;
import com.mentorship.food_delivery.configuration.environment.EnvironmentVariables;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


public class DatabaseInitializer {

    private static final String CREATE_DATABASE_SQL = "CREATE DATABASE food_delivery";
    private static final String CHECK_DATABASE_EXISTENCE_SQL = "SELECT 1 FROM pg_database WHERE datname = 'food_delivery'";

    public synchronized static void init() {
        DataSource dataSource = getDataSource();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        try {
            jdbcTemplate.queryForObject(CHECK_DATABASE_EXISTENCE_SQL, Integer.class);
            System.out.println("Database 'food_delivery' already exists.");
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Database 'food_delivery' does not exist, creating it...");
            jdbcTemplate.execute(CREATE_DATABASE_SQL);
            System.out.println("Database 'food_delivery' created successfully.");
        }
    }

    private static DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(EnvironmentVariables.datasourceUsername.value());
        dataSource.setPassword(EnvironmentVariables.datasourcePassword.value());
        dataSource.setUrl(new DatasourceUrlFactory(Databases.INITIALIZER).getDatasourceUrl());
        return dataSource;
    }
}
