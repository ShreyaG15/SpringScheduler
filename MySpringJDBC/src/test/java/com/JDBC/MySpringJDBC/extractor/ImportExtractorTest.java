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

@ExtendWith(MockitoExtension.class)
class ImportExtractorTest {
    @InjectMocks
    ImportExtractor importExtractor;

    @Mock
    ResultSet resultSet;


    @Test
    void extractData() throws SQLException {

        int product_id=1;
        String productName="ipad";
        int brand_id=2;
        int price =100;
        int currency_id=3;

        Product expected=new Product();
        expected.setProduct_id(product_id);
        expected.setProduct_name(productName);
        expected.setBrand_id(brand_id);
        expected.setPrice(price);
        expected.setCurrency_id(currency_id);

        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getInt(1)).thenReturn(product_id);
        Mockito.when(resultSet.getString(2)).thenReturn(productName);
        Mockito.when(resultSet.getInt(3)).thenReturn(brand_id);
        Mockito.when(resultSet.getInt(4)).thenReturn(price);
        Mockito.when(resultSet.getInt(5)).thenReturn(currency_id);

        Product result=importExtractor.extractData(resultSet);

        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expected);




    }
}