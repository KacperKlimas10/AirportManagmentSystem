package org.pl.serwis_panel.services;

import org.pl.serwis_panel.entities.Airplane;
import org.pl.serwis_panel.repositories.AirplaneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PilotPanelService {
    private final AirplaneRepository airplaneRepository;

    public PilotPanelService(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    public List<Airplane> getPlanes() {
        return this.airplaneRepository.findAll();
    }

    public Airplane getAirplaneById(int id) {
        return this.airplaneRepository.findById(id).get();
    }
}
