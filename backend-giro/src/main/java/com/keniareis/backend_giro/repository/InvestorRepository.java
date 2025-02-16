package com.keniareis.backend_giro.repository;

import com.keniareis.backend_giro.models.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorRepository extends JpaRepository<Investor, Long> {
}
