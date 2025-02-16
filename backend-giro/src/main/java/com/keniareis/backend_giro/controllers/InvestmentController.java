package com.keniareis.backend_giro.controllers;

import com.keniareis.backend_giro.models.Investment;
import com.keniareis.backend_giro.services.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/investments")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @PostMapping
    public Investment createInvestment(@RequestBody Investment investment){
        return investmentService.createInvestment(investment);
    }
}
