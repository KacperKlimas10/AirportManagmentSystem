package org.pl.serwis_panel.dto;

import lombok.Data;
import org.pl.serwis_panel.enums.Role;

@Data
public class UserDTO {
    private String name;
    private String surname;
    private String login;
    private String password;
    private Role role;
}