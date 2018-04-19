package com.studia.bd2.hotels.server.database.repository;

import com.studia.bd2.hotels.server.database.entity.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
}
