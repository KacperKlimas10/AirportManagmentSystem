package org.pl.serwis_panel.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.pl.serwis_panel.config.TokenServiceClient;
import org.pl.serwis_panel.entities.User;
import org.pl.serwis_panel.services.PanelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/panel")
public class PanelServiceController {

    private final PanelService panelService;

    public PanelServiceController(PanelService panelService) {
        this.panelService = panelService;
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByName(HttpServletRequest request) {
        try {
            TokenServiceClient tokenServiceClient = new TokenServiceClient();
            String login = tokenServiceClient.getUsernameFromExternalService(request);
            if (login != null) {
                return ResponseEntity.ok(panelService.getUserByLogin(login));
            }
            return ResponseEntity.status(403).build();
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User user, HttpServletRequest request) {
        try {
            TokenServiceClient tokenServiceClient = new TokenServiceClient();
            String login = tokenServiceClient.getUsernameFromExternalService(request);
            if (login != null) {
                User userPatch = panelService.getUserById(id);
                if (userPatch != null) {
                    userPatch.setId(id);
                    if (user.getName() != null) userPatch.setName(user.getName());
                    if (user.getSurname() != null) userPatch.setSurname(user.getSurname());
                    if (user.getLogin() != null) userPatch.setLogin(user.getLogin());
                    if (user.getPassword() != null) userPatch.setPassword(user.getPassword());
                    if (user.getRole() != null) userPatch.setRole(user.getRole());
                    return ResponseEntity.ok(panelService.updateUser(userPatch));
                } else return ResponseEntity.status(404).build();
            }
            return ResponseEntity.status(403).build();
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }
}
