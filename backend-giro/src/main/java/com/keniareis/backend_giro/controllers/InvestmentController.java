package com.keniareis.backend_giro.controllers;

import com.keniareis.backend_giro.dto.InvestmentDTO;
import com.keniareis.backend_giro.models.Investment;
import com.keniareis.backend_giro.services.InvestmentService;

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

@RestController
@RequestMapping("/investments")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;
    
    @Operation(
        summary = "Create a new investment",
        description = "Creates a new investment"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Investment created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Investment.class))),
        
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<Investment> createInvestment(@Valid @RequestBody InvestmentDTO investmentDTO){

        return new ResponseEntity<>( investmentService.createInvestment(investmentDTO), HttpStatus.CREATED);
    }
}
