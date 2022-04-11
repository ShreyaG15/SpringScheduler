package com.JDBC.MySpringJDBC.PrepareStatmentSetter;

import com.JDBC.MySpringJDBC.Models.Brand;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BrandPreparedStatementSetter implements BatchPreparedStatementSetter
{

    private List<Brand> brands;

    public BrandPreparedStatementSetter(List<Brand> brandList)
    {

        this.brands = brandList;
    }


    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException
    {
        Brand brand = brands.get(i);
        ps.setInt(1,brand.getBrand_id());
        ps.setString(2, brand.getBrand_name());
       // ps.setString(3,brand.getBrand_location());
    }

    @Override
    public int getBatchSize()
    {
        return brands.size();
    }
}
