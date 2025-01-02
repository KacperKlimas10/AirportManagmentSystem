package org.pl.serwis_panel.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.pl.serwis_panel.entities.Airplane;
import org.pl.serwis_panel.enums.Role;
import org.pl.serwis_panel.services.PilotPanelService;
import org.pl.serwis_panel.services.TokenServiceClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // Po ID samolotu
    @GetMapping("/plane")
    public ResponseEntity<List<Airplane>> ListPlanes(HttpServletRequest request) {
        Role rola = this.tokenServiceClient.getRoleFromName(request);
        if (rola == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (rola == Role.pilot || rola == Role.administrator) {
            List<Airplane> planes = this.pilotPanelService.getPlanes();
            if (planes == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                return ResponseEntity.ok(planes);
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
