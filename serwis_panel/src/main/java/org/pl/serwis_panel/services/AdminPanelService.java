package org.pl.serwis_panel.services;

import org.pl.serwis_panel.entities.User;
import org.pl.serwis_panel.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminPanelService {

    private final UserRepository userRepository;

    public AdminPanelService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
