package com.keniareis.backend_giro.controllers;

import com.keniareis.backend_giro.models.Currency;
import com.keniareis.backend_giro.services.CurrencyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @PostMapping
    public Currency createCurrency(@Valid @RequestBody Currency currency){
        return currencyService.createCurrency(currency);
    }

    @GetMapping
    public List<Currency> getAllCurrencies(){
        return currencyService.getAllCurrencies();
    }
}
