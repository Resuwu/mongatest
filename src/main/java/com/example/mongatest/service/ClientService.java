package com.example.mongatest.service;

import com.example.mongatest.model.Client;

import java.util.List;
import java.util.Optional;


public interface ClientService {
    void create(Client client);
    List<Client> findAll();
    Optional<Client> find(String id);
    Client update(Client found, Client client);
    void delete(String id);
}
