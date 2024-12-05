package org.pl.serwis_logownia.entities;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
public class User {
    Long ID_Użytkownika;
    String Imię;
    String Nazwisko;
    String Login;
    String Hasło;
    Role Rola;
}