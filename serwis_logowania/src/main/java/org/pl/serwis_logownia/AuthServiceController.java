package org.pl.serwis_logownia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/login")
public class AuthServiceController {

    @Autowired
    AuthServiceRepository authServiceRepository;

    @GetMapping("")
    public String showLoginPage() {
        return "In progress";
    }

    @PostMapping("")
    public String register(@RequestBody User user) {
        AuthServiceRepository repo = new AuthServiceRepository();
        try {
            repo.registerUser(user);
            return "OK";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
