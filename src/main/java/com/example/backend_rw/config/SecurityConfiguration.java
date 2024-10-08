package com.example.backend_rw.config;

import com.example.backend_rw.security.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) throws Exception {
        // Cấu hình cors
        httpSecurity.cors(cors -> {
            cors.configurationSource(request -> {
                CorsConfiguration corsConfig = new CorsConfiguration();
                corsConfig.setAllowCredentials(true);
                corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4173", "http" +
                        "://localhost:5173"));
                corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept", "x-no-retry"));
                return corsConfig;
            });
        });
        httpSecurity.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/api/auth/**").permitAll().anyRequest().permitAll()).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()).authenticationEntryPoint(customAuthenticationEntryPoint)).csrf().disable().formLogin(AbstractHttpConfigurer::disable)

//                .exceptionHandling(exceptions -> exceptions
//                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint()) //401
//                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())) //403
        ;
        return httpSecurity.build();
    }
}
