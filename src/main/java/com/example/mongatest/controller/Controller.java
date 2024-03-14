package com.example.mongatest.controller;

import com.example.mongatest.model.Client;
import com.example.mongatest.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    private final MyService service;
    @Autowired
    public Controller(MyService service) {
        this.service = service;
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Client client, @RequestHeader(value = "USER_ID", required = false) String userId) {
        if (userId == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        service.create(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> findAll(@RequestHeader(value = "USER_ID", required = false) String userId) {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> find(@PathVariable(name = "id") String id, @RequestHeader(value = "USER_ID", required = false) String userId) {
        Optional<Client> found = service.find(id);
        return found.map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/clients{id}")
    public ResponseEntity<Client> update(@PathVariable(name = "id") String id, @RequestBody Client client, @RequestHeader(value = "USER_ID", required = false) String userId) {
        Client found = service.find(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(service.update(found, client), HttpStatus.OK);
    }
    @DeleteMapping("/clients{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") String id, @RequestHeader(value = "USER_ID", required = false) String userId) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
