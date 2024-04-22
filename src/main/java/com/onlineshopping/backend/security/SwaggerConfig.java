package com.onlineshopping.backend.security;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition()
public class SwaggerConfig {
    public SwaggerConfig(){}

    @Bean
    public OpenAPI swaggerApiConfig(){
        var info = new Info()
                .title("E-Commerce API")
                .description("user and admin api collections")
                .version("1.0");

        return new OpenAPI().info(info);
    }
}
