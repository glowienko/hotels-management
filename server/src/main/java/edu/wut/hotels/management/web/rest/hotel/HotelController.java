package edu.wut.hotels.management.web.rest.hotel;


import edu.wut.hotels.management.database.entity.Discount;
import edu.wut.hotels.management.database.entity.Hotel;
import edu.wut.hotels.management.database.entity.Price;
import edu.wut.hotels.management.database.entity.Reservation;
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
import java.math.MathContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static edu.wut.hotels.management.web.rest.ResourcePaths.API_PATH;
import static edu.wut.hotels.management.web.rest.ResourcePaths.BUILDINGS_PATH;
import static edu.wut.hotels.management.web.rest.ResourcePaths.HOTELS_PATH;
import static edu.wut.hotels.management.web.rest.ResourcePaths.ID_PATH_PARAM;
import static edu.wut.hotels.management.web.rest.ResourcePaths.ROOMS_PATH;
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
        LocalDateTime sd = dateStringToLocalDateTime(startDate);
        LocalDateTime ed = dateStringToLocalDateTime(endDate);

        List<Hotel> selectedHotels = hotelService.getHotelsByUserSelection(location, stars, sd, ed, BigDecimal.valueOf(price));
//        List<Hotel> selectedHotels = hotelService.getAllHotels();

        return ResponseEntity.ok(
                selectedHotels.stream()
                        .map(hotelMapper::toHotelDto)
                        .collect(toList())
        );
    }


    @GetMapping(HOTELS_PATH + ID_PATH_PARAM + ROOMS_PATH)
    public ResponseEntity<List<RoomDto>> getRoomsInHotelByUserSelection(@PathVariable(value = "id") String hotelId, String startDate, String endDate, int price){
        int hId = Integer.parseInt(hotelId);
        LocalDateTime sd = dateStringToLocalDateTime(startDate);
        LocalDateTime ed = dateStringToLocalDateTime(endDate);


//        Optional<Hotel> hotel = hotelService.findHotelById(hId);
//        List<Room> selectedRooms = hotelService.findRoomsByBuildingId(hotel.get().getBuildings().get(0).getId());
        List<Room> selectedRooms = hotelService.getRoomsInHotelByUserSelection((long) hId, sd, ed, BigDecimal.valueOf(price));
        selectedRooms = checkPricesAndReservations(selectedRooms, sd, ed, price);

        return ResponseEntity.ok(
                selectedRooms.stream()
                        .map(roomMapper::toRoomDto)
                        .collect(toList())
        );
    }

    private LocalDateTime dateStringToLocalDateTime(String dateStr){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateStr += " 00:00:00";

       return LocalDateTime.parse(dateStr, format);
    }

    private List<Room> checkPricesAndReservations(List<Room> selectedRooms, LocalDateTime startDate, LocalDateTime endDate, int price){
        List<Room> roomsToRemove = new ArrayList<>();
        for(Room room : selectedRooms){
            List<Price> pricesToRemove = new ArrayList<>();
            List<Price> prices = room.getPrices();
            for (Price p : prices){
                List<Discount> discountsToRemove = new ArrayList<>();
                List<Discount> discounts = room.getDiscounts();
                if((p.getStartDate().compareTo(startDate) < 1) && (p.getEndDate().compareTo(endDate) > -1)) {
                    for(Discount d : discounts){
                        if( (d.getStartDate().compareTo(startDate) < 1) && (d.getEndDate().compareTo(endDate) > -1) ){
                            p.setValue(p.getValue().multiply(BigDecimal.valueOf( d.getPercentage()/100) ));
                        }
                        else {
                            discountsToRemove.add(d);
                        }
                    }
                }
                else {
                    pricesToRemove.add(p);
                }

                for (Discount d :discountsToRemove){
                    discounts.remove(d);
                }
                room.setDiscounts(discounts);
            }
            for(Price p : pricesToRemove){
                prices.remove(p);
            }
            //data in db doesn't cover all dates, but price must exists anyway
            if(prices.size() == 0) {
                Random generator = new Random();
                Price testPrice = new Price();
                room.setDiscounts(new ArrayList<>());
                testPrice.setValue(BigDecimal.valueOf(generator.nextDouble() * price).round(new MathContext(5)));
                prices.add(testPrice);
            }

            for (Reservation res: room.getReservations()) {
                if( !(res.getCheckInDate().isAfter(endDate) || res.getCheckOutDate().isBefore(startDate)) ){
                    roomsToRemove.add(room);
                }
            }
        }
        for (Room r : roomsToRemove){
            selectedRooms.remove(r);
        }

        return selectedRooms;
    }
}
