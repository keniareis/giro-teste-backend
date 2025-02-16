package com.keniareis.backend_giro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExchangeRateDTO {
    private Long id;

    @NotNull(message = "date is required")
    private LocalDate date;

    @NotNull(message = "daily_variation is required")
    @JsonProperty("daily_variation")
    private Double dailyVariation;

    @NotNull(message = "daily_rate is required")
    @JsonProperty("daily_rate")
    private Double dailyRate;

    @NotNull(message = "currency_id is required")
    @Positive(message = "currency_id must be positive")
    @JsonProperty("currency_id")
    private Long currencyId;
}
