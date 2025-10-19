package com.example.desafio_sistema_cadastro.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Api - Sistema de cadastro")
                        .version("1.0")
                        .description("Documentação da Api de usuario Spring Boot."));
    }
}
