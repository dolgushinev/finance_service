package com.example.exchange_rates.repository;

import com.example.exchange_rates.entity.Spend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpendRepository extends JpaRepository<Spend, Long> {
}