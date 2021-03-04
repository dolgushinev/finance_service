package com.example.exchange_rates.repository;

import com.example.exchange_rates.entity.Spend;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpendRowMapper implements RowMapper<Spend> {

    @Override
    public Spend mapRow(ResultSet resultSet, int i) throws SQLException {
        Spend spend = new Spend();
        spend.setUser_id(resultSet.getLong("user_id"));
        spend.setSpend(resultSet.getBigDecimal("spend"));
        spend.setId(resultSet.getLong("id"));
        spend.setCDate(resultSet.getTimestamp("cDate").toLocalDateTime());
        return spend;
    }
}
