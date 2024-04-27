package com.travelpickup.secutiry.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelpickup.secutiry.enums.JWTErrorType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        JWTErrorType jwtErrorType = (JWTErrorType) request.getAttribute("exception");

        if (JWTErrorType.JWT_TOKEN_IS_INVALID.equals(jwtErrorType)) {
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("message", jwtErrorType.getMessage());
            responseData.put("status", HttpStatus.UNAUTHORIZED);

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(responseData));
        }

    }

}
