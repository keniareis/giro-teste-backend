package com.keniareis.backend_giro.services;

import com.keniareis.backend_giro.dto.ExchangeRateUpdateDTO;
import com.keniareis.backend_giro.dto.RecentRateResponseDTO;
import com.keniareis.backend_giro.models.ExchangeRate;
import com.keniareis.backend_giro.repository.ExchangeRateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

        if (recentRates.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No recent exchange rates found");
        }

        return recentRates.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ExchangeRate> getAllExchangeRates(){
        List<ExchangeRate> rates = exchangeRateRepository.findAll();

        if (rates.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No exchange rates found");
        }
        return exchangeRateRepository.findAll();
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

    public ExchangeRate updateExchangeRate(Long id, ExchangeRateUpdateDTO updateDTO){
        ExchangeRate exchangeRate = exchangeRateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exchange rate not found with ID: " + id));

        if (updateDTO.getId() != null && !updateDTO.getId().equals(id)){
            throw new RuntimeException("ID cannot be updated");
        }

        if (updateDTO.getDailyVariation() != null){
            exchangeRate.setDailyVariation(updateDTO.getDailyVariation());
        }
        if (updateDTO.getDailyRate() != null) {
            exchangeRate.setDailyRate(updateDTO.getDailyRate());
        }
        if (updateDTO.getCurrencyId() != null) {
            exchangeRate.setCurrency(currencyService.getCurrencyById(updateDTO.getCurrencyId()));
        }

        return exchangeRateRepository.save(exchangeRate);
    }

    public void deleteOldRates(){
        LocalDate cutoffDate = LocalDate.now().minusDays(30);

        long count = exchangeRateRepository.countByDateBefore(cutoffDate);
        if (count == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No old exchange rates found to delete");
        }

        exchangeRateRepository.deleteOldRates(cutoffDate);
    }
}
