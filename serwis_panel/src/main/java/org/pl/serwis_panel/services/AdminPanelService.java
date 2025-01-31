package org.pl.serwis_panel.services;

import lombok.RequiredArgsConstructor;
import org.pl.serwis_panel.dto.UserDTO;
import org.pl.serwis_panel.entities.User;
import org.pl.serwis_panel.mappers.UserMapper;
import org.pl.serwis_panel.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminPanelService {

    private final UserRepository userRepository;

    public User getUserById(int id) {return userRepository.getUserById(id);}

    public List<User> getAllUsers() {return userRepository.findAll();}

    public void deleteUser(User user) {userRepository.delete(user);}

    public void updateUser(UserDTO userDTO, int id) {
        User user = UserMapper.modifyUser(userDTO, getUserById(id));
        if (user != null) {
            userRepository.save(user);
        } else throw new IllegalArgumentException("User not found");
    }

    public void createUser(UserDTO userDTO) {
        User user = UserMapper.addUser(userDTO);
        userRepository.save(user);
    }
}