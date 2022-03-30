package com.sg.springscheduler.entity;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class BatchInsertWrapper implements BatchPreparedStatementSetter {

    private Customer customers;


    public BatchInsertWrapper(Customer customers) {
        this.customers = customers;
    }

    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException {

        System.out.println(customers);
        ps.setString(1,customers.getId());
        ps.setString(2,customers.getUserName());
        ps.setString(3,customers.getFirstName());
        ps.setString(4, customers.getLastName());
        ps.setString(5, customers.getPhoneNumber());
        ps.setString(6, customers.getEmail());
        ps.setString(7,customers.getStatus().toString());
        ps.setString(8,customers.getPreferredLanguage().toString());
    }

    @Override
    public int getBatchSize() {
        return 1;
    }


}
