package com.sg.springscheduler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    private final DatabaseConfiguration databaseConfiguration;

    @Autowired
    public JdbcConfig(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(databaseConfiguration.getDriver())
                .url(databaseConfiguration.getUrl())
                .username(databaseConfiguration.getUser())
                .password(databaseConfiguration.getPassword())
                .build();
    }


}
