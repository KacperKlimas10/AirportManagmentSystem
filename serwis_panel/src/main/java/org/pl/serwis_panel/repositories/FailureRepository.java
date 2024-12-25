package org.pl.serwis_panel.repositories;

import org.pl.serwis_panel.entities.Failure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailureRepository extends JpaRepository<Failure, Integer> {
}