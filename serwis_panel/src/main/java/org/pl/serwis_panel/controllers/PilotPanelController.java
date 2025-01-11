package org.pl.serwis_panel.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.pl.serwis_panel.dto.AirplaneDTO;
import org.pl.serwis_panel.dto.FlightDTO;
import org.pl.serwis_panel.dto.openweather.api.OpenWeatherDTO;
import org.pl.serwis_panel.entities.Airplane;
import org.pl.serwis_panel.enums.Role;
import org.pl.serwis_panel.services.PilotPanelService;
import org.pl.serwis_panel.services.TokenServiceClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/panel/pilot")
public class PilotPanelController {
    private final TokenServiceClient tokenServiceClient;
    private final PilotPanelService pilotPanelService;

    public PilotPanelController(TokenServiceClient tokenServiceClient, PilotPanelService pilotPanelService) {
        this.pilotPanelService = pilotPanelService;
        this.tokenServiceClient = tokenServiceClient;
    }

    @GetMapping("/plane")
    public ResponseEntity<List<AirplaneDTO>> ListPlanes(HttpServletRequest request) {
        Role rola = this.tokenServiceClient.getRoleFromName(request);
        if (rola == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (rola == Role.pilot || rola == Role.administrator) {
            List<AirplaneDTO> planes = this.pilotPanelService.getPlanesWithFlights();
            if (planes == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                return ResponseEntity.ok(planes);
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/plane/{id}")
    public ResponseEntity<Airplane> CheckPlane(@PathVariable int id, HttpServletRequest request) {
        /* TODO: make method that checks if role is appropriate */
        Role rola = this.tokenServiceClient.getRoleFromName(request);
        if (rola == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (rola == Role.pilot || rola == Role.administrator) {
            Airplane plane = this.pilotPanelService.getAirplaneById(id);
            if (plane != null) {
                return ResponseEntity.ok(plane);
            } else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/flight/{id}")
    public ResponseEntity<FlightDTO> getFlight(@PathVariable int id, HttpServletRequest request)
    {
        Role rola = this.tokenServiceClient.getRoleFromName(request);
        if (rola == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (rola == Role.pilot || rola == Role.administrator) {
            FlightDTO flight = this.pilotPanelService.getFlightById(id);
            if (flight != null) {
                return ResponseEntity.ok(flight);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/weather")
    public ResponseEntity<OpenWeatherDTO> getWeather(@RequestParam String city, HttpServletRequest request)
    {
        Role rola = this.tokenServiceClient.getRoleFromName(request);
        if (rola == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (rola == Role.pilot || rola == Role.administrator) {
            return ResponseEntity.ok(pilotPanelService.getWeather(city));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
