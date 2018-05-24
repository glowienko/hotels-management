package edu.wut.hotels.management.database;

import com.github.javafaker.Faker;
import edu.wut.hotels.management.database.entity.Client;
import edu.wut.hotels.management.database.entity.ClientField;
import edu.wut.hotels.management.database.entity.ClientFieldType;
import edu.wut.hotels.management.database.repository.ClientRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Generator {

    private static Faker faker = new Faker();

    @Autowired
    private ClientRepository clientRepository;

    private Client randomClient() {
        ClientField address = new ClientField();
        address.setId(faker.number().randomNumber());
        address.setType(ClientFieldType.ADDRESS);
        address.setValue(faker.address().fullAddress());

        ClientField pesel = new ClientField();
        pesel.setId(faker.number().randomNumber());
        pesel.setType(ClientFieldType.PESEL);
        pesel.setValue(faker.number().digits(11));

        Client client = new Client();
        client.setId(faker.number().randomNumber());
        client.setFirstName(faker.name().firstName());
        client.setLastName(faker.name().lastName());
        client.setEmail(client.getFirstName() + "." + client.getLastName() + "@gmail.com");
        client.setPhone(faker.phoneNumber().cellPhone());
        client.setConference(faker.bool().bool());

        address.setClient(client);
        pesel.setClient(client);
        client.setClientFields(Lists.newArrayList(address, pesel));

        return client;
    }

    @Test
    public void contextLoads() {
        clientRepository.save(randomClient());
        clientRepository.flush();
    }
}
