package com.keniareis.backend_giro.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.keniareis.backend_giro.dto.ExchangeRateUpdateDTO;
import com.keniareis.backend_giro.dto.RecentRateResponseDTO;
import com.keniareis.backend_giro.models.Currency;
import com.keniareis.backend_giro.models.ExchangeRate;
import com.keniareis.backend_giro.repository.ExchangeRateRepository;


@ExtendWith(MockitoExtension.class)
public class ExchangeRateServiceTest {
    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @Mock
    private CurrencyService currencyService;

    @InjectMocks
    private ExchangeRateService exchangeRateService;

    private ExchangeRate exchangeRate;
    private Currency currency;

    @BeforeEach
    void setUp() {
        currency = new Currency();
        currency.setId(1L);
        currency.setName("Dólar Americano");
        currency.setType("USD");

        exchangeRate = new ExchangeRate();
        exchangeRate.setId(1L);
        exchangeRate.setDate(LocalDate.now());
        exchangeRate.setDailyRate(5.0);
        exchangeRate.setDailyVariation(0.1);
        exchangeRate.setCurrency(currency);
    }

    @Test
    @DisplayName("Should create exchange rate successfully when everything is ok")
    void createExchangeRateTest(){
        when(exchangeRateRepository.save(any(ExchangeRate.class))).thenReturn(exchangeRate);

        ExchangeRate createdExchangeRate = exchangeRateService.createExchangeRate(exchangeRate);

        assertNotNull(createdExchangeRate);
        assertEquals(exchangeRate.getId(), createdExchangeRate.getId());
        assertEquals(exchangeRate.getDate(), createdExchangeRate.getDate());
        assertEquals(exchangeRate.getDailyRate(), createdExchangeRate.getDailyRate());
        assertEquals(exchangeRate.getDailyVariation(), createdExchangeRate.getDailyVariation());
        assertEquals(exchangeRate.getCurrency(), createdExchangeRate.getCurrency());

        verify(exchangeRateRepository, times(1)).save(any(ExchangeRate.class));
    }

    @Test
    @DisplayName("Sould get exchange rates from the last 7 days successfully")
    void getRecentRatesTest(){
        LocalDate date = LocalDate.now().minusDays(7);
        List<ExchangeRate> recentRates = Arrays.asList(exchangeRate);
        when(exchangeRateRepository.findRecentRates(date)).thenReturn(recentRates);

        List<RecentRateResponseDTO> responseDTO = exchangeRateService.getRecentRates();

        assertNotNull(responseDTO);
        assertEquals(1, responseDTO.size());
        RecentRateResponseDTO dto = responseDTO.get(0);   
        assertEquals(exchangeRate.getId(), dto.getId());
        assertEquals(exchangeRate.getDate(), dto.getDate());
        assertEquals(exchangeRate.getDailyVariation(), dto.getDailyVariation());
        assertEquals(exchangeRate.getDailyRate(), dto.getDailyRate());
        assertEquals("Dólar Americano", dto.getCurrencyName());
        assertEquals("USD", dto.getCurrencyType());

        verify(exchangeRateRepository, times(1)).findRecentRates(date);
    }

    @Test
    @DisplayName("Should update exchange rate successfully")
    void updateExchangeRateSuccessTest(){
        ExchangeRateUpdateDTO updateDTO = new ExchangeRateUpdateDTO();
        updateDTO.setDailyRate(6.0);
        updateDTO.setDailyVariation(0.2);
        updateDTO.setCurrencyId(1L);

        when(exchangeRateRepository.findById(1L)).thenReturn(Optional.of(exchangeRate));
        when(currencyService.getCurrencyById(1L)).thenReturn(currency);
        when(exchangeRateRepository.save(any(ExchangeRate.class))).thenReturn(exchangeRate);

        ExchangeRate updatedExchangeRate = exchangeRateService.updateExchangeRate(1L, updateDTO);

        assertNotNull(updatedExchangeRate);
        assertEquals(updateDTO.getDailyRate(), updatedExchangeRate.getDailyRate());
        assertEquals(updateDTO.getDailyVariation(), updatedExchangeRate.getDailyVariation());
        assertEquals(currency, updatedExchangeRate.getCurrency());

        verify(exchangeRateRepository, times(1)).findById(1L);
        verify(currencyService, times(1)).getCurrencyById(1L);
        verify(exchangeRateRepository, times(1)).save(any(ExchangeRate.class));
    }


    @Test
    @DisplayName("Should throw an exception when exchange rate ID is not found")
    void updateExchangeRateNotFoundTest(){
        Long id = 1L;
        ExchangeRateUpdateDTO updateDTO = new ExchangeRateUpdateDTO();
        when(exchangeRateRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            exchangeRateService.updateExchangeRate(id, updateDTO);
        });

        assertEquals(exception.getStatusCode() , HttpStatus.NOT_FOUND);

        verify(exchangeRateRepository, times(1)).findById(id);
        verify(exchangeRateRepository, never()).save(any(ExchangeRate.class));
    }

    @Test
    @DisplayName("Should delete old rates successfully")
    void deleteOldRatesSuccessTest(){
        LocalDate cutoffDate = LocalDate.now().minusDays(30);
        when(exchangeRateRepository.countByDateBefore(cutoffDate)).thenReturn(1L);

        exchangeRateService.deleteOldRates();

        verify(exchangeRateRepository, times(1)).countByDateBefore(cutoffDate);
        verify(exchangeRateRepository, times(1)).deleteOldRates(cutoffDate);
    }

    @Test
    @DisplayName("Should throw exception when theres no old rates to delete")
    void deleteNoOldRatesTest(){
        LocalDate cutoffDate = LocalDate.now().minusDays(30);
        when(exchangeRateRepository.countByDateBefore(cutoffDate)).thenReturn(0L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->{
            exchangeRateService.deleteOldRates();
        });

        assertEquals("No old exchange rates found to delete", exception.getMessage());

        verify(exchangeRateRepository, times(1)).countByDateBefore(cutoffDate);
        verify(exchangeRateRepository, never()).deleteOldRates(cutoffDate);
    }    
}
