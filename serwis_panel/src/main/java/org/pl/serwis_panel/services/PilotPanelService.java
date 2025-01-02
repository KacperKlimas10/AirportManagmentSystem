package org.pl.serwis_panel.services;

import org.pl.serwis_panel.config.WebClientConfig;
import org.pl.serwis_panel.entities.Airplane;
import org.pl.serwis_panel.repositories.AirplaneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PilotPanelService {

    private final AirplaneRepository airplaneRepository;
    private final WebClientConfig webClientConfig;

    public PilotPanelService(AirplaneRepository airplaneRepository, WebClientConfig webClientConfig) {
        this.airplaneRepository = airplaneRepository;
        this.webClientConfig = webClientConfig;
    }

    public List<Airplane> getPlanes() {
        return this.airplaneRepository.findAll();
    }

    public Airplane getAirplaneById(int id) {
        return this.airplaneRepository.findById(id).get();
    }
}
