package com.travelpickup.member.util;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private SecretKey secretKey;

    private JwtParser jwtParser;


    public JWTUtil(@Value("${jwt.secret}") String secret) {
        this.secretKey =  new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.jwtParser = Jwts.parser().verifyWith(secretKey).build();
    }

    public Long getUserId(String token) {
        return jwtParser.parseSignedClaims(token).getPayload().get("userId", Long.class);
    }

    public String getRole(String token) {
        return jwtParser.parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public Long getCenterId(String token) {
        return jwtParser.parseSignedClaims(token).getPayload().get("centerId", Long.class);
    }

    public Boolean isExpired(String token) {
        return jwtParser.parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String createJwt(Long userId, String role, Long expiredMs) {
        return Jwts
                .builder()
                .claim("userId", userId)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

    public String createJwt(Long userId, Long centerId, String role, Long expiredMs) {
        return Jwts
                .builder()
                .claim("userId", userId)
                .claim("centerId", centerId)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

}
