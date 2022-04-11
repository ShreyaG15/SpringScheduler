package com.JDBC.MySpringJDBC.PrepareStatmentSetter;

import com.JDBC.MySpringJDBC.Models.Currency;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CurrencyPreparedStatementSetter implements BatchPreparedStatementSetter{

    private List<Currency> currencyList;

    public CurrencyPreparedStatementSetter(List<Currency> currencyList)
    {
        this.currencyList=currencyList;
    }

    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException
    {
        Currency currency=currencyList.get(i);
        ps.setInt(1,currency.getCurrency_id());
        ps.setString(2, currency.getCurrency_name());
        ps.setString(3, currency.getCountry());
        ps.setString(4, currency.getCurrency_code());

    }

    @Override
    public int getBatchSize()
    {
        return currencyList.size();
    }
}
