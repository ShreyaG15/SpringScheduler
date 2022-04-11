package com.JDBC.MySpringJDBC.PrepareStatmentSetter;

import com.JDBC.MySpringJDBC.Models.Brand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class BrandPreparedStatementSetterTest {

    @InjectMocks
    BrandPreparedStatementSetter brandPreparedStatementSetter;
    @Mock
    PreparedStatement preparedStatement;
    @Test
    void setValues() throws SQLException {

        List<Brand> brandList=new ArrayList<>();
        Brand brand=brandList.get(2);
        brand.setBrand_id(1);
        brand.setBrand_name("APPLE");

        int id=1;
        Mockito.when(brandList.get(2)).thenReturn(brand);
        //Mockito.when(preparedStatement.setInt(1,brand.getBrand_id())).;


    }
}