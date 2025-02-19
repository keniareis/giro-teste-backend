package com.keniareis.backend_giro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "Investor Management API - Teste Giro",
        version = "1.0",
        description = "API para gerenciamento de investidores e investimentos.",
        contact = @Contact(
            name = "Repositorio",
            url = "https://github.com/keniareis/giro-teste-backend/tree/main/backend-giro"
        )
    )
)
@SpringBootApplication
public class BackendGiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendGiroApplication.class, args);
	}

}
