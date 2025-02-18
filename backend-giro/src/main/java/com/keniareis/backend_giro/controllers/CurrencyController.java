package com.keniareis.backend_giro.controllers;

import com.keniareis.backend_giro.models.Currency;
import com.keniareis.backend_giro.services.CurrencyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @Operation(description = "Create a new currency")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Create a new currency successfully"),
        @ApiResponse(responseCode = "409", description = "Duplicate currency name or type"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    public Currency createCurrency(@Valid @RequestBody Currency currency){
        return currencyService.createCurrency(currency);
    }

    @Operation(description = "get all currencies")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get all currencies successfully"),
        @ApiResponse(responseCode = "404", description = "No currency found")
    })
    @GetMapping
    public List<Currency> getAllCurrencies(){
        return currencyService.getAllCurrencies();
    }
}
