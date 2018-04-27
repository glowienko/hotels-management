package com.studia.bd2.hotels.server.web.rest.hotel.dto.room;

import com.studia.bd2.hotels.server.web.rest.hotel.dto.DiscountDto;
import lombok.Data;

import java.util.List;

@Data
public class RoomDto {

    private Long id;
    private int floor;
    private int number;
    private int capacity;
    private boolean premium;

    private List<RoomReservationDto> reservations;
    private List<PriceDto> prices;
    private List<DiscountDto> discounts;
}
