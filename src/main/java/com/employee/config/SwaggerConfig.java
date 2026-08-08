package com.employee.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Employee CRUD REST API",
                version = "1.0",
                description = "Spring Boot Employee CRUD Application with JWT Authentication, MySQL, Docker and Swagger",
                contact = @Contact(
                        name = "Pariceema Macwan",
                        email = "pariceema@example.com"
                ),
                license = @License(
                        name = "Apache 2.0"
                )
        )
)

@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)

public class SwaggerConfig {

}