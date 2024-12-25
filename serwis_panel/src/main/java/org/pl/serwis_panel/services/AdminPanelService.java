package org.pl.serwis_panel.services;

import org.pl.serwis_panel.entities.User;
import org.pl.serwis_panel.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPanelService {

    private final UserRepository userRepository;

    public AdminPanelService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int id) {return userRepository.getUserById(id);}

    public List<User> getAllUsers() {return userRepository.findAll();}

    public User updateUser(User user, User userPatch) {
        if (userPatch != null) {
            userPatch.setId(user.getId());
            if (user.getName() != null) userPatch.setName(user.getName());
            if (user.getSurname() != null) userPatch.setSurname(user.getSurname());
            if (user.getLogin() != null) userPatch.setLogin(user.getLogin());
            if (user.getPassword() != null) userPatch.setPassword(user.getPassword());
            if (user.getRole() != null) userPatch.setRole(user.getRole());
            return userRepository.save(userPatch);
        } else return null;
    }
}
