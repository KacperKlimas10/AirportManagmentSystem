package org.pl.serwis_panel.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.pl.serwis_panel.config.WebClientConfig;
import org.pl.serwis_panel.entities.User;
import org.pl.serwis_panel.enums.Role;
import org.pl.serwis_panel.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceClient {

    String JWT_COOKIE_NAME = "jwtToken";

    private final UserRepository userRepository;
    private final WebClientConfig webClientConfig;

    public TokenServiceClient(UserRepository userRepository, WebClientConfig webClientConfig) {
        this.userRepository = userRepository;
        this.webClientConfig = webClientConfig;
    }

    public String getUsernameFromExternalService(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(JWT_COOKIE_NAME)) {
                try {
                    return webClientConfig.webClientTokenServiceURL()
                            .get()
                            .uri("/auth/verifytoken")
                            .header("Authorization", "Bearer " + cookie.getValue())
                            .retrieve()
                            .bodyToMono(String.class)
                            .block();
                } catch (Exception e) {
                    System.out.println(e);
                    return null;
                }
            }
        } return null;
    }

    public Role getRoleFromName(HttpServletRequest request) {
        String login = getUsernameFromExternalService(request);
        User user = userRepository.getUserByLogin(login);
        if (login != null && user != null) {
            return user.getRole();
        } else return null;
    }
}
