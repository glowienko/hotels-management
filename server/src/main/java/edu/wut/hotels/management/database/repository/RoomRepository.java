package edu.wut.hotels.management.database.repository;

import edu.wut.hotels.management.database.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByBuildingId(long id);

}
