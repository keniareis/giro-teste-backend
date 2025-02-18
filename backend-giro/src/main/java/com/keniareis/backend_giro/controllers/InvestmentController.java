package com.keniareis.backend_giro.controllers;

import com.keniareis.backend_giro.dto.InvestmentDTO;
import com.keniareis.backend_giro.models.Investment;
import com.keniareis.backend_giro.services.InvestmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/investments")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;
    

    @PostMapping
    public Investment createInvestment(@Valid @RequestBody InvestmentDTO investmentDTO){
        return investmentService.createInvestment(investmentDTO);
    }
}
