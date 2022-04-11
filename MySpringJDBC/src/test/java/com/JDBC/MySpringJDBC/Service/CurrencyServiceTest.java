package com.JDBC.MySpringJDBC.Service;

import com.JDBC.MySpringJDBC.Config.ScannerConfig;
import com.JDBC.MySpringJDBC.Dao.CurrencyDao;
import com.JDBC.MySpringJDBC.Models.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @InjectMocks
    CurrencyService currencyService;

    @Mock
    CurrencyDao currencyDao;

    @Mock
    ScannerConfig scannerConfig;
    @Mock
    Currency currency;

    @Test
    void currency_Validattion_If_It_Is_NULL() {

        String currencyName="USD";

        Mockito.when(scannerConfig.scannerNext()).thenReturn(currencyName);
        Mockito.when(currencyDao.searchCurrency(currencyName)).thenReturn(null);

        int id = test_InsertCurrency_By_CurrencyName();

        Assertions.assertEquals(-1,id);

       currencyService.currencyValidattion();
    }
    @Test
    void currency_Validattion_If_It_Is_Not_NULL() {

        String currencyName="USD";
        int currency_id=1;

        currency.setCurrency_id(currency_id);

        Mockito.when(scannerConfig.scannerNext()).thenReturn(currencyName);
        Mockito.when(currencyDao.searchCurrency(currencyName)).thenReturn(currency);
        Mockito.when(currency.getCurrency_id()).thenReturn(currency_id);

        int id = currencyService.currencyValidattion();

        Assertions.assertEquals(id,currency_id);
    }

    @Test
    int test_InsertCurrency_By_CurrencyName(){

        String country="USA";
        String currencyCode="USD";

        String currencyName="USD";

        Mockito.when(scannerConfig.scannerNext()).thenReturn(country).thenReturn(currencyCode);
        Mockito.when(currencyDao.getNextCurrency()).thenReturn(1);

        int newCurrencyId = getNewCurrencyId();
        Assertions.assertEquals(-1,newCurrencyId);
        return newCurrencyId;
    }
    @Test
    int getNewCurrencyId() {

        Mockito.when(currencyDao.searchCurrency("USD")).thenReturn(null);
        int newCurrencyId=currencyService.getNewCurrencyId("USD");
        Assertions.assertEquals(-1,newCurrencyId);

        return newCurrencyId;
    }

    @Test
    void getNewCurrency_Id() {

        int currency_id=1;
        currency.setCurrency_id(currency_id);
        Mockito.when(currencyDao.searchCurrency("USD")).thenReturn(currency);
        int newCurrencyId=currencyService.getNewCurrencyId("USD");
        Assertions.assertEquals(currency.getCurrency_id(),newCurrencyId);

    }



}