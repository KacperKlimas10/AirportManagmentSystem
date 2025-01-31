package org.pl.serwis_panel.controllers;

import lombok.RequiredArgsConstructor;
import org.pl.serwis_panel.dto.AirplaneDTO;
import org.pl.serwis_panel.dto.FlightDTO;
import org.pl.serwis_panel.dto.openweather.api.OpenWeatherDTO;
import org.pl.serwis_panel.entities.Airplane;
import org.pl.serwis_panel.services.PilotPanelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/panel/pilot")
@RequiredArgsConstructor
public class PilotPanelController {

    private final PilotPanelService pilotPanelService;

    @GetMapping("/plane")
    public ResponseEntity<List<AirplaneDTO>> listPlanes() {
        List<AirplaneDTO> planes = this.pilotPanelService.getPlanesWithFlights();
        if (planes == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(planes);
        }
    }

    @GetMapping("/plane/{id}")
    public ResponseEntity<Airplane> checkPlane(@PathVariable int id) {
        Airplane plane = this.pilotPanelService.getAirplaneById(id);
        if (plane != null) {
            return ResponseEntity.ok(plane);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/flight/{id}")
    public ResponseEntity<FlightDTO> getFlight(@PathVariable int id) {
        FlightDTO flight = this.pilotPanelService.getFlightById(id);
        if (flight != null) {
            return ResponseEntity.ok(flight);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/weather")
    public ResponseEntity<OpenWeatherDTO> getWeather(@RequestParam String city) {
        return ResponseEntity.ok(pilotPanelService.getWeather(city));
    }
}