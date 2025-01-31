package org.pl.serwis_panel.controllers;

import lombok.RequiredArgsConstructor;
import org.pl.serwis_panel.dto.PassengerDTO;
import org.pl.serwis_panel.services.StaffPanelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/panel/staff")
@RequiredArgsConstructor
public class StaffPanelController {

    private final StaffPanelService staffPanelService;

    @GetMapping("/passenger")
    public ResponseEntity<PassengerDTO> getPassenger(@RequestParam String name, @RequestParam String surname) {
        PassengerDTO passengerDTO = staffPanelService.getPassengerByName(name, surname);
        return (passengerDTO != null) ? ResponseEntity.ok(passengerDTO) : ResponseEntity.status(404).build();
    }

    @PatchMapping("/passenger")
    public ResponseEntity<?> patchPassenger(@RequestParam String name, @RequestParam String surname, @RequestBody PassengerDTO passengerDTO) {
        return (staffPanelService.updatePassenger(name, surname, passengerDTO))
                ? ResponseEntity.noContent().build()
                : ResponseEntity.status(404).build();
    }
}
