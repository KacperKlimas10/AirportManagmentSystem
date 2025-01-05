package org.pl.serwis_logowania.utils;

import io.jsonwebtoken.*;
import org.pl.serwis_logowania.enums.Role;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.*;

@Component
public class JwtUtils {

    private static final SecretKey jwtSecret = getSigningKey();

    private static final long jwtExpirationMs = 300_000;

    public static String generateJwtToken(String login, Role role) {
        return Jwts.builder()
                .header()
                .type("JWT")
                .and()
                .subject(login)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(jwtSecret)
                .compact();
    }

    public static boolean validateJwtToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(jwtSecret)
                    .build()
                    .parse(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("JWT Token is not valid: " + e.getMessage());
        } return false;
    }

    public static Claims getClaimsFromJwtToken(String token) {
        if (validateJwtToken(token)) {
            return (Claims) Jwts.parser()
                    .verifyWith(jwtSecret)
                    .build()
                    .parse(token)
                    .getPayload();
        } return null;
    }

    public static String getUsernameFromJwtToken(String token) {
        return Objects.requireNonNull(getClaimsFromJwtToken(token)).getSubject();
    }

    static SecretKey getSigningKey() {
        return Jwts.SIG.HS512.key().build();
    }
}