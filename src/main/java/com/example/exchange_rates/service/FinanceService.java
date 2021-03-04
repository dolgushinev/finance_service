package com.example.exchange_rates.service;

import com.example.exchange_rates.dto.Operation;
import com.example.exchange_rates.entity.Income;
import com.example.exchange_rates.entity.Spend;
import com.example.exchange_rates.repository.IncomeRepository;
import com.example.exchange_rates.repository.SpendRepository;
import com.example.exchange_rates.repository.StatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinanceService {

    private final IncomeRepository incomeRepository;
    private final SpendRepository spendRepository;
    private final StatsRepository statsRepository;

    public boolean addFinanceOperation(String operationType, String price, Long user_id) {

        if (operationType.equals("income")) {
            Income income = new Income();
            income.setUser_id(user_id);
            income.setIncome(new BigDecimal(price));
            income.setCDate(LocalDateTime.now());
            incomeRepository.save(income);
            return true;
        } else if (operationType.equals("spend")) {
            Spend spend = new Spend();
            spend.setUser_id(user_id);
            spend.setSpend(new BigDecimal(price));
            spend.setCDate(LocalDateTime.now());
            spendRepository.save(spend);
            return true;
        }
        return false;
    }

    public List<Operation> getSpendsMoreThen(Long user_id, BigDecimal amount) {

        List<Spend> spends = statsRepository.getSpendsMoreThen(user_id, amount);
        List<Operation> operations = new ArrayList<>();

        for (Spend s : spends
        ) {
            operations.add(new Operation(s.getUser_id(), "spend", s.getSpend().toString(), s.getCDate().toString()));

            new Operation();
        }

        return operations;
    }
}
