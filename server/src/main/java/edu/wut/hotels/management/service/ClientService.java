package edu.wut.hotels.management.service;

import edu.wut.hotels.management.database.entity.Client;
import edu.wut.hotels.management.database.repository.ClientFieldsRepository;
import edu.wut.hotels.management.database.repository.ClientRepository;
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
