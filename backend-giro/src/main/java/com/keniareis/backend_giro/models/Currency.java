package com.keniareis.backend_giro.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "currency name is required")
    @NotBlank(message = "currency name must not be blank")
    private String name;


    @NotNull(message = "currency type is required")
    @NotBlank(message = "currency type must not be blank")
    private String type;

}
