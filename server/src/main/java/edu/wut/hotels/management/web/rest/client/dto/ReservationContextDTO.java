package edu.wut.hotels.management.web.rest.client.dto;

import edu.wut.hotels.management.web.rest.hotel.dto.HotelDto;
import edu.wut.hotels.management.web.rest.hotel.dto.room.RoomDto;
import lombok.Data;

@Data
public class ReservationContextDTO {
    private RoomDto room;
    private ClientDto client;
    private HotelDto hotel;
    private ReservationInfo info;
}
