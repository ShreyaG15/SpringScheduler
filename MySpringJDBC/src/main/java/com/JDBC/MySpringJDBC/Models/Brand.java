package com.JDBC.MySpringJDBC.Models;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Brand {

    private int brand_id;
    private String brand_name;
    private String brand_location;

    public Brand() {
    }

    public Brand(int brand_id, String brand_name, String brand_location) {
        this.brand_id = brand_id;
        this.brand_name = brand_name;
        this.brand_location = brand_location;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public int setBrand_id(int brand_id) {
        this.brand_id = brand_id;
        return brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_location() {
        return brand_location;
    }

    public void setBrand_location(String brand_location) {
        this.brand_location = brand_location;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brand_id=" + brand_id +
                ", brand_name='" + brand_name + '\'' +
                ", brand_location='" + brand_location + '\'' +
                '}';
    }
}
