package com.keniareis.backend_giro.services;

import com.keniareis.backend_giro.models.Currency;
import com.keniareis.backend_giro.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public Currency createCurrency(Currency currency){
        return currencyRepository.save(currency);
    }

    public List<Currency> getAllCurrencies(){
        return currencyRepository.findAll();
    }

    public Currency getCurrencyById(Long id){
        return currencyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Currency not found"));
    }
}
