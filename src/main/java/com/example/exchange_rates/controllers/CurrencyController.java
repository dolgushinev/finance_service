package com.example.exchange_rates.controllers;

import com.example.exchange_rates.dto.Operation;
import com.example.exchange_rates.dto.ShortCurrency;
import com.example.exchange_rates.dto.ValuteCursOnDate;
import com.example.exchange_rates.service.FinanceService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.exchange_rates.service.CentralRussianBankService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CurrencyController {

    private final CentralRussianBankService centralRussianBankService;
    private final FinanceService financeService;

    @GetMapping("/getCurrencies")
    @ApiOperation("Получить курсы валют")
    public List<ValuteCursOnDate> getValuteCursOnDate() throws Exception {
        return centralRussianBankService.getCurrenciesFromCbr();
    }

    @GetMapping("/getShortCurrencies")
    @ApiOperation("Получить курсы валют в укороченной форме")
    public List<ShortCurrency> getShortCurrencies() throws Exception {
        List<ShortCurrency> shortCurrencies = new ArrayList<ShortCurrency>();
        for (ValuteCursOnDate valuteCursOnDate : centralRussianBankService.getCurrenciesFromCbr()) {
            shortCurrencies.add(new ShortCurrency(valuteCursOnDate.getChCode(), valuteCursOnDate.getName(), valuteCursOnDate.getCourse()));
        }
        return shortCurrencies;
    }

    @GetMapping("/getShortCurrency/{chCode}")
    @ApiOperation("Получить курс конкретной валюты")
    public ShortCurrency getShortCurrencyByChCode(@PathVariable String chCode) throws Exception {
        List<ShortCurrency> shortCurrencies = new ArrayList<ShortCurrency>();
        for (ValuteCursOnDate valuteCursOnDate : centralRussianBankService.getCurrenciesFromCbr()) {
            shortCurrencies.add(new ShortCurrency(valuteCursOnDate.getChCode(), valuteCursOnDate.getName(), valuteCursOnDate.getCourse()));
        }
        try {
            return shortCurrencies.stream().filter(c -> c.getChCode().equals(chCode)).findFirst().get();
        } catch (java.util.NoSuchElementException e) {
            return null;
        }
    }

    @PostMapping("/addOperation")
    @ApiOperation("Добавить финансовую операцию")
    public boolean addFinanceOperation(@RequestBody Operation operation){
        if(financeService.addFinanceOperation(operation.getType(), operation.getSum(), operation.getUser_id()))
        return true;
        else return false;
    }

    @GetMapping("/getSpendsMoreThen")
    @ResponseBody
    public List<Operation> getSpendsMoreThen(@RequestParam Long user_id, @RequestParam BigDecimal amount) {
        return financeService.getSpendsMoreThen(user_id, amount);
    }

}