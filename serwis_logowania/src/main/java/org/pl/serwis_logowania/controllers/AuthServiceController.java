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

    AuthService authService = new AuthService();

    @GetMapping("/verifytoken")
    public ResponseEntity<?> verifyToken(HttpServletRequest request) {
        try {
            if (authService.jwtVerifyToken(request)) {
                String token = CookieService.getCookieValue(request, authService.getJWT_COOKIE_NAME());
                String name = authService.getUsernameFromToken(token);
                return ResponseEntity.ok(name);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("")
    public String showLoginPage() {
        return "In progress";
    }

    @GetMapping("/users")
    public List<User> getUsers() {return authService.getUsers();}

    @GetMapping("/user/{name}")
    public User getUser(@PathVariable("name") String name) {
        return authService.getUser(name);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JsonUser user, HttpServletResponse response) {
        try {
            ResponseCookie responseCookie = authService.loginUserCookie(user);
            response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
            return ResponseEntity.ok("Logged in :D");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody JsonUser user) {
        try {
            authService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshJwtToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            ResponseCookie responseCookie = authService.refreshJwtToken(request);
            if (responseCookie != null) {
                response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
                return ResponseEntity.ok("Token refreshed");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
