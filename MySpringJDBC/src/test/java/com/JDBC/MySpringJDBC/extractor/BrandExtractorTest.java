package com.JDBC.MySpringJDBC.extractor;

import com.JDBC.MySpringJDBC.Models.Brand;
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
class BrandExtractorTest {
@InjectMocks
    BrandExtractor brandExtractor;
@Mock
ResultSet resultSet;


    @Test
    void BrandExtractorUsingMock() throws SQLException {

         int brand_id =1;
         String brand_name="apple";
         String brand_location="usa";

         Brand expected=new Brand();

         expected.setBrand_id(brand_id);
         expected.setBrand_name(brand_name);
         expected.setBrand_location(brand_location);

        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getInt(1)).thenReturn(brand_id);
        Mockito.when(resultSet.getString(2)).thenReturn(brand_name);
        Mockito.when(resultSet.getString(3)).thenReturn(brand_location);

        Brand result=brandExtractor.extractData(resultSet);

        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
}