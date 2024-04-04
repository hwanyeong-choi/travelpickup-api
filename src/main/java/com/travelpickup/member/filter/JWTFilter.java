package com.travelpickup.member.filter;

import com.travelpickup.member.dto.LoginUser;
import com.travelpickup.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    private final String BEARER = "Bearer ";

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("요청 URI:[{}]", request.getRequestURI());

        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization == null || !authorization.startsWith(BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.split(" ")[1];

        if (jwtUtil.isExpired(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        Long userId = jwtUtil.getUserId(token);
        String role = jwtUtil.getRole(token);

        LoginUser loginUser = LoginUser.of(userId, role);
        Authentication authToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);

    }

}
