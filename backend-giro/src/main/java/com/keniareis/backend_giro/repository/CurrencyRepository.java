package com.keniareis.backend_giro.repository;

import com.keniareis.backend_giro.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findByType(String type);
    Optional<Currency> findByName(String name);
}
