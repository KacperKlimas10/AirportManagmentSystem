package org.pl.serwis_logownia.utils;

import io.jsonwebtoken.*;
import org.pl.serwis_logownia.entities.Role;
import org.pl.serwis_logownia.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    public String generateJwtToken(User user, List<Role> roles) {
        Claims claims = Jwts.claims().setSubject(user.getLogin()).build();
        claims.put("roles", roles.stream().map(Enum::name).toList());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("JWT Token is not valid: " + e.getMessage());
        }
        return false;
    }

    public List<Role> getRolesFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();

        List<String> rolesAsString = (List<String>) claims.get("roles");
        return rolesAsString.stream()
                .map(Role::valueOf) // Konwersja String to Enum
                .toList();
    }
}
