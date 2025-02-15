package com.keniareis.backend_giro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExchangeRateDTO {
    private Long id;
    private LocalDate date;

    @JsonProperty("daily_variation")
    private Double dailyVariation;

    @JsonProperty("daily_rate")
    private Double dailyRate;

    @JsonProperty("currency_id")
    private Long currencyId;
}
