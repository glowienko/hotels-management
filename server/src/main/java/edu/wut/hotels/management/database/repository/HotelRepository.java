package edu.wut.hotels.management.database.repository;

import edu.wut.hotels.management.database.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findAllByLocationAndStarsCount(String location, int starsCount);
}
