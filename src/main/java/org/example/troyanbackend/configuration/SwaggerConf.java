package org.example.troyanbackend.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@OpenAPIDefinition(
        info = @Info(

                contact = @Contact(
                        name = "Feruzbek KHamroev",
                        email = "feruzbekhamrayev2002@gmail.com"
                ),
                title = "Troyan app front testing",
                version = "1.0.0",
                description = "API documentation for Troyan Gift Back"
        ),

        security = @SecurityRequirement(
                name = "OAuth2.0"
        )
)
public class SwaggerConf {
}
