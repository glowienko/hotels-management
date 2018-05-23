package edu.wut.hotels.management.service;

import edu.wut.hotels.management.database.entity.Hotel;
import edu.wut.hotels.management.database.entity.Room;
import edu.wut.hotels.management.database.repository.HotelRepository;
import edu.wut.hotels.management.database.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;


    @Transactional(readOnly = true)
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Room> findRoomsByBuildingId(long buildingId) {
        return roomRepository.findByBuildingId(buildingId);
    }


}
