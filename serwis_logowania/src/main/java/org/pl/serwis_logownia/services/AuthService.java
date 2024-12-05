package org.pl.serwis_logownia.services;

import org.pl.serwis_logownia.config.DatabaseConfig;
import org.pl.serwis_logownia.entities.User;
import org.pl.serwis_logownia.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    UserRepository userRepository = new UserRepository(DatabaseConfig.getConfiguredTemplate());

    public void registerUser(User user) {userRepository.insertUser(user);}

    public String loginUser(User user) {return null;}

    public List<User> getUsers() {return userRepository.getUsersList();}

    public User getUser() {return userRepository.findByLogin("AdamusSkyrim");}
}
