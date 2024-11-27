package org.pl.serwis_logownia;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthServiceController {

    @PostMapping("/authenticate")
    private void login(String username, String password) {

    }


}
