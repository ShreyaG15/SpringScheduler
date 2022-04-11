package com.JDBC.MySpringJDBC.extractor;

import com.JDBC.MySpringJDBC.Models.Brand;
import com.JDBC.MySpringJDBC.Models.Currency;
import com.JDBC.MySpringJDBC.Models.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExportExtractor implements ResultSetExtractor<List<Product>>
{
    @Override
    public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException
    {
        Product product;

        List<Product> products = new ArrayList<>();

        while (rs.next()) {
            product = new Product();

            product.setProduct_id(rs.getInt(1));
            product.setProduct_name(rs.getString(2));

            Brand brand = new Brand();
            brand.setBrand_name(rs.getString(3));
            product.setBrand(brand);

            product.setPrice(rs.getInt(4));

            Currency currency = new Currency();
            currency.setCurrency_name(rs.getString(5));

           // System.out.printf("cname: " + currency.getCurrency_name());

            product.setCurrency(currency);

            products.add(product);
        }
        return products;
    }
}
