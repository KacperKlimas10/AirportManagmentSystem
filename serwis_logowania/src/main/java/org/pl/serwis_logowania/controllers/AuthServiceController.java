package org.pl.serwis_logowania.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.pl.serwis_logowania.entities.User;
import org.pl.serwis_logowania.entities.JsonUser;
import org.pl.serwis_logowania.services.AuthService;
import org.pl.serwis_logowania.services.CookieService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthServiceController {

    private final AuthService authService;

    public AuthServiceController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("")
    public String showLoginPage() {return "In progress";}

    @GetMapping("/verifytoken")
    public ResponseEntity<?> verifyToken(HttpServletRequest request) {
        if (authService.jwtVerifyToken(request)) {
            String token = CookieService.getCookieValue(request, authService.getJWT_COOKIE_NAME());
            String name = authService.getUsernameFromToken(token);
            return ResponseEntity.ok(name);
        }
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JsonUser user, HttpServletResponse response) {
        try {
            ResponseCookie responseCookie = authService.loginUserCookie(user);
            response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
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
