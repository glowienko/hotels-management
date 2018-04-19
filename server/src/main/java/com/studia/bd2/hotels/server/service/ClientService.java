package com.studia.bd2.hotels.server.service;

import com.studia.bd2.hotels.server.database.entity.Client;
import com.studia.bd2.hotels.server.database.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;


    public Client createHotelClient(Client newClient) {
        return clientRepository.save(newClient);
    }
}
