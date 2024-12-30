package org.pl.serwis_logowania.utils;

import io.jsonwebtoken.*;
import org.pl.serwis_logowania.enums.Role;
import org.springframework.stereotype.Component;
import java.util.Base64;
import java.util.*;

@Component
public class JwtUtils {

    private static final String jwtSecret = "ThisIsASecureSecretKeyForHS512ThatIsAtLeast64CharactersLongAndMore";

    private static final String encodedKey = Base64.getEncoder().encodeToString(jwtSecret.getBytes());

    private static final long jwtExpirationMs = 300_000;

    public static String generateJwtToken(String login, Role roles) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", roles.toString());
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .subject(login)
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .issuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, encodedKey)
                .compact();
    }

    public static boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(encodedKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("JWT Token is not valid: " + e.getMessage());
        } return false;
    }

    public static Claims getClaimsFromJwtToken(String token) {
        if (validateJwtToken(token)) {
            return Jwts.parser()
                    .setSigningKey(encodedKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } return null;
    }

    public static Role getRolesFromToken(String token) {
        Claims claims = getClaimsFromJwtToken(token);
        return Role.valueOf((String) claims.get("role"));
    }

    public static String getUsernameFromJwtToken(String token) {
        return getClaimsFromJwtToken(token).getSubject();
    }
}