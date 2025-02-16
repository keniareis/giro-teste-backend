package com.keniareis.backend_giro.repository;

import com.keniareis.backend_giro.models.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvestorRepository extends JpaRepository<Investor, Long> {
    Optional<Investor> findByEmail(String email);
}
