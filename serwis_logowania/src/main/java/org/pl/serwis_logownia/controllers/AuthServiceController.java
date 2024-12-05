package org.pl.serwis_logownia.controllers;

import org.pl.serwis_logownia.entities.User;
import org.pl.serwis_logownia.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthServiceController {

    AuthService auth = new AuthService();

    @GetMapping("")
    public String showLoginPage() {
        return "In progress";
    }

    @GetMapping("/users")
    public List<User> getUsers() {return auth.getUsers();}

    @GetMapping("/user")
    public User getUser() {return auth.getUser();}

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        try {
            return auth.loginUser(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        try {
            auth.registerUser(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
