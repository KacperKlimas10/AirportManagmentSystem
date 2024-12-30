package org.pl.serwis_panel.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.pl.serwis_panel.dto.PassengerDTO;
import org.pl.serwis_panel.enums.Role;
import org.pl.serwis_panel.services.StaffPanelService;
import org.pl.serwis_panel.services.TokenServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PassengerDTO>
        getPassenger(@RequestParam String name,
                     @RequestParam String surname,
                     HttpServletRequest request)
    {
        Role rola = tokenServiceClient.getRoleFromName(request);
        if (rola != null && (rola.equals(Role.obsługa_techniczna) || rola.equals(Role.administrator))) {
            PassengerDTO passengerDTO= staffPanelService.getPassengerByName(name, surname);
            if (passengerDTO != null) {
                return ResponseEntity.ok(passengerDTO);
            } else return ResponseEntity.status(404).build();
        } else return ResponseEntity.status(403).build();
    }

    @PatchMapping("/passenger")
    public ResponseEntity<?>
        patchPassenger(@RequestParam String name,
                       @RequestParam String surname,
                       @RequestBody PassengerDTO passengerDTO,
                       HttpServletRequest request)
    {
        Role rola = tokenServiceClient.getRoleFromName(request);
        if (rola != null && (rola.equals(Role.obsługa_techniczna) || rola.equals(Role.administrator))) {
            if (staffPanelService.updatePassenger(name, surname, passengerDTO)) {
                return ResponseEntity.noContent().build();
            } else return ResponseEntity.status(404).build();
        } else return ResponseEntity.status(403).build();
    }
}