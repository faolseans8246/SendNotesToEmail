package org.example.troyanbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import java.net.http.HttpHeaders;

@Configuration
@EnableWebSecurity
public class SecuryConf {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
//                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                    auth -> {

                        // main configurations
                        auth.requestMatchers(
                                "/**",
                                "/api/message/**"
                        ).permitAll();

                        // Swagger configurations
                        auth.requestMatchers(
                                "/api/auth/**",
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html/**",
                                "/swagger-ui/index.html"

                        ).permitAll();

                        // Other actions
                        auth.anyRequest().authenticated();
                    }
                );

        return http.build();

    }
}