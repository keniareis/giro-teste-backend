package com.keniareis.backend_giro.repository;

import com.keniareis.backend_giro.models.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}
