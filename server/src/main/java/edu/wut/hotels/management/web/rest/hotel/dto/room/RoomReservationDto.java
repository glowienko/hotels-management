package edu.wut.hotels.management.web.rest.hotel.dto.room;

import edu.wut.hotels.management.database.entity.ReservationState;
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
