package com.keniareis.backend_giro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InvestmentDTO {
    @JsonProperty("initial_amount")
    private Double initialAmount;

    private Integer months;

    @JsonProperty("interest_rate")
    private Double interestRate;

    @JsonProperty("final_amount")
    private Double finalAmount;

    @JsonProperty("currency_id")
    private Long currencyId;

    @JsonProperty("investor_id")
    private Long investorId;
}
