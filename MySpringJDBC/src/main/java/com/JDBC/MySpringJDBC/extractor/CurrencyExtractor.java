package com.JDBC.MySpringJDBC.extractor;

import com.JDBC.MySpringJDBC.Models.Currency;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CurrencyExtractor implements ResultSetExtractor<Currency>
{
    @Override
    public Currency extractData(ResultSet rs) throws SQLException, DataAccessException
    {
        Currency result = null;
        if (rs.next())
        {
            result = new Currency(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4));
            return result;
        }

        return result;
    }
}
