package com.keniareis.backend_giro.repository;

import com.keniareis.backend_giro.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
