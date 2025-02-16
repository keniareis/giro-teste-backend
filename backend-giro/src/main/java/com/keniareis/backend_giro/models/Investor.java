package com.keniareis.backend_giro.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Investment> investments;
}
