package com.keniareis.backend_giro.controllers;

import com.keniareis.backend_giro.models.Investor;
import com.keniareis.backend_giro.services.InvestorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investors")
public class InvestorController {

    @Autowired
    private InvestorService investorService;

    @PostMapping
    public Investor createInvestor(@Valid @RequestBody Investor investor){
        return investorService.createInvestor(investor);
    }

    @DeleteMapping("/{id}")
    public String deleteInvestor(@PathVariable Long id){
        investorService.deleteInvestor(id);
        return "Investor and associated investments deleted successfully";
    }

    @GetMapping
    public List<Investor> getAllInvestors(){
        return investorService.getAllInvestors();
    }
}
