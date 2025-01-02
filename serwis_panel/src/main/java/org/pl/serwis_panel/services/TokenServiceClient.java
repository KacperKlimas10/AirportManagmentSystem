package org.pl.serwis_panel.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.pl.serwis_panel.entities.User;
import org.pl.serwis_panel.enums.Role;
import org.pl.serwis_panel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TokenServiceClient {

    @Value("${token.service.url}")
    private String webClientUrl;

    String JWT_COOKIE_NAME = "jwtToken";
    private final UserRepository userRepository;

    public TokenServiceClient(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUsernameFromExternalService(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(JWT_COOKIE_NAME)) {
                try {
                    return WebClient.builder()
                            .build()
                            .get()
                            .uri(webClientUrl)
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
