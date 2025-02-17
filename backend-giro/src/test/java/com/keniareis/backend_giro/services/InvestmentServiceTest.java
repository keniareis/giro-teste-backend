package com.keniareis.backend_giro.services;

import com.keniareis.backend_giro.dto.InvestmentDTO;
import com.keniareis.backend_giro.models.Currency;
import com.keniareis.backend_giro.models.Investment;
import com.keniareis.backend_giro.models.Investor;
import com.keniareis.backend_giro.repository.CurrencyRepository;
import com.keniareis.backend_giro.repository.InvestmentRepository;
import com.keniareis.backend_giro.repository.InvestorRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InvestmentServiceTest {
    @Mock
    InvestmentRepository investmentRepository;

    @Mock
    InvestorRepository investorRepository;

    @Mock
    CurrencyRepository currencyRepository;

    @InjectMocks
    private InvestmentService investmentService;

    private InvestmentDTO investmentDTO;
    private Currency currency;
    private Investor investor;
    private Investment expectedInvestment;

    @BeforeEach
    void setUp(){
        investmentDTO = new InvestmentDTO();
        investmentDTO.setId(1L);
        investmentDTO.setInitialAmount(10000.0);
        investmentDTO.setMonths(12);
        investmentDTO.setInterestRate(5.5);
        investmentDTO.setFinalAmount(10550.0);
        investmentDTO.setCurrencyId(1L);
        investmentDTO.setInvestorId(1L);

        currency = new Currency();
        currency.setId(1L);
        currency.setName("USD");

        investor = new Investor();
        investor.setId(1L);
        investor.setName("John Doe");

        expectedInvestment = new Investment();
        expectedInvestment.setInitialAmount(1000.0);
        expectedInvestment.setMonths(12);
        expectedInvestment.setInterestRate(5.0);
        expectedInvestment.setFinalAmount(1050.0);
        expectedInvestment.setCurrency(currency);
        expectedInvestment.setInvestor(investor);
    }

    @Test
    @DisplayName("Should create Investment successfully when everything is ok")
    void createInvestmentSucess(){
        when(currencyRepository.findById(1L)).thenReturn(Optional.of(currency));
        when(investorRepository.findById(1L)).thenReturn(Optional.of(investor));
        when(investmentRepository.save(any(Investment.class))).thenReturn(expectedInvestment);

        Investment createdInvestment = investmentService.createInvestment(investmentDTO);

        assertEquals(expectedInvestment.getInitialAmount(), createdInvestment.getInitialAmount());
        assertEquals(expectedInvestment.getMonths(), createdInvestment.getMonths());
        assertEquals(expectedInvestment.getInterestRate(), createdInvestment.getInterestRate());
        assertEquals(expectedInvestment.getFinalAmount(), createdInvestment.getFinalAmount());
        assertEquals(expectedInvestment.getCurrency(), createdInvestment.getCurrency());
        assertEquals(expectedInvestment.getInvestor(), createdInvestment.getInvestor());

        verify(currencyRepository, times(1)).findById(1L);
        verify(investorRepository, times(1)).findById(1L);
        verify(investmentRepository, times(1)).save(any(Investment.class));
    }

    
    @Test
    @DisplayName("Should throw exception when currency ID is not found")
    void createInvestmentCurrencyNotFound(){
        when(currencyRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            investmentService.createInvestment(investmentDTO);
        });
        
        assertEquals("Currency not found with ID: 1", exception.getMessage());

        verify(currencyRepository, times(1)).findById(1L);

        verify(investmentRepository, never()).save(any(Investment.class));
    }

    @Test
    @DisplayName("Should throw exception when investor ID is not found")
    void createInvestmentInvestorNotFound(){
        when(currencyRepository.findById(1L)).thenReturn(Optional.of(currency));
        when(investorRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            investmentService.createInvestment(investmentDTO);
        });
        
        assertEquals("Investor not found with ID: 1", exception.getMessage());

        verify(currencyRepository, times(1)).findById(1L);
        verify(investorRepository, times(1)).findById(1L);

        verify(investmentRepository, never()).save(any(Investment.class));
    }
}
