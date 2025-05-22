package org.pl.serwis_panel.repositories;

import org.pl.serwis_panel.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    Flight findById(int id);
}