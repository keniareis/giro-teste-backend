package com.keniareis.backend_giro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class InvestmentDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "generated automatically")
    private Long id;

    @Schema(description = "investment inicial amount", example = "10000")
    @NotNull(message = "initial_amount is required")
    @Positive(message = "initial_amount must be positive")
    @JsonProperty("initial_amount")
    private Double initialAmount;

    @Schema(description = "investment month", example = "12")
    @NotNull(message = "months is required")
    @Min(value = 1, message = "months must be at least 1")
    private Integer months;

    @Schema(description = "investment daily rate", example = "5.5")
    @NotNull(message = "interest_rate is required")
    @PositiveOrZero(message = "interest_rate must be positive or zero")
    @JsonProperty("interest_rate")
    private Double interestRate;

    @Schema(description = "investment final amount ", example = "10550")
    @NotNull(message = "final_amount is required")
    @Positive(message = "final_amount must be positive")
    @JsonProperty("final_amount")
    private Double finalAmount;

    @Schema(description = "currency ID for investment", example = "1")
    @NotNull(message = "currency_id is required")
    @Positive(message = "currency_id must be positive")
    @JsonProperty("currency_id")
    private Long currencyId;
    
    @Schema(description = "investor ID for investment", example = "1")
    @NotNull(message = "investor_id is required")
    @Positive(message = "investor_id must be positive")
    @JsonProperty("investor_id")
    private Long investorId;
}
