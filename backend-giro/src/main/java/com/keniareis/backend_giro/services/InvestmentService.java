package com.keniareis.backend_giro.services;

import com.keniareis.backend_giro.dto.InvestmentDTO;
import com.keniareis.backend_giro.models.Currency;
import com.keniareis.backend_giro.models.Investment;
import com.keniareis.backend_giro.models.Investor;
import com.keniareis.backend_giro.repository.CurrencyRepository;
import com.keniareis.backend_giro.repository.InvestmentRepository;
import com.keniareis.backend_giro.repository.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestmentService {
    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private InvestorRepository investorRepository;

    public Investment createInvestment(InvestmentDTO investmentDTO){

        Investment investment = new Investment();
        investment.setInitialAmount(investmentDTO.getInitialAmount());
        investment.setMonths(investmentDTO.getMonths());
        investment.setInterestRate(investmentDTO.getInterestRate());
        investment.setFinalAmount(investmentDTO.getFinalAmount());

        Currency currency = currencyRepository.findById(investmentDTO.getCurrencyId())
                .orElseThrow(() -> new RuntimeException("Currency not found with ID: " + investmentDTO.getCurrencyId()));
        investment.setCurrency(currency);

        Investor investor = investorRepository.findById(investmentDTO.getInvestorId())
                .orElseThrow(() -> new RuntimeException("Investor not found with ID: " + investmentDTO.getInvestorId()));
        investment.setInvestor(investor);

        return investmentRepository.save(investment);
    }
}
