package com.keniareis.backend_giro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @NotNull(message = "daily_variation is required")
    @JsonProperty("daily_variation")
    private Double dailyVariation;

    @NotNull(message = "daily_rate is required")
    @JsonProperty("daily_rate")
    private Double dailyRate;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    @JsonIgnore
    @NotNull(message = "currency_id is required")
    private Currency currency;

    @JsonProperty("currency_id")
    public Long getCurrencyId(){
        return currency != null ? currency.getId() : null;
    }
}
