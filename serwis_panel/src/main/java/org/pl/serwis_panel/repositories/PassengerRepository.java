package org.pl.serwis_panel.repositories;

import org.pl.serwis_panel.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
}