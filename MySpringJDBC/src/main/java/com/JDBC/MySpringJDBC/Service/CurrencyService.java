package com.JDBC.MySpringJDBC.Service;

import com.JDBC.MySpringJDBC.Config.DataSourceConfig;
import com.JDBC.MySpringJDBC.Config.ScannerConfig;
import com.JDBC.MySpringJDBC.Dao.CurrencyDao;
import com.JDBC.MySpringJDBC.Models.Currency;
import com.JDBC.MySpringJDBC.extractor.CurrencyExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CurrencyService
{
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final DataSourceConfig dataSourceConfig;
    private final CurrencyExtractor currencyExtractor;
    private final CurrencyDao currencyDao;
    private final ScannerConfig scannerConfig;

    @Autowired
    public CurrencyService(DataSourceConfig dataSourceConfig, CurrencyExtractor currencyExtractor, CurrencyDao currencyDao, ScannerConfig scannerConfig)
    {
        this.dataSourceConfig = dataSourceConfig;
        this.currencyExtractor = currencyExtractor;
        this.currencyDao = currencyDao;
        this.scannerConfig = scannerConfig;
    }

    public int currencyValidattion()
    {
        int currency_id;

        logger.info("\nEnter Currency name for new product: ");

        String productCurrency =scannerConfig.scannerNext();

        Currency currencyResult = currencyDao.searchCurrency(productCurrency);

        if (currencyResult != null)
        {
            currency_id = currencyResult.getCurrency_id();
            return currency_id;
        } else
        {
            logger.info("The provided currency doesn't exist :");
            int newCurrency_id = insertCurrency(productCurrency);
            return newCurrency_id;
        }
    }

    public int insertCurrency(String productCurrencyName)
    {
        //Scanner sc = dataSourceConfig.scanner();
        logger.info("Enter Country ");
        String country = scannerConfig.scannerNext();

        logger.info("Enter Currency code ");
        String currencyCode = scannerConfig.scannerNext();

        int currency_id = currencyDao.getNextCurrency();

        currencyDao.insertCurrency(currency_id, productCurrencyName, country, currencyCode);
       int newCurrency_Id = getNewCurrencyId(productCurrencyName);


        return newCurrency_Id;
    }

    public int getNewCurrencyId(String productCurrency)
    {
        int currency_id;
        Currency currencyResult = currencyDao.searchCurrency(productCurrency);
        if (currencyResult != null)
        {
            currency_id = currencyResult.getCurrency_id();
            return currency_id;
        }
        return -1;
    }
}
