package com.studia.bd2.hotels.server.service;

import com.studia.bd2.hotels.server.database.entity.Hotel;
import com.studia.bd2.hotels.server.database.entity.Room;
import com.studia.bd2.hotels.server.database.repository.HotelRepository;
import com.studia.bd2.hotels.server.database.repository.RoomRepository;
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


    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public List<Room> findRoomsByBuildingId(long buildingId) {
        return roomRepository.findByBuildingId(buildingId);
    }


}
