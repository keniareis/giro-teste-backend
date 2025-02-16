package com.keniareis.backend_giro.services;

import com.keniareis.backend_giro.models.Investor;
import com.keniareis.backend_giro.repository.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvestorService {

    @Autowired
    private InvestorRepository investorRepository;

    public Investor createInvestor(Investor investor){
        Optional<Investor> existingInvestor = investorRepository.findByEmail(investor.getEmail());

        if (existingInvestor.isPresent()){
            throw new RuntimeException("Investor with this email already exists");
        }
        return investorRepository.save(investor);
    }
}
