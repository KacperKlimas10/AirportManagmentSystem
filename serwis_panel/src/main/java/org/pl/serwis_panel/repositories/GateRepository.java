package org.pl.serwis_panel.repositories;

import org.pl.serwis_panel.entities.Gate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GateRepository extends JpaRepository<Gate, Integer> {
}