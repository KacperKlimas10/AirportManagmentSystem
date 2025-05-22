package org.pl.serwis_panel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pl.serwis_panel.enums.Role;

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Użytkownik")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Użytkownika")
    private int id;

    @Column(name = "Imię")
    private String name;

    @Column(name = "Nazwisko")
    private String surname;

    @Column(name = "Login")
    private String login;

    @Column(name = "Hasło")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "Rola")
    private Role role;
}