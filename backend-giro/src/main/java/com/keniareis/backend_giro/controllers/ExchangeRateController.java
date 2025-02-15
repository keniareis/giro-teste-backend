package com.keniareis.backend_giro.controllers;

import com.keniareis.backend_giro.dto.ExchangeRateDTO;
import com.keniareis.backend_giro.dto.RecentRateResponseDTO;
import com.keniareis.backend_giro.models.ExchangeRate;
import com.keniareis.backend_giro.services.CurrencyService;
import com.keniareis.backend_giro.services.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchange-rates")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private CurrencyService currencyService;

    @PostMapping
    public ExchangeRate createExchangeRate(@RequestBody ExchangeRateDTO exchangeRateDTO){
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setDate(exchangeRateDTO.getDate());
        exchangeRate.setDailyVariation(exchangeRateDTO.getDailyVariation());
        exchangeRate.setDailyRate(exchangeRateDTO.getDailyRate());

        exchangeRate.setCurrency(currencyService.getCurrencyById(exchangeRateDTO.getCurrencyId()));

        return exchangeRateService.createExchangeRate(exchangeRate);
    }

    @GetMapping("/recent")
    public List<RecentRateResponseDTO> getRecentRates(){
        return exchangeRateService.getRecentRates();
    }
}
