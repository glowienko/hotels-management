package com.studia.bd2.hotels.management.server.web.rest.controller;

import com.studia.bd2.hotels.management.server.database.repository.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.studia.bd2.hotels.management.server.web.rest.ResourcePaths.API_PATH;

@RestController
@RequestMapping(API_PATH)
@AllArgsConstructor
public class HotelsController {

    //TODO: it should be service injected here
    private HotelRepository hotelRepository;

    //TODO: remove this later
    @GetMapping
    public String test() {
//        hotelRepository.save(new Hotel("testHotel2"));
        return "HUEHU";
    }
}
