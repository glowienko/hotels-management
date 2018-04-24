package com.studia.bd2.hotels.server.service;

import com.studia.bd2.hotels.server.database.entity.Client;
import com.studia.bd2.hotels.server.database.entity.ClientField;
import com.studia.bd2.hotels.server.database.repository.ClientFieldsRepository;
import com.studia.bd2.hotels.server.database.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientFieldsRepository fieldsRepository;

    public Client createClient(Client newClient) {
        Client createdClient = clientRepository.save(newClient);

        setClientToFields(createdClient);
        saveClientFields(createdClient.getClientFields());

        return createdClient;
    }

    private void setClientToFields(Client createdClient) {
        createdClient.getClientFields()
                .forEach(clientField -> clientField.setClient(createdClient));
    }

    private List<ClientField> saveClientFields(List<ClientField> clientFields) {
        return fieldsRepository.saveAll(clientFields);
    }
}
