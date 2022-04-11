package com.JDBC.MySpringJDBC.Config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Scanner;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(){

        return  DataSourceBuilder.create()
              .driverClassName("org.postgresql.Driver")
              .url("jdbc:postgresql://localhost:5433/ShoppingApplication")
              .username("postgres")
                .password("")
              .build();
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

}
