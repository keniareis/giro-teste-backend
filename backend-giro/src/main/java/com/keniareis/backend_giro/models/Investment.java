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

    @JsonProperty("inicial_amount")
    private Double inicialAmount;
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
}
