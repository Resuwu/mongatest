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

    //CREATE NEW RECORD
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Client client) {
        service.create(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //GET LIST WITH ALL RECORDS
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    //FIND RECORD BY ID
    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> find(@PathVariable(name = "id") String id) {
        Optional<Client> found = service.find(id);
        return found.map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //UPDATE RECORD
    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> update(@PathVariable(name = "id") String id, @RequestBody Client client) {
        Client found = service.find(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(service.update(found, client), HttpStatus.OK);
    }

    //DELETE RECORD
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
