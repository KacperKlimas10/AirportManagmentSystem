package org.pl.serwis_panel.repositories;

import java.util.List;
import org.pl.serwis_panel.entities.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Integer> {
    Airplane findById(int id);

    @Query("SELECT a FROM Airplane a JOIN FETCH a.flights")
    List<Airplane> findAllWithFlights();
}