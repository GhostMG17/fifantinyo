package com.example.fifantinyo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Fifa Tracker API",
                version = "1.0",
                description = "API documentation for FIFA tracker"
        ),
        servers = @Server(url = "/", description = "Default Server")
)
public class OpenApiConfig {
}
