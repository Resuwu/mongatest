package com.example.mongatest.controller;

import com.example.mongatest.model.ApplicationUser;
import com.example.mongatest.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/adminpanel")
@RestController
public class AdminController {
    private final AdminServiceImpl service;
    @Autowired
    public AdminController(AdminServiceImpl service) {
        this.service = service;
    }

    //NEW USER REGISTRATION
    @PostMapping("/registration")
    public ResponseEntity<?> signUpNewUser() {
        ApplicationUser user = new ApplicationUser();
        service.signUpNewUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //USER DELETION
    @DeleteMapping("/{id}/rem/account")
    public ResponseEntity<?> removeUser(@PathVariable(name = "id") String id) {
        service.removeUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //ADD PERMISSION TO USER
    @PutMapping("/{id}/add/{permission}")
    public ResponseEntity<?> addPermission(@PathVariable(name = "id") String id, @PathVariable(name = "permission") String permission) {
        ApplicationUser found = service.find(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        found = service.addPermission(found, permission);
        if (found != null) return new ResponseEntity<>(found, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    //REMOVE PERMISSION FROM USER
    @PutMapping("/{id}/rem/{permission}")
    public ResponseEntity<?> removePermission(@PathVariable(name = "id") String id, @PathVariable(name = "permission") String permission) {
        ApplicationUser found = service.find(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        found = service.removePermission(found, permission);
        if (found != null) return new ResponseEntity<>(found, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    //GET LIST OF USERS WITH SPECIFIC PERMISSION
    @GetMapping("/{permission}")
    public  ResponseEntity<List<ApplicationUser>> findUsersWithPermission(@PathVariable(name = "permission") String permission) {
        return new ResponseEntity<>(service.findUsersWithPermission(permission), HttpStatus.OK);
    }
}