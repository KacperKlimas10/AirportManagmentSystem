package org.pl.serwis_panel.repositories;

import org.pl.serwis_panel.entities.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepository extends JpaRepository<Transport, Integer> {
}