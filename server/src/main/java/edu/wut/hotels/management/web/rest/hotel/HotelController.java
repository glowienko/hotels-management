package edu.wut.hotels.management.web.rest.hotel;


import edu.wut.hotels.management.database.entity.Hotel;
import edu.wut.hotels.management.database.entity.Room;
import edu.wut.hotels.management.mapper.HotelMapper;
import edu.wut.hotels.management.mapper.RoomMapper;
import edu.wut.hotels.management.service.HotelService;
import edu.wut.hotels.management.web.rest.hotel.dto.HotelDto;
import edu.wut.hotels.management.web.rest.hotel.dto.room.RoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import static edu.wut.hotels.management.web.rest.ResourcePaths.*;
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

    @GetMapping(HOTELS_PATH + ROOMS_PATH)
    public ResponseEntity<List<HotelDto>> getHotelsByUserSelection(String location, int stars, String startDate, String endDate, int price){

        List<Hotel> selectedHotels = hotelService.getHotelsByUserSelection(location, stars, startDate, endDate, BigDecimal.valueOf(price));

        return ResponseEntity.ok(
                selectedHotels.stream()
                        .map(hotelMapper::toHotelDto)
                        .collect(toList())
        );
    }


    @GetMapping(HOTELS_PATH + ID_PATH_PARAM + ROOMS_PATH)
    public ResponseEntity<List<RoomDto>> getRoomsInHotelByUserSelection(@PathVariable(value = "id") String hotelId, String startDate, String endDate, int price){
        int hId = Integer.parseInt(hotelId);

        List<Room> selectedHotels = hotelService.getRoomsInHotelByUserSelection((long) hId, startDate, endDate, BigDecimal.valueOf(price));

        return ResponseEntity.ok(
                selectedHotels.stream()
                        .map(roomMapper::toRoomDto)
                        .collect(toList())
        );
    }
}
