package com.project.testApp.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static com.project.testApp.security.SecurityText.*;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${test.app.secret}")
    private String APP_SECRET;

    @Value("${test.expires.in}")
    private long EXPIRES_IN;
    public long user_id;

    public String generateJwtTokenByUserId(Long userId) {
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
        log.info(JWT_TOKEN_GENERATION_LOG, userId);
        return Jwts.builder()
                .setSubject(Long.toString(userId))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_SECRET.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public Long getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(APP_SECRET.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
        user_id = Long.parseLong(claims.getSubject());
        log.info(JWT_TOKEN_USER_ID_LOG, user_id);
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser()
                    .setSigningKey(APP_SECRET.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(authToken);
            log.info(JWT_TOKEN_VALIDATION_LOG, authToken);
            return true;
        } catch (Exception ex) {
            log.error(JWT_TOKEN_VALIDATION_ERROR_LOG, ex.getMessage());
        }
        return false;
    }
}
