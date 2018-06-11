package edu.wut.hotels.management.web.rest.client;


import edu.wut.hotels.management.database.entity.Client;
import edu.wut.hotels.management.database.entity.Reservation;
import edu.wut.hotels.management.database.entity.ReservationState;
import edu.wut.hotels.management.mapper.ClientMapper;
import edu.wut.hotels.management.mapper.RoomMapper;
import edu.wut.hotels.management.service.ClientService;
import edu.wut.hotels.management.service.ReservationService;
import edu.wut.hotels.management.web.rest.client.dto.ClientDto;
import edu.wut.hotels.management.web.rest.client.dto.ReservationContextDTO;
import edu.wut.hotels.management.web.rest.hotel.dto.room.RoomReservationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static edu.wut.hotels.management.web.rest.ResourcePaths.API_PATH;
import static edu.wut.hotels.management.web.rest.ResourcePaths.CLIENTS_PATH;
import static edu.wut.hotels.management.web.rest.ResourcePaths.RESERVATIONS;
import static java.util.Collections.singletonList;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(API_PATH + CLIENTS_PATH)
@RequiredArgsConstructor
public class HotelClientController {

    private final ClientMapper clientMapper;
    private final RoomMapper roomMapper;
    private final ClientService clientService;
    private final ReservationService reservationService;

    @PostMapping
    ResponseEntity<ClientDto> createClient(@RequestBody ClientDto newClient) {
        Optional<Client> clientOptional = clientService.getClientByEmail(newClient.getEmail());

        return clientOptional.map(client -> ResponseEntity.status(CREATED).body(clientMapper.toClientDto(client)))
                .orElseGet(() -> createNewClient(newClient));
    }

    @PostMapping(RESERVATIONS)
    ResponseEntity<RoomReservationDto> makeReservation(@RequestBody ReservationContextDTO contextDTO) {

        LocalDateTime checkInDate = LocalDateTime.of(contextDTO.getInfo().getCheckInDate(), LocalTime.now());
        LocalDateTime checkOutDate = LocalDateTime.of(contextDTO.getInfo().getCheckOutDate(), LocalTime.now());

        Reservation reservation = Reservation.builder()
                .checkInDate(checkInDate)
                .checkOutDate(checkOutDate)
                .state(ReservationState.MADE)
                .client(clientMapper.toClientEntity(contextDTO.getClient()))
                .cost(contextDTO.getInfo().getTotalReservationCost())
                .rooms(singletonList(roomMapper.toRoomEntity(contextDTO.getRoom())))
                .build();

        RoomReservationDto madeReservation = roomMapper.toRoomReservationDto(reservationService.makeReservation(reservation));
        return ResponseEntity.status(CREATED).body(madeReservation);
    }


    private ResponseEntity<ClientDto> createNewClient(ClientDto client) {
        Client createdClient = clientService.createClient(clientMapper.toClientEntity(client));
        return ResponseEntity
                .status(CREATED)
                .body(clientMapper.toClientDto(createdClient));
    }

    @GetMapping("best_cost")
    public List<ClientDto> findBestClientsInYear(@RequestParam int year, @RequestParam int numberOfClients) {
        return clientService.findBestClientsInYear(year, numberOfClients)
                .stream()
                .map(clientMapper::toClientDto)
                .collect(Collectors.toList());
    }

    @GetMapping("best_time")
    public List<ClientDto> findBestClientsSpendingMuchTimeInYear(@RequestParam int year, @RequestParam int numberOfClients,
                                                                 @RequestParam String dateFrom, @RequestParam String dateTo) {
        return clientService.findBestClientsSpendingMuchTimeInYear(year, numberOfClients, dateFrom, dateTo)
                .stream()
                .map(clientMapper::toClientDto)
                .collect(Collectors.toList());
    }

}
