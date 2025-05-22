package org.pl.serwis_logowania.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.pl.serwis_logowania.dto.UserDTO;
import org.pl.serwis_logowania.services.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthServiceController {

    private final AuthService authService;

    @GetMapping
    public ResponseEntity<String> getNameFromJWT(HttpServletRequest request) {
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
        } return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO user, HttpServletResponse response) throws NullPointerException {
        ResponseCookie responseCookie = authService.login(user);
        if (responseCookie != null) {
            response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
            return ResponseEntity.ok().build();
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshJwtToken(HttpServletRequest request,
                                             HttpServletResponse response)
    {
        ResponseCookie responseCookie = authService.refreshJwtToken(request);
        if (responseCookie != null) {
            response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
            return ResponseEntity.ok().build();
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request,
                                    HttpServletResponse response)
    {
        ResponseCookie responseCookie = authService.logout(request);
        if (responseCookie != null) {
            response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
            return ResponseEntity.ok().build();
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}