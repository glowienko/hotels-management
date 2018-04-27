package com.studia.bd2.hotels.server.web.rest.client;

import com.studia.bd2.hotels.server.database.entity.Client;
import com.studia.bd2.hotels.server.mapper.ClientMapper;
import com.studia.bd2.hotels.server.service.ClientService;
import com.studia.bd2.hotels.server.web.rest.client.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.studia.bd2.hotels.server.web.rest.ResourcePaths.API_PATH;
import static com.studia.bd2.hotels.server.web.rest.ResourcePaths.CLIENTS_PATH;
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
