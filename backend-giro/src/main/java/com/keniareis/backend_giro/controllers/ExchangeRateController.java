package com.keniareis.backend_giro.controllers;

import com.keniareis.backend_giro.models.ExchangeRate;
import com.keniareis.backend_giro.services.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange-rates")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @PostMapping
    public ExchangeRate createExchangeRate(@RequestBody ExchangeRate exchangeRate){
        return exchangeRateService.createExchangeRate(exchangeRate);
    }
}
