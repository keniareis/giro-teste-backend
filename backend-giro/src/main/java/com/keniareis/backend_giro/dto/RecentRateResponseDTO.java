package com.keniareis.backend_giro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RecentRateResponseDTO {
    private Long id;
    private LocalDate date;

    @NotNull(message = "daily_variation is required")
    @JsonProperty("daily_variation")
    private Double dailyVariation;

    @NotNull(message = "daily_rate is required")
    @JsonProperty("daily_rate")
    private Double dailyRate;

    @NotNull(message = "currency_name is required")
    @JsonProperty("currency_name")
    private String currencyName;

    @NotNull(message = "currency_type is required")
    @JsonProperty("currency_type")
    private String currencyType;

}
