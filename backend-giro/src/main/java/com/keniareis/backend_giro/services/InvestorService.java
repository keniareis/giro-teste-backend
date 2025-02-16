package com.keniareis.backend_giro.services;

import com.keniareis.backend_giro.models.Investor;
import com.keniareis.backend_giro.repository.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestorService {

    @Autowired
    private InvestorRepository investorRepository;

    public Investor createInvestor(Investor investor){
        return investorRepository.save(investor);
    }
}
