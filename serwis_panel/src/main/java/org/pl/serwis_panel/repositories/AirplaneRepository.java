package org.pl.serwis_panel.repositories;

import org.pl.serwis_panel.entities.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<Airplane, Integer> {
    Airplane findById(int id);
}