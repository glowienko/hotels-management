package edu.wut.hotels.management.web.rest.hotel.dto.room;

import edu.wut.hotels.management.web.rest.hotel.dto.DiscountDto;
import lombok.Data;

import java.util.List;

@Data
public class RoomDto {

    private Long id;
    private int floor;
    private int number;
    private int capacity;
    private boolean premium;
    private String imgPath;

    private List<RoomReservationDto> reservations;
    private List<PriceDto> prices;
    private List<DiscountDto> discounts;
}
