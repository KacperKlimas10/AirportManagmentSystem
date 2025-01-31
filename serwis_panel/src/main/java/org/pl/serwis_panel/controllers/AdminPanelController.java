package org.pl.serwis_panel.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.pl.serwis_panel.dto.UserDTO;
import org.pl.serwis_panel.entities.User;
import org.pl.serwis_panel.enums.Role;
import org.pl.serwis_panel.services.AdminPanelService;
import org.pl.serwis_panel.utils.TokenServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/panel/admin")
@RequiredArgsConstructor
public class AdminPanelController {

    private final AdminPanelService adminPanelService;
    private final TokenServiceClient tokenServiceClient;

    @GetMapping
    public ResponseEntity<Role> getRole(HttpServletRequest request) {
        Optional<Role> rola = tokenServiceClient.getRoleFromName(request);
        return ResponseEntity.ok(rola.orElseThrow(RuntimeException::new));
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(adminPanelService.getAllUsers());
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        adminPanelService.createUser(userDTO);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(adminPanelService.getUserById(id));
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        adminPanelService.updateUser(userDTO, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        User user = adminPanelService.getUserById(id);
        if (user == null) return ResponseEntity.status(404).build();
        adminPanelService.deleteUser(user);
        return ResponseEntity.noContent().build();
    }
}

