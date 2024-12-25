package org.pl.serwis_panel.repositories;

import org.pl.serwis_panel.entities.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaggageRepository extends JpaRepository<Baggage, Integer> {
}