package edu.wut.hotels.management.database.repository;

import edu.wut.hotels.management.database.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
