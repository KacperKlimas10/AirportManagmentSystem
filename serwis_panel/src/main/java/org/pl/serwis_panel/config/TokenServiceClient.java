package org.pl.serwis_panel.config;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class TokenServiceClient {

    String JWT_COOKIE_NAME = "jwtToken";
    private final WebClient webClient = WebClient.create("http://localhost:8080");

    public String getUsernameFromExternalService(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(JWT_COOKIE_NAME)) {
                try {
                    return webClient.get()
                            .uri("auth/verifytoken")
                            .header("Cookie", "jwtToken=" + cookie.getValue())
                            .retrieve()
                            .bodyToMono(String.class)
                            .block();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }
}
