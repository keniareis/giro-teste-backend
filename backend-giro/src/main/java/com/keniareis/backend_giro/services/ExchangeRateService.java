package com.keniareis.backend_giro.services;

import com.keniareis.backend_giro.dto.ExchangeRateDTO;
import com.keniareis.backend_giro.dto.RecentRateResponseDTO;
import com.keniareis.backend_giro.models.ExchangeRate;
import com.keniareis.backend_giro.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private CurrencyService currencyService;

    public ExchangeRate createExchangeRate(ExchangeRate exchangeRate){
        return exchangeRateRepository.save(exchangeRate);
    }

    public List<RecentRateResponseDTO> getRecentRates(){
        LocalDate date = LocalDate.now().minusDays(7);
        List<ExchangeRate> recentRates = exchangeRateRepository.findRecentRates(date);

        return recentRates.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private RecentRateResponseDTO mapToDTO(ExchangeRate exchangeRate){
        RecentRateResponseDTO dto = new RecentRateResponseDTO();

        dto.setId(exchangeRate.getId());
        dto.setDate(exchangeRate.getDate());
        dto.setDailyVariation(exchangeRate.getDailyVariation());
        dto.setDailyRate(exchangeRate.getDailyRate());
        dto.setCurrencyName(exchangeRate.getCurrency().getName());
        dto.setCurrencyType(exchangeRate.getCurrency().getType());

        return dto;
    }

    public ExchangeRate updateExchangeRate(Long id, ExchangeRateDTO updateDTO){
        ExchangeRate exchangeRate = exchangeRateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exchange rate not found with ID: \" + id"));

        exchangeRate.setDailyVariation(updateDTO.getDailyVariation());
        exchangeRate.setDailyRate(updateDTO.getDailyRate());
        exchangeRate.setCurrency(currencyService.getCurrencyById(updateDTO.getCurrencyId()));

        return exchangeRateRepository.save(exchangeRate);
    }
}
