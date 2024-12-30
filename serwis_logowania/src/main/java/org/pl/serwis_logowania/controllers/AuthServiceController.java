package org.pl.serwis_logowania.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.pl.serwis_logowania.entities.JsonUser;
import org.pl.serwis_logowania.services.AuthService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthServiceController {

    private final AuthService authService;

    public AuthServiceController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<String> getNameFromCookie(HttpServletRequest request) {
        String username = authService.getNameFromJWTCookie(request);
        if (username != null) {
            return ResponseEntity.ok(username);
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/verifytoken")
    public ResponseEntity<?> verifyToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            if (authService.jwtVerifyToken(token)) {
                String name = authService.getUsernameFromToken(token);
                return ResponseEntity.ok(name);
            }
        } return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JsonUser user, HttpServletResponse response) {
        ResponseCookie responseCookie = authService.loginUserCookie(user);
        if (responseCookie != null) {
            response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
            return ResponseEntity.ok().build();
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshJwtToken(HttpServletRequest request, HttpServletResponse response) {
        ResponseCookie responseCookie = authService.refreshJwtToken(request);
        if (responseCookie != null) {
            response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
            return ResponseEntity.ok().build();
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}