package com.keniareis.backend_giro.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.keniareis.backend_giro.models.Investor;
import com.keniareis.backend_giro.repository.InvestorRepository;

@ExtendWith(MockitoExtension.class)
public class InvestorServiceTest {
    @Mock
    InvestorRepository investorRepository;

    @InjectMocks
    InvestorService investorService;

    private Investor investor;

    @BeforeEach
    void setUp(){
        investor = new Investor();
        investor.setId(1L);
        investor.setName("João Silva");
        investor.setEmail("joao@email.com");
    }


    @Test
    @DisplayName("Should create investor successfully when everything is ok")
    void createInvestorSuccess(){
        when(investorRepository.findByEmail(investor.getEmail())).thenReturn(Optional.empty());

        when(investorRepository.save(any(Investor.class))).thenReturn(investor);

        Investor createdInvestor = investorService.createInvestor(investor);

        assertNotNull(createdInvestor);
        assertEquals(1L, createdInvestor.getId());
        assertEquals("João Silva", createdInvestor.getName());
        assertEquals("joao@email.com", createdInvestor.getEmail());

        verify(investorRepository, times(1)).findByEmail(investor.getEmail());
        verify(investorRepository, times(1)).save(any(Investor.class));
    }

    @Test
    @DisplayName("Should throw RunTimeException when Investor email already exists")
    void createInvestorDuplicateEmail(){
        when(investorRepository.findByEmail(investor.getEmail())).thenReturn(Optional.of(investor));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            investorService.createInvestor(investor);
        });

        assertEquals("Investor with this email already exists", exception.getMessage());

        verify(investorRepository, times(1)).findByEmail(investor.getEmail());
        verify(investorRepository, never()).save(any(Investor.class));
    }

    @Test
    @DisplayName("Should delete investor successfully")
    void deleteInvestorSuccess(){
        when(investorRepository.findById(1L)).thenReturn(Optional.of(investor));

        doNothing().when(investorRepository).delete(investor);

        investorService.deleteInvestor(1L);

        verify(investorRepository, times(1)).findById(1L);
        verify(investorRepository, times(1)).delete(investor);
    }  

    @Test
    @DisplayName("Should throw exception when investor is not found")
    void deleteInvestorNotFound(){
        when(investorRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            investorService.deleteInvestor(1L);
        });

        assertEquals("Investor not found with ID: 1", exception.getMessage());

        verify(investorRepository, times(1)).findById(1L);
        verify(investorRepository, never()).delete(any(Investor.class));
    }

}
