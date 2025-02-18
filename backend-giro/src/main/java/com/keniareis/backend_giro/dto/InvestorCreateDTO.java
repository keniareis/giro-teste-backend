package com.keniareis.backend_giro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InvestorCreateDTO {
    @Schema(description = "Investor name", example = "João Silva")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "Investor email", example = "joao@email.com")
    @NotBlank(message = "Email is required")
    private String email;
}
