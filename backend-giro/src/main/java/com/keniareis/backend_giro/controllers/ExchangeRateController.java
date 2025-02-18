package com.keniareis.backend_giro.controllers;

import com.keniareis.backend_giro.dto.ExchangeRateDTO;
import com.keniareis.backend_giro.dto.ExchangeRateUpdateDTO;
import com.keniareis.backend_giro.dto.RecentRateResponseDTO;
import com.keniareis.backend_giro.models.ExchangeRate;
import com.keniareis.backend_giro.services.CurrencyService;
import com.keniareis.backend_giro.services.ExchangeRateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.models.media.MediaType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/exchange-rates")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private CurrencyService currencyService;


    @Operation(description = "Create a new exchange rate")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Create a new exchange rate successfully"),
        @ApiResponse(responseCode = "400", description = "Currency_id must not be null"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<ExchangeRate> createExchangeRate(@Valid @RequestBody ExchangeRateDTO exchangeRateDTO){
        if (exchangeRateDTO.getCurrencyId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "currency_id must not be null");
        }

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setDate(exchangeRateDTO.getDate());
        exchangeRate.setDailyVariation(exchangeRateDTO.getDailyVariation());
        exchangeRate.setDailyRate(exchangeRateDTO.getDailyRate());

        exchangeRate.setCurrency(currencyService.getCurrencyById(exchangeRateDTO.getCurrencyId()));

        return new ResponseEntity<>(exchangeRateService.createExchangeRate(exchangeRate), HttpStatus.CREATED);
    }


    @Operation(description = "get recents exchange rates")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get all recents exchange rates successfully"),
        @ApiResponse(responseCode = "404", description = "No recents exchange rates found")
    })
    @GetMapping("/recent")
    public List<RecentRateResponseDTO> getRecentRates(){
        return exchangeRateService.getRecentRates();
    }

    @Operation(description = "get all exchange rates")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get all exchange rates successfully"),
        @ApiResponse(responseCode = "404", description = "No exchange rates found")
    })
    @GetMapping
    public List<ExchangeRate> getAllExchangeRates(){
        return exchangeRateService.getAllExchangeRates();
    }


    @Operation(description = "update exchange rate")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "update exchange rate successfully"),
        @ApiResponse(responseCode = "404", description = "No exchange rates found")
    })
    @PutMapping("/{id}")
    public ExchangeRate updateExchangeRate(@PathVariable Long id, @Valid @RequestBody ExchangeRateUpdateDTO updateDTO){
        return exchangeRateService.updateExchangeRate(id, updateDTO);
    }


    @Operation(description = "delete old exchange rates")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Delete all old exchange rates successfully", 
            content = @Content(schema = @Schema(type = "string", example = "Old exchange rates deleted successfully"))),
        @ApiResponse(responseCode = "404", description = "No old exchange rates found")
    })
    @DeleteMapping("/old")
    public String deleteOldRates(){
        exchangeRateService.deleteOldRates();
        return "Old exchange rates deleted successfully";
    }
}
