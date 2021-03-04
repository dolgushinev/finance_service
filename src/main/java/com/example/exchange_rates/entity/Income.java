package com.example.exchange_rates.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "INCOMES")
@Data
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID")
    private Long user_id;

    @Column(name = "INCOME")
    private BigDecimal income;

    @Column(name = "CDATE")
    private LocalDateTime cDate;
}