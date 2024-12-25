package org.pl.serwis_panel.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.pl.serwis_panel.entities.enums.Role;
import org.pl.serwis_panel.services.TokenServiceClient;
import org.pl.serwis_panel.entities.User;
import org.pl.serwis_panel.services.AdminPanelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/panel/admin")
public class AdminPanelServiceController {

    private final TokenServiceClient tokenServiceClient;
    private final AdminPanelService adminPanelService;

    public AdminPanelServiceController(AdminPanelService adminPanelService,
                                       TokenServiceClient tokenServiceClient) {
        this.tokenServiceClient = tokenServiceClient;
        this.adminPanelService = adminPanelService;
    }

    @GetMapping("")
    public ResponseEntity<Role> getRole(HttpServletRequest request) {
        Role rola = tokenServiceClient.getRoleFromName(request);
        if (rola != null) {
            return ResponseEntity.ok(rola);
        }
        else return ResponseEntity.status(403).build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id, HttpServletRequest request) {
        Role rola = tokenServiceClient.getRoleFromName(request);
        if (rola != null && rola.equals(Role.administrator)) {
            return ResponseEntity.ok(adminPanelService.getUserById(id));
        }
        else return ResponseEntity.status(403).build();
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(HttpServletRequest request) {
        Role rola = tokenServiceClient.getRoleFromName(request);
        if (rola != null && rola.equals(Role.administrator)) {
            return ResponseEntity.ok(adminPanelService.getAllUsers());
        }
        else return ResponseEntity.status(403).build();
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user, HttpServletRequest request) {
        Role rola = tokenServiceClient.getRoleFromName(request);
        if (rola == null  || !rola.equals(Role.administrator)) {
            return ResponseEntity.status(403).build();
        }

        User userPatch = adminPanelService.getUserById(id);
        if (userPatch == null || adminPanelService.updateUser(user, userPatch) == null) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(userPatch);
    }
}

