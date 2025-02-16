package com.keniareis.backend_giro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class InvestmentDTO {

    @NotNull(message = "initial_amount is required")
    @Positive(message = "initial_amount must be positive")
    @JsonProperty("initial_amount")
    private Double initialAmount;

    @NotNull(message = "months is required")
    @Min(value = 1, message = "months must be at least 1")
    private Integer months;

    @NotNull(message = "interest_rate is required")
    @PositiveOrZero(message = "interest_rate must be positive or zero")
    @JsonProperty("interest_rate")
    private Double interestRate;

    @NotNull(message = "final_amount is required")
    @Positive(message = "final_amount must be positive")
    @JsonProperty("final_amount")
    private Double finalAmount;

    @NotNull(message = "currency_id is required")
    @Positive(message = "currency_id must be positive")
    @JsonProperty("currency_id")
    private Long currencyId;

    @NotNull(message = "investor_id is required")
    @Positive(message = "inves_id must be positive")
    @JsonProperty("investor_id")
    private Long investorId;
}
