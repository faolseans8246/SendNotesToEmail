package org.example.troyanbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecuryConf {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
//                    corsConfig.addAllowedOrigin("https://birthday-frontend-d8395a6906eb.herokuapp.com"); // Frontend manzili
                    corsConfig.addAllowedOrigin("https://send-notes-to-email-front.vercel.app"); // Frontend manzili
                    corsConfig.addAllowedMethod("*"); // Barcha metodlarga ruxsat berish
                    corsConfig.addAllowedHeader("*"); // Barcha headerlarga ruxsat berish
                    corsConfig.setAllowCredentials(true); // Credentiallarni ruxsat berish
                    return corsConfig;
                }))
                .csrf(AbstractHttpConfigurer::disable) // CSRF himoyasini o'chirish
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/**", "/api/**", "/api/message/**").permitAll();
                    auth.anyRequest().authenticated(); // Boshqa barcha so'rovlar autentifikatsiya talab qiladi
                });

        return http.build();
    }
}
