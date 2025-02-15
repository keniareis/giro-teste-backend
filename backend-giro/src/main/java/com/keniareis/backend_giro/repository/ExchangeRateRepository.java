package com.keniareis.backend_giro.repository;

import com.keniareis.backend_giro.models.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
}
