package com.keniareis.backend_giro.services;

import com.keniareis.backend_giro.models.Investment;
import com.keniareis.backend_giro.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestmentService {
    @Autowired
    private InvestmentRepository investmentRepository;

    public Investment createInvestment(Investment investment){
        return investmentRepository.save(investment);
    }
}
