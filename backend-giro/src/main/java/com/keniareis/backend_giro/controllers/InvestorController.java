package com.keniareis.backend_giro.controllers;

import com.keniareis.backend_giro.models.Investor;
import com.keniareis.backend_giro.services.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/investors")
public class InvestorController {

    @Autowired
    private InvestorService investorService;

    @PostMapping
    public Investor createInvestor(@RequestBody Investor investor){
        return investorService.createInvestor(investor);
    }
}
