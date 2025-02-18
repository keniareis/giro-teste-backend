package com.keniareis.backend_giro.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Data
public class Investor {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "generated automatically")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Name of the investor", example = "João Silva")
    @NotBlank(message = "investor name is required")
    private String name;

    @Schema(description = "Email of the investor", example = "joao@email.com")
    @NotBlank(message = "investor email is required")
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "List of investments associated with the investor", example = "[{...}]")
    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Investment> investments;
}
