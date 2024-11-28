package org.pl.serwis_logownia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.net.http.HttpResponse;

@Repository
public class AuthServiceRepository {

    @Autowired
    JdbcTemplate jdbcTemplate = new JdbcTemplate(DbConfig.getDataSource());

    public void registerUser(User user) {
       try {
           jdbcTemplate.update(
                   "INSERT INTO Użytkownik (Login, Hasło) VALUES (?, ?)",
                        user.getUsername(), user.sha256(user.getPassword()));
       } catch (DataAccessException e) {
           throw new RuntimeException(e);
       }
    }
}
