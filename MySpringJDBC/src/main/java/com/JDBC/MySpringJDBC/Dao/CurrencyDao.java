package com.JDBC.MySpringJDBC.Dao;

import com.JDBC.MySpringJDBC.Models.Currency;
import com.JDBC.MySpringJDBC.PrepareStatmentSetter.CurrencyPreparedStatementSetter;
import com.JDBC.MySpringJDBC.Query.QuerySet;
import com.JDBC.MySpringJDBC.extractor.CurrencyExtractor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.JDBC.MySpringJDBC.Query.QuerySet.*;

@Repository
public class CurrencyDao {

    private final JdbcTemplate jdbcTemplate;
    private final QuerySet querySet;
    private final CurrencyExtractor currencyExtractor;

    public CurrencyDao(JdbcTemplate jdbcTemplate, QuerySet querySet, CurrencyExtractor currencyExtractor) {
        this.jdbcTemplate = jdbcTemplate;
        this.querySet = querySet;
        this.currencyExtractor = currencyExtractor;
    }

    public Currency searchCurrency(String productCurrency) {

        Currency currency = jdbcTemplate.query(LOOKUP_CURRENCY, currencyExtractor, productCurrency);
        return currency;

    }
    public Currency searchCurrencyCode(String productCurrency) {

        Currency currency = jdbcTemplate.query(LOOKUP_CURRENCY_CODE, currencyExtractor, productCurrency);
        return currency;

    }



    public int getNextCurrency() {
        return jdbcTemplate.queryForObject(GET_MAX_CURRENCY_ID, Integer.class) + 1;

    }

    public int insertCurrency(int currency_id, String productCurrency, String country, String currencyCode) {

        int currency = jdbcTemplate.update(INSERT_CURRENCY, currency_id, productCurrency, country, currencyCode);

        return currency;
    }
    public int insertCurrencyForImport(int currency_id, String currencyCode) {

        int currency = jdbcTemplate.update(INSERT_CURRENCY_FOR_IMPORT, currency_id,null,null,currencyCode);

        return currency;
    }

    public int[] insertCurrencyUsingBatch(List<Currency> currencyList) {
        return jdbcTemplate.batchUpdate(INSERT_CURRENCY_FOR_IMPORT,new CurrencyPreparedStatementSetter(currencyList));

    }
}
