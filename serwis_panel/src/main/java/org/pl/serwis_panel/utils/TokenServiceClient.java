package org.pl.serwis_panel.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.pl.serwis_panel.config.WebClientConfig;
import org.pl.serwis_panel.entities.User;
import org.pl.serwis_panel.enums.Role;
import org.pl.serwis_panel.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenServiceClient {

    String JWT_COOKIE_NAME = "jwtToken";

    private final UserRepository userRepository;
    private final WebClientConfig webClientConfig;

    public Optional<String> getUsernameFromExternalService(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return Optional.empty();
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(JWT_COOKIE_NAME)) {
                try {
                    return Optional.ofNullable(
                        webClientConfig.webClientTokenServiceURL()
                                .get()
                                .uri("/auth/verifytoken")
                                .header("Authorization", "Bearer " + cookie.getValue())
                                .retrieve()
                                .bodyToMono(String.class)
                                .block()
                        );
                } catch (Exception e) {
                    throw new RuntimeException("Error while verifying token");
                }
            }
        } return Optional.empty();
    }

    public Optional<Role> getRoleFromName(HttpServletRequest request) {
        Optional<String> login = getUsernameFromExternalService(request);
        User user = userRepository.getUserByLogin(login.orElseThrow(RuntimeException::new));
        if (user != null) {
            return Optional.ofNullable(user.getRole());
        } else return Optional.empty();
    }
}
