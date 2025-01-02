package org.pl.serwis_panel.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.pl.serwis_panel.dto.UserDTO;
import org.pl.serwis_panel.enums.Role;
import org.pl.serwis_panel.services.TokenServiceClient;
import org.pl.serwis_panel.entities.User;
import org.pl.serwis_panel.services.AdminPanelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/panel/admin")
public class AdminPanelController {

    private final TokenServiceClient tokenServiceClient;
    private final AdminPanelService adminPanelService;

    public AdminPanelController(AdminPanelService adminPanelService,
                                TokenServiceClient tokenServiceClient) {
        this.tokenServiceClient = tokenServiceClient;
        this.adminPanelService = adminPanelService;
    }

    @GetMapping
    public ResponseEntity<Role> getRole(HttpServletRequest request) {
        Role rola = tokenServiceClient.getRoleFromName(request);
        if (rola != null) {
            return ResponseEntity.ok(rola);
        } else return ResponseEntity.status(403).build();
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(HttpServletRequest request) {
        Role rola = tokenServiceClient.getRoleFromName(request);
        if (rola != null && rola.equals(Role.administrator)) {
            return ResponseEntity.ok(adminPanelService.getAllUsers());
        } else return ResponseEntity.status(403).build();
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        Role rola = tokenServiceClient.getRoleFromName(request);
        if (rola != null && rola.equals(Role.administrator)) {
            adminPanelService.createUser(userDTO);
            return ResponseEntity.status(201).build();
        } else return ResponseEntity.status(403).build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id, HttpServletRequest request) {
        Role rola = tokenServiceClient.getRoleFromName(request);
        if (rola != null && rola.equals(Role.administrator)) {
            return ResponseEntity.ok(adminPanelService.getUserById(id));
        } else return ResponseEntity.status(403).build();
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id,
                                           @RequestBody UserDTO userDTO,
                                           HttpServletRequest request) {
        Role rola = tokenServiceClient.getRoleFromName(request);
        if (rola != null && rola.equals(Role.administrator)) {
            UserDTO user = adminPanelService.updateUser(userDTO, id);
            if (user != null) {
                return ResponseEntity.noContent().build();
            } else return ResponseEntity.noContent().build();
        } else return ResponseEntity.status(403).build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id, HttpServletRequest request) {
        Role rola = tokenServiceClient.getRoleFromName(request);
        if (rola != null && rola.equals(Role.administrator)) {
            User user = adminPanelService.getUserById(id);
            if (user != null) {
                adminPanelService.deleteUser(user);
                return ResponseEntity.noContent().build();
            } else return ResponseEntity.status(404).build();
        } else return ResponseEntity.status(403).build();
    }
}

