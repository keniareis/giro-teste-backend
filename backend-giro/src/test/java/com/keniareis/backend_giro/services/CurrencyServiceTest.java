package com.keniareis.backend_giro.services;

import com.keniareis.backend_giro.exceptions.DuplicateCurrencyException;
import com.keniareis.backend_giro.models.Currency;
import com.keniareis.backend_giro.repository.CurrencyRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @Autowired
    @InjectMocks
    private CurrencyService currencyService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create currency successfully when everything is ok")
    void createCurrency_Success() {
        Currency currency = new Currency();
        currency.setName("Dólar Americano");
        currency.setType("USD");

        when(currencyRepository.findByName(currency.getName())).thenReturn(Optional.empty());
        when(currencyRepository.findByType(currency.getType())).thenReturn(Optional.empty());
        when(currencyRepository.save(any(Currency.class))).thenReturn(currency);

        Currency createdCurrency = currencyService.createCurrency(currency);

        assertNotNull(createdCurrency);
        assertEquals("Dólar Americano", createdCurrency.getName());
        assertEquals("USD", createdCurrency.getType());
        verify(currencyRepository, times(1)).save(any(Currency.class));
    }

    @Test
    @DisplayName("Should throw DuplicatedCurrencyException when currency name already exists")
    void createCurrencyDuplicateName(){
        Currency currency = new Currency();
        currency.setName("Dólar Americano");
        currency.setType("USD");

        when(currencyRepository.findByName(currency.getName())).thenReturn(Optional.of(currency));

        DuplicateCurrencyException exception = assertThrows(DuplicateCurrencyException.class, () -> {
            currencyService.createCurrency(currency);
        });

        assertEquals("Currency with name Dólar Americano already exists", exception.getMessage());
        
        verify(currencyRepository, never()).save(any(Currency.class));
    }

    @Test
    @DisplayName("Should throw DuplicatedCurrencyException when currency type already exists")
    void createCurrencyDuplicateType(){
        Currency currency = new Currency();
        currency.setName("Dólar Americano");
        currency.setType("USD");

        when(currencyRepository.findByType(currency.getType())).thenReturn(Optional.of(currency));

        DuplicateCurrencyException exception = assertThrows(DuplicateCurrencyException.class, () -> {
            currencyService.createCurrency(currency);
        });

        assertEquals("Currency with type USD already exists", exception.getMessage());

        verify(currencyRepository, never()).save(any(Currency.class));
    }

    @Test
    @DisplayName("Should throw RunTimeException when currency ID is not found")
    void getCurrencyByIdNotFound(){
        when(currencyRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            currencyService.getCurrencyById(1L);
        });

        assertEquals("Currency not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when currency ID is null")
    void getCurrencyByIdNullId(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            currencyService.getCurrencyById(null);
        });

        assertEquals("Currency ID must not be null", exception.getMessage());
    }

}