package com.JDBC.MySpringJDBC.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Product {

    private int product_id;
    private String product_name;
    private int brand_id;
    private int price;
    private int currency_id;

    @Autowired
    private Brand brand;

    @Autowired
    private Currency currency;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Product( ) {
    }


    public Product(int product_id, String product_name, int brand_id, int price, int currency_id) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.brand_id = brand_id;
        this.price = price;
        this.currency_id = currency_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(int currency_id) {
        this.currency_id = currency_id;
    }


    @Override
    public String toString() {
        return "ProductModel{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", brand_id=" + brand_id +
                ", price=" + price +
                ", currency_id=" + currency_id +
                '}';
    }
}
