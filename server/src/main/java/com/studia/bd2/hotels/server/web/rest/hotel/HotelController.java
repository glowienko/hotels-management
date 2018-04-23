package com.studia.bd2.hotels.server.web.rest.hotel;

import com.studia.bd2.hotels.server.database.entity.Hotel;
import com.studia.bd2.hotels.server.mapper.HotelMapper;
import com.studia.bd2.hotels.server.service.HotelService;
import com.studia.bd2.hotels.server.web.rest.hotel.dto.HotelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.studia.bd2.hotels.server.web.rest.ResourcePaths.API_PATH;
import static com.studia.bd2.hotels.server.web.rest.ResourcePaths.HOTELS_PATH;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(API_PATH + HOTELS_PATH)
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;
    private final HotelMapper hotelMapper;

    @GetMapping
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();

        return ResponseEntity.ok(
                hotels.stream()
                        .map(hotelMapper::toHotelDto)
                        .collect(toList())
        );
    }

}
