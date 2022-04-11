package com.JDBC.MySpringJDBC.extractor;

import com.JDBC.MySpringJDBC.Models.Brand;
import com.JDBC.MySpringJDBC.Models.Currency;
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
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ExportExtractorTest {

    @InjectMocks
    ExportExtractor exportExtractor;

    @Mock
    ResultSet resultSet;

    @Test
    void extractData() throws SQLException {
        int product_id=1;
        String productName="iphone";
        String brandName="APPLE";
        int price=100;
        String currencyName="INR";

        List<Product> productList=new ArrayList<>();
        Product expected=new Product();
        expected.setProduct_id(product_id);
        expected.setProduct_name(productName);
        Brand expectedBrand=new Brand();
        expectedBrand.setBrand_name(brandName);
        expected.setBrand(expectedBrand);
        expected.setPrice(price);
        Currency expectedCurrency=new Currency();
        expectedCurrency.setCurrency_name(currencyName);
        expected.setCurrency(expectedCurrency);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(product_id);
        Mockito.when(resultSet.getString(2)).thenReturn(productName);
        Mockito.when(resultSet.getString(3)).thenReturn(brandName);
        Mockito.when(resultSet.getInt(4)).thenReturn(price);
        Mockito.when(resultSet.getString(5)).thenReturn(currencyName);
        productList.add(expected);
        List<Product> result=exportExtractor.extractData(resultSet);
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expected);

    }
}