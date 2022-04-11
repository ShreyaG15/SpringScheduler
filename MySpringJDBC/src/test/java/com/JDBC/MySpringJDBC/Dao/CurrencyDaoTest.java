package com.JDBC.MySpringJDBC.Dao;

import com.JDBC.MySpringJDBC.Models.Currency;
import com.JDBC.MySpringJDBC.Query.QuerySet;
import com.JDBC.MySpringJDBC.extractor.CurrencyExtractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CurrencyDaoTest {

    @InjectMocks
    CurrencyDao currencyDao;

    @Mock
    Currency currency;
    @Mock
    JdbcTemplate jdbcTemplate;
    @Mock
    CurrencyExtractor currencyExtractor;

    @Test
    void searchCurrency() {
        String productCurrency="INR";
        Mockito.when(jdbcTemplate.query(QuerySet.LOOKUP_CURRENCY,currencyExtractor,productCurrency)).thenReturn(currency);

        Currency expected=currencyDao.searchCurrency(productCurrency);
        Assertions.assertEquals(expected,currency);
    }

    @Test
    void searchCurrencyCode() {
        String productCurrencyCode="INR";
        Mockito.when(jdbcTemplate.query(QuerySet.LOOKUP_CURRENCY_CODE,currencyExtractor,productCurrencyCode)).thenReturn(currency);

        Currency expected=currencyDao.searchCurrencyCode(productCurrencyCode);
        Assertions.assertEquals(expected,currency);


    }

    @Test
    void getNextCurrency() {
    }

    @Test
    void insertCurrency() {
        int currency_id =1;
        String productCurrency="inr";
        String country="india";
        String currencyCode="INR";

        Mockito.when(jdbcTemplate.update(QuerySet.INSERT_CURRENCY,currency_id,productCurrency,country,currencyCode)).thenReturn(1);
        int result=currencyDao.insertCurrency(currency_id,productCurrency,country,currencyCode);
        Assertions.assertEquals(1,result);

    }

    @Test
    void insertCurrencyForImport() {
        int currency_id=1;
        String currencyCode="INR";
        Mockito.when(jdbcTemplate.update(QuerySet.INSERT_BRAND_FORIMPORT,currency_id,currencyCode)).thenReturn(currency_id);

        int result=currencyDao.insertCurrencyForImport(currency_id,currencyCode);
        Assertions.assertEquals(1,result);
    }

    @Test
    void insertCurrencyUsingBatch() {
    }
}