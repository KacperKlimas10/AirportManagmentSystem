package org.pl.serwis_panel.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.pl.serwis_panel.dto.PassengerDTO;
import org.pl.serwis_panel.enums.Role;
import org.pl.serwis_panel.services.StaffPanelService;
import org.pl.serwis_panel.services.TokenServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/panel/staff")
public class StaffPanelController {

    private final StaffPanelService staffPanelService;
    private final TokenServiceClient tokenServiceClient;

    public StaffPanelController(StaffPanelService staffPanelService, TokenServiceClient tokenServiceClient) {
        this.staffPanelService = staffPanelService;
        this.tokenServiceClient = tokenServiceClient;
    }

    @GetMapping("/passenger")
    public ResponseEntity<PassengerDTO> getPassengerByCredentials(@RequestParam String name, @RequestParam String surname, HttpServletRequest request) {
        Role rola = tokenServiceClient.getRoleFromName(request);
        if (rola != null && rola.equals(Role.obsługa_techniczna)) {
            PassengerDTO passengerDTO= staffPanelService.getPassengerByName(name, surname);
            if (passengerDTO != null) {
                return ResponseEntity.ok(passengerDTO);
            } else return ResponseEntity.status(404).build();
        } else return ResponseEntity.status(403).build();
    }
}