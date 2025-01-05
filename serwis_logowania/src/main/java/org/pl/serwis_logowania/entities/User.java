package org.pl.serwis_logowania.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.pl.serwis_logowania.enums.Role;

@Data
@Entity
@Table(name = "`Użytkownik`", schema = "lotnisko_baza", uniqueConstraints = {
        @UniqueConstraint(name = "Login", columnNames = {"Login"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID_Użytkownika`", nullable = false)
    private Integer id;

    @Column(name = "`Imię`", length = 50)
    private String name;

    @Column(name = "Nazwisko", length = 50)
    private String surname;

    @Column(name = "Login", nullable = false, length = 50)
    private String login;

    @Column(name = "`Hasło`", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "Rola")
    private Role role;
}