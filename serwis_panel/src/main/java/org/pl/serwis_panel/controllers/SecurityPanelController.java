package org.pl.serwis_panel.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.pl.serwis_panel.dto.FailureDTO;
import org.pl.serwis_panel.enums.Role;
import org.pl.serwis_panel.services.SecurityPanelService;
import org.pl.serwis_panel.services.TokenServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/panel/security")
@RequiredArgsConstructor
public class SecurityPanelController {

    private final TokenServiceClient tokenServiceClient;
    private final SecurityPanelService securityPanelService;

    @PostMapping("/report")
    public ResponseEntity<?> reportFailure(@RequestBody FailureDTO failureDTO, HttpServletRequest request) {
        Role rola = tokenServiceClient.getRoleFromName(request);
        if (rola != null && (rola.equals(Role.ochrona) || rola.equals(Role.administrator))) {
            securityPanelService.saveFailure(failureDTO);
            return ResponseEntity.ok().build();
        } else return ResponseEntity.status(403).build();
    }
}
