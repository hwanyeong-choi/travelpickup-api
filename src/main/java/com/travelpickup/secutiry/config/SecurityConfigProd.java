package com.travelpickup.secutiry.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelpickup.common.filter.RequestInforFilter;
import com.travelpickup.secutiry.filter.JWTFilter;
import com.travelpickup.secutiry.handler.JwtAuthenticationEntryPoint;
import com.travelpickup.secutiry.handler.TravelPickupAccessDeniedHandler;
import com.travelpickup.secutiry.util.JWTUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

@Profile("prod")
@Configuration
@EnableWebSecurity
public class SecurityConfigProd {

    private final AuthenticationConfiguration authenticationConfiguration;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JWTUtil jwtUtil;

    public SecurityConfigProd(AuthenticationConfiguration authenticationConfiguration,
                              JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                              JWTUtil jwtUtil) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new TravelPickupAccessDeniedHandler(new ObjectMapper());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(request -> {

                    CorsConfiguration configuration = new CorsConfiguration();

                    configuration.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
                    configuration.setAllowedMethods(Collections.singletonList("*"));
                    configuration.setAllowCredentials(Boolean.TRUE);
                    configuration.setAllowedHeaders(Collections.singletonList("*"));
                    configuration.setMaxAge(3600L);
                    configuration.setExposedHeaders(Collections.singletonList(HttpHeaders.AUTHORIZATION));

                    return configuration;
                }));

        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .exceptionHandling(exceptions -> {
                    exceptions.accessDeniedHandler(accessDeniedHandler());
                    exceptions.authenticationEntryPoint(jwtAuthenticationEntryPoint);})
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/login/**").permitAll()
                        .requestMatchers("/api/v1/health/**").permitAll()
                        .requestMatchers("/api/v1/manager/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated());

        http
                .addFilterAt(new RequestInforFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

}
