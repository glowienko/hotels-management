package com.studia.bd2.hotels.server.database.repository;


import com.studia.bd2.hotels.server.database.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByBuildingId(long id);
}
