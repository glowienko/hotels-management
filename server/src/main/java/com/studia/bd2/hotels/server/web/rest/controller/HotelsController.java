package com.studia.bd2.hotels.server.web.rest.controller;

import com.studia.bd2.hotels.server.database.entity.Hotel;
import com.studia.bd2.hotels.server.database.repository.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.studia.bd2.hotels.server.web.rest.ResourcePaths.API_PATH;

@RestController
@RequestMapping(API_PATH)
@AllArgsConstructor
public class HotelsController {

    //TODO: it should be service injected here
    private HotelRepository hotelRepository;

    //TODO: remove this later
    @GetMapping
    public String test() {
        Hotel hotel = new Hotel();
        hotel.setName("Star Beach");
        hotel.setStarsCount(5);
        return hotel.toString();
    }
}
