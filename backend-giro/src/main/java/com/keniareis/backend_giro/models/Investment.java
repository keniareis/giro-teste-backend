package com.keniareis.backend_giro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("initial_amount")
    private Double initialAmount;
    private Integer months;

    @JsonProperty("interest_rate")
    private Double interestRate;

    @JsonProperty("final_amount")
    private Double finalAmount;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    @JsonIgnore
    private Currency currency;

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
