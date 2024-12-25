package org.pl.serwis_panel.repositories;

import org.pl.serwis_panel.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserById(int id);
    User getUserByLogin(String login);
}

