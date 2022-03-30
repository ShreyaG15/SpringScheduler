package com.sg.springscheduler.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Autowired
    DatabaseConfiguration databaseConfiguration;

    @Bean("scanner")
    public Scanner getScanner() {
        return new Scanner(System.in);
    }

    @Bean("connection")
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseConfiguration.getUrl(), databaseConfiguration.getUser(), databaseConfiguration.getPassword());
    }

}
