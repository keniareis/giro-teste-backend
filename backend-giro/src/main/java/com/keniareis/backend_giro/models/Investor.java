package com.keniareis.backend_giro.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Data
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "investor name is required")
    private String name;

    @NotBlank(message = "investor email is required")
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Investment> investments;
}
