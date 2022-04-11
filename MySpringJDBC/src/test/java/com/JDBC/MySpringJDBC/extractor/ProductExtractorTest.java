package com.JDBC.MySpringJDBC.extractor;

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

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductExtractorTest {


    @InjectMocks
    ProductExtractor productExtractor;
    @Mock
    ResultSet resultSet;

    @Test
    void CheckingProductExtractorUsingMock() throws SQLException {

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

        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getInt(1)).thenReturn(productId);
        Mockito.when(resultSet.getString(2)).thenReturn(productName);
        Mockito.when(resultSet.getInt(3)).thenReturn(brandId);
        Mockito.when(resultSet.getInt(4)).thenReturn(price);
        Mockito.when(resultSet.getInt(5)).thenReturn(currencyId);


        Product actual=productExtractor.extractData(resultSet);
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

    }



}