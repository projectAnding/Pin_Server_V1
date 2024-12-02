package com.server.pin.global.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo())
                .servers(List.of(
                        new Server()
                                .url("http://43.202.103.139:8080")
                                .description("Production Server"),
                        new Server()
                                .url("http://localhost:8080")
                                .description("Development Server")
                ))
                .addSecurityItem(new SecurityRequirement().addList("Authorization"))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "Authorization", new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("Bearer")
                                                .bearerFormat("Authorization")
                                                .in(SecurityScheme.In.HEADER)
                                                .name("Authorization")
                                )
                );
    }

    private Info apiInfo() {
        return new Info()
                .title("Pin_Server API")
                .description("Pin_Server V1")
                .version("1.0.0");
    }
}