package org.pl.serwis_logowania.services;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.pl.serwis_logowania.dto.UserDTO;
import org.pl.serwis_logowania.entities.User;
import org.pl.serwis_logowania.repositories.UserRepositoryCurrent;
import org.pl.serwis_logowania.utils.JwtUtils;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Getter
public class AuthService {

    String JWT_COOKIE_NAME = "jwtToken";
    private final int CookieAge = 300;

    private final UserRepositoryCurrent userRepositoryCurrent;
    private final CookieService cookieService;
    private final JwtUtils jwtUtils;
    Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder(
                            "",
                            16,
                            650_000,
                                    Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

    public AuthService(UserRepositoryCurrent userRepository1,
                       CookieService cookieService,
                       JwtUtils jwtUtils)
    {
        this.userRepositoryCurrent = userRepository1;
        this.cookieService = cookieService;
        this.jwtUtils = jwtUtils;
    }

    public ResponseCookie login(UserDTO userFromJSON) {
        if (encoder.matches(userFromJSON.getPassword(),
                userRepositoryCurrent.findByLogin(userFromJSON.getLogin()).getPassword()))
        {
            User AuthUser = userRepositoryCurrent.findByLogin(userFromJSON.getLogin());
            String userToken = JwtUtils.generateJwtToken(AuthUser.getLogin(), AuthUser.getRole());
            return cookieService.createCookie(JWT_COOKIE_NAME, userToken, CookieAge);
        } else return null;
    }

    public ResponseCookie refreshJwtToken(HttpServletRequest request) {
        String token = cookieService.getCookieValue(request, JWT_COOKIE_NAME);
        if (JwtUtils.validateJwtToken(token)) {
            String username = JwtUtils.getUsernameFromJwtToken(token);
            User user = userRepositoryCurrent.findByLogin(username);
            String newToken = JwtUtils.generateJwtToken(user.getLogin(), user.getRole());
            return cookieService.createCookie(JWT_COOKIE_NAME, newToken, CookieAge);
        } else return null;
    }

    public ResponseCookie logout(HttpServletRequest request) {
        String token = cookieService.getCookieValue(request, JWT_COOKIE_NAME);
        if (JwtUtils.validateJwtToken(token)) {
            return cookieService.deleteCookie(JWT_COOKIE_NAME);
        } return null;
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
