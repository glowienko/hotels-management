package edu.wut.hotels.management.service;

import edu.wut.hotels.management.database.entity.Client;
import edu.wut.hotels.management.database.repository.ClientFieldsRepository;
import edu.wut.hotels.management.database.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientFieldsRepository fieldsRepository;


    public Optional<Client> getClientByEmail(String email) {
        return clientRepository.getClientByEmail(email);
    }

    public Client createClient(Client newClient) {

        setClientToFields(newClient);
        return clientRepository.save(newClient);
    }

    private void setClientToFields(Client client) {
        client.getClientFields()
                .forEach(clientField -> clientField.setClient(client));
    }

    public List<Client> findBestClientsInYear(int year, int numberOfClients) {
        return clientRepository.findBestClientsInYear(year, numberOfClients);
    }

    public List<Client> findBestClientsSpendingMuchTimeInYear(int year, int numberOfClients, String dateFrom, String dateTo) {
        return clientRepository.findBestClientsSpendingMuchTimeInYear(year, numberOfClients, dateFrom, dateTo);
    }

}
