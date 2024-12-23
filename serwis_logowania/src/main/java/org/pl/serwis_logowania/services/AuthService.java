package org.pl.serwis_logowania.services;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.pl.serwis_logowania.entities.User;
import org.pl.serwis_logowania.entities.JsonUser;
import org.pl.serwis_logowania.repositories.UserRepository;
import org.pl.serwis_logowania.utils.HashHandler;
import org.pl.serwis_logowania.utils.JwtUtils;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class AuthService {

    String JWT_COOKIE_NAME = "jwtToken";
    UserRepository userRepository = new UserRepository();

    public void registerUser(JsonUser user) {
        User userquery = new User();
        userquery.setLogin(user.getUsername());
        userquery.setHasło(user.getPassword());
        userRepository.insertUser(userquery);}

    public List<User> getUsers() {return userRepository.getUsersList();}

    public User getUser(String user) {return userRepository.findByLogin(user);}

    public ResponseCookie loginUserCookie(JsonUser userFromJSON) {
        if (HashHandler.validateHash(userFromJSON.getPassword(),
                userRepository.findByLogin(userFromJSON.getUsername()).getHasło()))
        {
            User AuthUser = userRepository.findByLogin(userFromJSON.getUsername());
            // Tworzymy JWT Token
            String userToken = JwtUtils.generateJwtToken(AuthUser.getLogin(), AuthUser.getRola());
            // Tworzymy ciastko z tokenem
            return CookieService.createCookie(JWT_COOKIE_NAME, userToken, 3600);
        }
        return null;
    }

    public boolean jwtVerifyToken(HttpServletRequest request) {
        String token = CookieService.getCookieValue(request, JWT_COOKIE_NAME);
        return JwtUtils.validateJwtToken(token);
    }

    public ResponseCookie refreshJwtToken(HttpServletRequest request) {
        String token = CookieService.getCookieValue(request, JWT_COOKIE_NAME);
        if (JwtUtils.validateJwtToken(token)) {
            String username = JwtUtils.getUsernameFromJwtToken(token);
            User user = userRepository.findByLogin(username);
            String newToken = JwtUtils.generateJwtToken(user.getLogin(), user.getRola());
            return CookieService.createCookie(JWT_COOKIE_NAME, newToken, 3600);
        }
        return null;
    }

    public String getUsernameFromToken(String token) {
        return JwtUtils.getUsernameFromJwtToken(token);
    }
}
