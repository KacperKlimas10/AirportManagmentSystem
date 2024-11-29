package org.pl.serwis_logownia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthServiceController {

    @Autowired
    AuthServiceRepository auth = new AuthServiceRepository();

    @GetMapping("")
    public String showLoginPage() {
        return "In progress";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        try {
            return auth.loginUser(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        try {
            auth.registerUser(user);
            return "OK";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
