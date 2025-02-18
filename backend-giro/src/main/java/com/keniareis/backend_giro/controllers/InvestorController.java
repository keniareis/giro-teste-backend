package com.keniareis.backend_giro.controllers;

import com.keniareis.backend_giro.dto.InvestorCreateDTO;
import com.keniareis.backend_giro.models.Investor;
import com.keniareis.backend_giro.services.InvestorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investors")
public class InvestorController {

    @Autowired
    private InvestorService investorService;

    @Operation(
        summary = "Create a new investor",
        description = "Creates a new investor with the provided name and email. If an investor with the same email already exists, a 400 error is returned."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Investor created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Investor.class))),
        @ApiResponse(responseCode = "400", description = "Investor with this email already exists")
    })
    @PostMapping
    public ResponseEntity<Investor> createInvestor(@Valid @RequestBody InvestorCreateDTO investorDTO){
        Investor investor = new Investor();
        investor.setName(investorDTO.getName());
        investor.setEmail(investorDTO.getEmail());

        Investor createdInvestor = investorService.createInvestor(investor);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInvestor);
    }

    @Operation(
        summary = "Delete investor",
        description = "delete investor by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Delete investor successfully", 
            content = @Content(schema = @Schema(type = "string", example = "investor deleted successfully"))),
        @ApiResponse(responseCode = "404", description = "No investor with this ID found")
    })
    @DeleteMapping("/{id}")
    public String deleteInvestor(@PathVariable Long id){
        investorService.deleteInvestor(id);
        return "Investor and associated investments deleted successfully";
    }

    @Operation(
        summary = "Get all investors",
        description = "get all investors investors")
    @GetMapping
    public List<Investor> getAllInvestors(){
        return investorService.getAllInvestors();
    }
}
