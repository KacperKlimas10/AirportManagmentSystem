package org.pl.serwis_panel.controllers;

import lombok.RequiredArgsConstructor;
import org.pl.serwis_panel.dto.FailureDTO;
import org.pl.serwis_panel.services.SecurityPanelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/panel/security")
@RequiredArgsConstructor
public class SecurityPanelController {

    private final SecurityPanelService securityPanelService;

    @PostMapping("/report")
    public ResponseEntity<?> reportFailure(@RequestBody FailureDTO failureDTO) {
            securityPanelService.saveFailure(failureDTO);
            return ResponseEntity.noContent().build();
    }
}
