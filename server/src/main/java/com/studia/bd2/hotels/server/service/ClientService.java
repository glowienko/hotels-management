package com.studia.bd2.hotels.server.service;

import com.studia.bd2.hotels.server.database.entity.Client;
import com.studia.bd2.hotels.server.database.repository.ClientFieldsRepository;
import com.studia.bd2.hotels.server.database.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientFieldsRepository fieldsRepository;

    public Client createClient(Client newClient) {

        setClientToFields(newClient);
        return clientRepository.save(newClient);
    }

    private void setClientToFields(Client client) {
        client.getClientFields()
                .forEach(clientField -> clientField.setClient(client));
    }
}
