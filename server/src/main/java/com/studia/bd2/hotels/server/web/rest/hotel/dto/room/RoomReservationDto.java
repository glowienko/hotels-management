package com.studia.bd2.hotels.server.web.rest.hotel.dto.room;

import com.studia.bd2.hotels.server.database.entity.ReservationState;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoomReservationDto {

    private Long id;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private Long cost;
    private ReservationState state;
}
