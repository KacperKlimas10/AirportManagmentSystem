package org.pl.serwis_panel.repositories;

import org.pl.serwis_panel.entities.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, Integer> {
}