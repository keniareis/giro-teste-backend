package com.keniareis.backend_giro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ExchangeRateUpdateDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "generated automatically")
    private Long id;

    @Schema(description = "Exchange rate daily variation", example = "0.5")
    @NotNull(message = "daily_variation is required")
    @JsonProperty("daily_variation")
    private Double dailyVariation;

    @Schema(description = "Exchange rate daily rate", example = "5.25")
    @NotNull(message = "daily_rate is required")
    @JsonProperty("daily_rate")
    private Double dailyRate;

    @Schema(description = "Currency id for exchange rate", example = "1")
    @NotNull(message = "currency_id is required")
    @Positive(message = "currency_id must be positive")
    @JsonProperty("currency_id")
    private Long currencyId;
}
   