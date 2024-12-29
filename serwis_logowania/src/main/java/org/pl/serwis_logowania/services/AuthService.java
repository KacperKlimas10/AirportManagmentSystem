package org.pl.serwis_logowania.services;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.pl.serwis_logowania.entities.JsonUser;
import org.pl.serwis_logowania.entities.User;
import org.pl.serwis_logowania.repositories.UserRepository;
import org.pl.serwis_logowania.utils.HashHandler;
import org.pl.serwis_logowania.utils.JwtUtils;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
@Getter
public class AuthService {

    String JWT_COOKIE_NAME = "jwtToken";
    private final int maxAge = 3600;

    private final UserRepository userRepository;
    private final CookieService cookieService;
    private final JwtUtils jwtUtils;

    public AuthService(UserRepository userRepository, CookieService cookieService, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.cookieService = cookieService;
        this.jwtUtils = jwtUtils;
    }

    public ResponseCookie loginUserCookie(JsonUser userFromJSON) {
        if (HashHandler.validateHash(userFromJSON.getPassword(),
                userRepository.findByLogin(userFromJSON.getLogin()).getHasło()))
        {
            User AuthUser = userRepository.findByLogin(userFromJSON.getLogin());
            String userToken = JwtUtils.generateJwtToken(AuthUser.getLogin(), AuthUser.getRola());
            return cookieService.createCookie(JWT_COOKIE_NAME, userToken, maxAge);
        } else return null;
    }

    public ResponseCookie refreshJwtToken(HttpServletRequest request) {
        String token = cookieService.getCookieValue(request, JWT_COOKIE_NAME);
        if (JwtUtils.validateJwtToken(token)) {
            String username = JwtUtils.getUsernameFromJwtToken(token);
            User user = userRepository.findByLogin(username);
            String newToken = JwtUtils.generateJwtToken(user.getLogin(), user.getRola());
            return cookieService.createCookie(JWT_COOKIE_NAME, newToken, maxAge);
        } else return null;
    }

    public String getNameFromJWTCookie(HttpServletRequest request) {
        String token = cookieService.getCookieValue(request, JWT_COOKIE_NAME);
        if (JwtUtils.validateJwtToken(token)) {
            return JwtUtils.getUsernameFromJwtToken(token);
        } return null;
    }

    public boolean jwtVerifyToken(String token) {
        return JwtUtils.validateJwtToken(token);
    }

    public String getUsernameFromToken(String token) {
        return JwtUtils.getUsernameFromJwtToken(token);
    }
}
