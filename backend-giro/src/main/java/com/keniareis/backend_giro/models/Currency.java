package com.keniareis.backend_giro.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Currency {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "generated automatically")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Currency name", example = "Dólar Americano")
    @NotBlank(message = "currency name must not be blank")
    private String name;
    
    @Schema(description = "Currency type", example = "USD")
    @NotBlank(message = "currency type must not be blank")
    private String type;

}
