package com.studia.bd2.hotels.management.server.database.repositories;

import com.studia.bd2.hotels.management.server.database.entities.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
}
