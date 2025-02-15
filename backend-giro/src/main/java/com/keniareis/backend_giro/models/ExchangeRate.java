package com.keniareis.backend_giro.models;

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
    private Double dailyVariation;
    private Double dailyRate;

    @ManyToOne
    private Currency currency;
}
