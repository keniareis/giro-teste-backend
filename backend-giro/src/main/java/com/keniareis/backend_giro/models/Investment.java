package com.keniareis.backend_giro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Entity
@Data
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @ManyToOne
    @JoinColumn(name = "currency_id")
    @JsonIgnore
    private Currency currency;

    @NotNull(message = "investor_id is required")
    @ManyToOne
    @JoinColumn(name = "investor_id")
    @JsonIgnore
    private Investor investor;

    @JsonProperty("currency_id")
    public Long getCurrencyId() {
        return currency != null ? currency.getId() : null;
    }

    @JsonProperty("investor_id")
    public Long getInvestorId() {
        return investor != null ? investor.getId() : null;
    }
}
