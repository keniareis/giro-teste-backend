package com.keniareis.backend_giro.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.keniareis.backend_giro.models.ExchangeRate;
import com.keniareis.backend_giro.services.ExchangeRateService;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateRepositoryTest {
    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private ExchangeRateService exchangeRateService;

    private ExchangeRate oldRate;
    private ExchangeRate recenteRate;

    @BeforeEach
    void setUp(){
        oldRate = new ExchangeRate();
        oldRate.setDate(LocalDate.of(2025, 1, 1));

        recenteRate = new ExchangeRate();
        recenteRate.setDate(LocalDate.of(2025, 2, 18));
    }

    @Test
    @DisplayName("Should count exchange rates prior to a specific date.")
    void countByDateBeforeTest(){
        LocalDate cutoffDate = LocalDate.of(2025, 1, 15);

        when(exchangeRateRepository.countByDateBefore(cutoffDate)).thenReturn(1L);

        long count = exchangeRateRepository.countByDateBefore(cutoffDate);

        assertEquals(1, count);
        verify(exchangeRateRepository, times(1)).countByDateBefore(cutoffDate);
    }

    @Test
    @DisplayName("Should return only the recent exchange rates from a specific date")
    void findRecentRatesTest(){
        LocalDate startDate = LocalDate.of(2025, 2, 15);

        when(exchangeRateRepository.findRecentRates(startDate)).thenReturn(List.of(recenteRate));

        List<ExchangeRate> recentRates = exchangeRateRepository.findRecentRates(startDate);

        assertEquals(1, recentRates.size());
        assertTrue(recentRates.contains(recenteRate));
        verify(exchangeRateRepository, times(1)).findRecentRates(startDate);
    }

    @Test
    @DisplayName("Should delete the exchange rates older than a specific date")
    void deleteOldRatesTest(){
        LocalDate cutoffDate = LocalDate.of(2025, 2, 15);

        doNothing().when(exchangeRateRepository).deleteOldRates(cutoffDate);
        
        exchangeRateRepository.deleteOldRates(cutoffDate);

        verify(exchangeRateRepository, times(1)).deleteOldRates(cutoffDate);
    }

        
}
