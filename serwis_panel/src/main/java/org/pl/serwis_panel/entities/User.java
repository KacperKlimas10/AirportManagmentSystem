package org.pl.serwis_panel.entities;

import jakarta.persistence.*;
import lombok.*;
import org.pl.serwis_panel.entities.enums.Role;

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Użytkownik")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Użytkownika")
    private Long id;

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