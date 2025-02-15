package com.keniareis.backend_giro.repository;

import com.keniareis.backend_giro.models.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    @Query("SELECT er FROM ExchangeRate er WHERE er.date >= :date")
    List<ExchangeRate> findRecentRates(LocalDate date);
}
