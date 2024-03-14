package com.example.mongatest.controller;

import com.example.mongatest.model.ApplicationUser;
import com.example.mongatest.model.Client;
import com.example.mongatest.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AdminController {
    private final AdminServiceImpl service;
    @Autowired
    public AdminController(AdminServiceImpl service) {
        this.service = service;
    }

    @PutMapping("/adminpanel/{id}/{permission}/{value}")
    public ResponseEntity<?> permissionUpdate(@PathVariable(name = "id") String userId, @PathVariable(name = "permission") String permission, @PathVariable(name = "value") Boolean b) {
        ApplicationUser found = service.find(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        found = service.permissionUpdate(found, permission, b);
        if (found != null) return new ResponseEntity<>(found, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
