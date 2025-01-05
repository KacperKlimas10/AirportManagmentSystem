package org.pl.serwis_panel.mappers;

import org.pl.serwis_panel.dto.UserDTO;
import org.pl.serwis_panel.entities.User;
import org.pl.serwis_panel.utils.HashHandler;

public class UserMapper {

   public static User modifyUser(UserDTO userDTO, User user) {
           if (userDTO.getName() != null) user.setName(userDTO.getName());
           if (userDTO.getSurname() != null) user.setSurname(userDTO.getSurname());
           if (userDTO.getLogin() != null) user.setLogin(userDTO.getLogin());
           if (userDTO.getPassword() != null) user.setPassword(HashHandler.sha256(userDTO.getPassword()));
           if (userDTO.getRole() != null) user.setRole(userDTO.getRole());
           return user;
    }

    public static User addUser(UserDTO userDTO) {
        User user = new User();
            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());
            user.setLogin(userDTO.getLogin());
            user.setPassword(HashHandler.sha256(userDTO.getPassword()));
            user.setRole(userDTO.getRole());
        return user;
    }
}
