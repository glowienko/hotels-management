package edu.wut.hotels.management.web.rest.client;


import edu.wut.hotels.management.database.entity.Client;
import edu.wut.hotels.management.service.ClientService;
import edu.wut.hotels.management.web.rest.client.dto.ClientDto;
import edu.wut.hotels.management.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static edu.wut.hotels.management.web.rest.ResourcePaths.API_PATH;
import static edu.wut.hotels.management.web.rest.ResourcePaths.CLIENTS_PATH;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(API_PATH + CLIENTS_PATH)
@RequiredArgsConstructor
public class HotelClientController {


    private final ClientMapper clientMapper;
    private final ClientService clientService;

    @PostMapping
    ResponseEntity<ClientDto> createClient(@RequestBody ClientDto newClient) {
        Client createdClient = clientService.createClient(clientMapper.toClientEntity(newClient));

        return ResponseEntity
                .status(CREATED)
                .body(clientMapper.toClientDto(createdClient));
    }
}
