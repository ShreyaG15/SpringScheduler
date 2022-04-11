package com.JDBC.MySpringJDBC.Models;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Currency {

    private int currency_id;
    private String currency_name;
    private String country;
    private String currency_code;

    public Currency() {
    }

    public Currency(int currency_id, String currency_name, String country, String currency_code) {
        this.currency_id = currency_id;
        this.currency_name = currency_name;
        this.country = country;
        this.currency_code = currency_code;
    }

    public int getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(int currency_id) {
        this.currency_id = currency_id;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currency_id=" + currency_id +
                ", currency_name='" + currency_name + '\'' +
                ", country='" + country + '\'' +
                ", currency_code='" + currency_code + '\'' +
                '}';
    }
}
