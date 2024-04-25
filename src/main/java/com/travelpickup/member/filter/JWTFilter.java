package com.travelpickup.member.filter;

import com.travelpickup.member.dto.LoginManager;
import com.travelpickup.member.dto.LoginUser;
import com.travelpickup.member.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;
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

        Authentication authToken = getAuthToken(token);
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);

    }

    private Authentication getAuthToken(String token) {
        Long userId = jwtUtil.getUserId(token);
        String role = jwtUtil.getRole(token);
        Long centerId = jwtUtil.getCenterId(token);

        if (ObjectUtils.isEmpty(centerId)) {
            LoginUser loginUser = LoginUser.of(userId, role);
            return new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        }

        LoginManager loginManager = LoginManager.of( userId, role, centerId);
        return new UsernamePasswordAuthenticationToken(loginManager, null, loginManager.getAuthorities());

    }

}
