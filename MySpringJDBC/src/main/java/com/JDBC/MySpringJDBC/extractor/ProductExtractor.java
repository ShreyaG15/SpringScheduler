package com.JDBC.MySpringJDBC.extractor;

import com.JDBC.MySpringJDBC.Models.Currency;
import com.JDBC.MySpringJDBC.Models.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductExtractor implements ResultSetExtractor<Product>
{

    @Override
    public Product extractData(ResultSet rs) throws SQLException, DataAccessException
    {
        Product result = null;
        if (rs.next())
        {
            result = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
      }
        return result;
    }
}
