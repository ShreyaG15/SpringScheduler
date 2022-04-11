package com.JDBC.MySpringJDBC.extractor;

import com.JDBC.MySpringJDBC.Models.Currency;
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
class CurrencyExtractorTest {

    @InjectMocks
    CurrencyExtractor currencyExtractor;
    @Mock
    ResultSet resultSet;

    @Test
    void CurrencyExtractorUsingMock() throws SQLException {

         int currency_id =1;
         String currency_name="USD";
         String country="USA";
         String  currency_code="USD";

        Currency expected=new Currency();

        expected.setCurrency_id(currency_id);
        expected.setCurrency_name(currency_name);
        expected.setCountry(country);
        expected.setCurrency_code(currency_code);


        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getInt(1)).thenReturn(currency_id);
        Mockito.when(resultSet.getString(2)).thenReturn(currency_name);
        Mockito.when(resultSet.getString(3)).thenReturn(country);
        Mockito.when(resultSet.getString(4)).thenReturn(currency_code);

        Currency result=currencyExtractor.extractData(resultSet);


        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expected);

    }
}