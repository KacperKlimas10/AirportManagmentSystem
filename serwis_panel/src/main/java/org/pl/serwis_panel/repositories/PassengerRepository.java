package org.pl.serwis_panel.repositories;

import org.pl.serwis_panel.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

    @Query("SELECT p FROM Passenger p WHERE p.firstName = ?1 AND p.lastName = ?2")
    Passenger getPassagerByName(String name, String surname);
}

