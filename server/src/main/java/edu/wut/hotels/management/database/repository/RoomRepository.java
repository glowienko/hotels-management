package edu.wut.hotels.management.database.repository;

import edu.wut.hotels.management.database.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByBuildingId(long id);

    @Query(value = "SELECT rooms.* " +
            "FROM hotels" +
            "  JOIN buildings ON hotels.id = buildings.hotel_id" +
            "  JOIN rooms on buildings.id = rooms.building_id" +
            "WHERE" +
            "  rooms.id NOT IN " +
            "    SELECT rooms.id" +
            "    FROM rooms" +
            "      JOIN rooms_reservations ON rooms.id = rooms_reservations.room_id" +
            "      JOIN reservations ON rooms_reservations.reservation_id = reservations.id" +
            "    WHERE" +
            "      reservations.check_in_date > ?2  AND reservations.check_out_date < ?3" +
            "      DATE_ADD(CURRENT_DATE, INTERVAL 10 DAY) BETWEEN reservations.check_in_date AND reservations.check_out_date" +
            "  ) AND1" +
            "  hotels.id = ;", nativeQuery = true)
    List<Room> findFreeRoomsForHotelAndParams(Long hotelId, LocalDateTime startDate, LocalDateTime endDate, BigDecimal price);
}
