package com.JDBC.MySpringJDBC.PrepareStatmentSetter;

import com.JDBC.MySpringJDBC.Models.Product;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductPreparedStatementSetter implements BatchPreparedStatementSetter
{


    private List<Product> products;

    public ProductPreparedStatementSetter(List<Product> productList)
    {
        this.products=productList;
    }

    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException
    {
        Product product=products.get(i);
        ps.setInt(1,product.getProduct_id());
        ps.setString(2,product.getProduct_name());
        ps.setInt(3,product.getBrand_id());
        ps.setInt(4,product.getPrice());
        ps.setInt(5,product.getCurrency_id());

    }

    @Override
    public int getBatchSize()
    {
        return products.size();
    }
}
