package org.pl.serwis_panel.repositories;

import org.pl.serwis_panel.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}