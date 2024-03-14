package com.example.mongatest.service;

import com.example.mongatest.model.Client;
import com.example.mongatest.repos.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyService implements ClientService {
    private final ClientRepository clientRepository;
    @Autowired
    public MyService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void create(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> find(String id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client update(Client found, Client client) {
        found.setId(client.getId());
        found.setName(client.getName());
        found.setEmail(client.getEmail());
        found.setPhone(client.getPhone());
        return clientRepository.save(found);
    }

    @Override
    public void delete(String id) {
        clientRepository.deleteById(id);
    }
}
