package org.pl.serwis_logownia;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@Component
public class DbConfig {
    public static DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://192.168.19.113:3306/lotnisko_baza")
                .username("root")
                .password("haslo")
                .build();
    }
}
