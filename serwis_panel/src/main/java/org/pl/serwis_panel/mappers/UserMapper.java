package org.pl.serwis_panel.mappers;

import org.pl.serwis_panel.dto.UserDTO;
import org.pl.serwis_panel.entities.User;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class UserMapper {

    static Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder(
            "",
            16,
            650_000,
            Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

   public static User modifyUser(UserDTO userDTO, User user) {
           if (userDTO.getName() != null) user.setName(userDTO.getName());
           if (userDTO.getSurname() != null) user.setSurname(userDTO.getSurname());
           if (userDTO.getLogin() != null) user.setLogin(userDTO.getLogin());
           if (userDTO.getPassword() != null) user.setPassword(encoder.encode(userDTO.getPassword()));
           if (userDTO.getRole() != null) user.setRole(userDTO.getRole());
           return user;
    }

    public static User addUser(UserDTO userDTO) {
        User user = new User();
            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());
            user.setLogin(userDTO.getLogin());
            user.setPassword(encoder.encode(userDTO.getPassword()));
            user.setRole(userDTO.getRole());
        return user;
    }
}
