package com.sg.springscheduler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class DatabaseConfiguration
{

    @Value("${spring.datasource.username}")
    public String user;


    @Value("${spring.datasource.password}")
    public String password;


    @Value("${spring.datasource.url}")
    public String url;


    @Value("${spring.datasource.driver-class-name}")
    public String driver;

    @Autowired
    public DatabaseConfiguration() {
    }

    public String getUser()
    {
        return user;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUrl()
    {
        return url;
    }

    public String getDriver()
    {
        return driver;
    }
}






