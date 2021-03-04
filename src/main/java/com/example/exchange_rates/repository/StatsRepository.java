package com.example.exchange_rates.repository;

import com.example.exchange_rates.entity.Spend;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Data
@Repository
public class StatsRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Spend> getSpendsMoreThen(Long user_id, BigDecimal amount) {
        List<Spend> ls = jdbcTemplate.query("SELECT * FROM SPEND WHERE USER_ID = ? AND SPEND > ? ", new SpendRowMapper(), user_id, amount);
        return ls;
    }

}
