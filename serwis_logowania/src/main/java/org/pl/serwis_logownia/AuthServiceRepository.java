package org.pl.serwis_logownia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthServiceRepository {

    @Autowired
    JdbcTemplate jdbcTemplate = new JdbcTemplate(DbConfig.getDataSource());

    public String registerUser(User user) {
       try {
           jdbcTemplate.update(
                   "INSERT INTO Użytkownik (Login, Hasło) VALUES (?, ?)",
                        user.getUsername(), HashHandler.sha256(user.getPassword()));
           return "OK";
       } catch (DataAccessException e) {
           throw new RuntimeException(e);
       }
    }

    public String loginUser(User user) {
        try {
            String db_hash = jdbcTemplate.query("SELECT HASŁO FROM Użytkownik WHERE Login = ?",
                    user.getUsername(), );
            if (db_hash != null) {
                if (HashHandler.validateUser(user, db_hash)) {
                    return user.getUsername() + " logged in!";
                }
            }
            return "Invalid user or password";
        } catch (DataAccessException e) {throw new RuntimeException(e);}
    }
}
