package com.studia.bd2.hotels.server.web.rest.hotel;

import com.studia.bd2.hotels.server.database.entity.Hotel;
import com.studia.bd2.hotels.server.database.entity.Room;
import com.studia.bd2.hotels.server.mapper.HotelMapper;
import com.studia.bd2.hotels.server.mapper.RoomMapper;
import com.studia.bd2.hotels.server.service.HotelService;
import com.studia.bd2.hotels.server.web.rest.hotel.dto.HotelDto;
import com.studia.bd2.hotels.server.web.rest.hotel.dto.room.RoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.studia.bd2.hotels.server.web.rest.ResourcePaths.*;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(value = API_PATH, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;
    private final HotelMapper hotelMapper;
    private final RoomMapper roomMapper;

    @GetMapping(HOTELS_PATH)
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();

        return ResponseEntity.ok(
                hotels.stream()
                        .map(hotelMapper::toHotelDto)
                        .collect(toList())
        );
    }

    @GetMapping(BUILDINGS_PATH + ID_PATH_PARAM + ROOMS_PATH)
    public ResponseEntity<List<RoomDto>> getRoomsByBuildingId(@PathVariable("id") Long buildingId) {
        List<Room> rooms = hotelService.findRoomsByBuildingId(buildingId);

        return ResponseEntity.ok(
                rooms.stream()
                        .map(roomMapper::toRoomDto)
                        .collect(toList())
        );
    }

}
