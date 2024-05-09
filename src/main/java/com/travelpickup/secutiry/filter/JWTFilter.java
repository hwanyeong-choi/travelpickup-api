package com.travelpickup.secutiry.filter;

import com.travelpickup.secutiry.dto.LoginManager;
import com.travelpickup.secutiry.dto.LoginUser;
import com.travelpickup.secutiry.util.JWTUtil;
import com.travelpickup.secutiry.enums.JWTErrorType;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    private final String BEARER = "Bearer ";

    private final String TOKEN_SPLIT = " ";

    private final int TOKEN_INDEX = 1;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization == null || !authorization.startsWith(BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.split(TOKEN_SPLIT)[TOKEN_INDEX];

        try {

            if (jwtUtil.isExpired(token)) {
                filterChain.doFilter(request, response);
                return;
            }

            Authentication authToken = getAuthToken(token);
            SecurityContextHolder.getContext().setAuthentication(authToken);
            filterChain.doFilter(request, response);

        } catch (MalformedJwtException exception) {
            request.setAttribute("exception", JWTErrorType.JWT_TOKEN_IS_INVALID);
            filterChain.doFilter(request, response);
        }

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
