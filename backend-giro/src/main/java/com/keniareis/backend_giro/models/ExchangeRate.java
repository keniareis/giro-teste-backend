package com.keniareis.backend_giro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "exchange rate ID", example = "1")
    private Long id;

    @Schema(description = "Exchange rate date", example = " 2025-02-01")
    private LocalDate date;

    @Schema(description = "Exchange rate daily variation", example = "0.5")
    @NotNull(message = "daily_variation is required")
    @JsonProperty("daily_variation")
    private Double dailyVariation;

    @Schema(description = "Exchange rate daily rate", example = "5.25")
    @NotNull(message = "daily_rate is required")
    @JsonProperty("daily_rate")
    private Double dailyRate;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    @JsonIgnore
    @NotNull(message = "currency_id is required")
    private Currency currency;
    
    @Schema(description = "Currency id for exchange rate", example = "1")
    @JsonProperty("currency_id")
    public Long getCurrencyId(){
        return currency != null ? currency.getId() : null;
    }
}
