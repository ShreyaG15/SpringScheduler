package com.JDBC.MySpringJDBC.extractor;

import com.JDBC.MySpringJDBC.Models.Brand;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BrandExtractor implements ResultSetExtractor<Brand>
{
    @Override
    public Brand extractData(ResultSet rs) throws SQLException, DataAccessException
    {
        Brand result = null;
        if (rs.next())
        {
            result = new Brand(rs.getInt(1), rs.getString(2), rs.getString(3));
            return result;
        }
        return result;
    }
}
