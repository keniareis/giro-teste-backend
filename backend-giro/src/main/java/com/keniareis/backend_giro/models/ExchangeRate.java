package com.keniareis.backend_giro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @JsonProperty("daily_variation")
    private Double dailyVariation;

    @JsonProperty("daily_rate")
    private Double dailyRate;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    @JsonIgnore
    private Currency currency;

    @JsonProperty("currency_id")
    public Long getCurrencyId(){
        return currency != null ? currency.getId() : null;
    }
}
