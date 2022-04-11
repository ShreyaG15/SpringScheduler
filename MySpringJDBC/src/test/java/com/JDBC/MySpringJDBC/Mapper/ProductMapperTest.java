package com.JDBC.MySpringJDBC.Mapper;

import com.JDBC.MySpringJDBC.Models.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {

    @InjectMocks
    ProductMapper productMapper;
    @Mock
    ResultSet resultSet;


    @Test
    void mapRowValidation() throws SQLException {

        int  productId=1;
        String productName="iphone";
        int brandId=2;
        int price=10000;
        int currencyId=3;

        Product expected=new Product();

        expected.setProduct_id(productId);
        expected.setProduct_name(productName);
        expected.setBrand_id(brandId);
        expected.setPrice(price);
        expected.setCurrency_id(currencyId);

        Mockito.when(resultSet.getInt(1)).thenReturn(productId);
        Mockito.when(resultSet.getString(2)).thenReturn(productName);
        Mockito.when(resultSet.getInt(3)).thenReturn(brandId);
        Mockito.when(resultSet.getInt(4)).thenReturn(price);
        Mockito.when(resultSet.getInt(5)).thenReturn(currencyId);

      //        Mockito.verify(resultSet).getString(2);
     //   Assertions.assertThat(resultSet.getString(2)).isEqualTo(productName);
        Product product = productMapper.mapRow(resultSet, 1);
        Assertions.assertThat(product).usingRecursiveComparison().isEqualTo(expected);
    }
}