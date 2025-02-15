package com.keniareis.backend_giro.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RecentRateResponseDTO {
    private Long id;
    private LocalDate date;
    private Double dailyVariation;
    private Double dailyRate;
    private String currencyName;
    private String currencyType;

}
