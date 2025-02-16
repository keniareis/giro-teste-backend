package com.keniareis.backend_giro.controllers;

import com.keniareis.backend_giro.dto.ExchangeRateDTO;
import com.keniareis.backend_giro.dto.RecentRateResponseDTO;
import com.keniareis.backend_giro.models.ExchangeRate;
import com.keniareis.backend_giro.services.CurrencyService;
import com.keniareis.backend_giro.services.ExchangeRateService;
import jakarta.validation.Valid;
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
    public ExchangeRate createExchangeRate(@Valid @RequestBody ExchangeRateDTO exchangeRateDTO){
        if (exchangeRateDTO.getCurrencyId() == null){
            throw new IllegalArgumentException("currency_id must not be null");
        }

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

    @PutMapping("/{id}")
    public ExchangeRate updateExchangeRate(@PathVariable Long id, @Valid @RequestBody ExchangeRateDTO updateDTO){
        return exchangeRateService.updateExchangeRate(id, updateDTO);
    }

    @DeleteMapping("/old")
    public String deleteOldRates(){
        exchangeRateService.deleteOldRates();
        return "Old exchange rates deleted successfully";
    }
}
