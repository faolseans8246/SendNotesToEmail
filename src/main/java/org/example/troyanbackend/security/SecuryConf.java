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
                // CORS konfiguratsiyasini qo'shish
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                    corsConfig.addAllowedOrigin("https://send-notes-to-email-front.vercel.app"); // Frontend manzili
                    corsConfig.addAllowedMethod("GET");
                    corsConfig.addAllowedMethod("POST");
                    corsConfig.addAllowedMethod("PUT");
                    corsConfig.addAllowedMethod("DELETE");
                    corsConfig.addAllowedMethod("OPTIONS");
                    corsConfig.addAllowedHeader("Content-Type");
                    corsConfig.setAllowCredentials(true);
                    return corsConfig;
                }))
                .csrf(AbstractHttpConfigurer::disable) // CSRF himoyasini o'chirish
                .authorizeHttpRequests(auth -> {
                    // umumiy ruxsat berilgan yo'llar
                    auth.requestMatchers(
                            "/**",
                            "/api/**",
                            "/api/message/**",
                            "/api/message/send"
                    ).permitAll();

                    // Swagger API uchun ruxsatlar
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

                    // Boshqa barcha so'rovlar faqat autentifikatsiya qilingan foydalanuvchilar uchun
                    auth.anyRequest().authenticated();
                });

        return http.build();
    }
}
