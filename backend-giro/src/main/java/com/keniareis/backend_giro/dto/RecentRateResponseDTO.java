package com.keniareis.backend_giro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RecentRateResponseDTO {
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

    @Schema(description = "Currency name for exchange rate", example = "Dólar Americano")
    @NotNull(message = "currency_name is required")
    @JsonProperty("currency_name")
    private String currencyName;

    @Schema(description = "Currency type for exchange rate", example = "USD")
    @NotNull(message = "currency_type is required")
    @JsonProperty("currency_type")
    private String currencyType;

}
