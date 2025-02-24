package org.pl.serwis_logowania.repositories;

import org.pl.serwis_logowania.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryCurrent extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}