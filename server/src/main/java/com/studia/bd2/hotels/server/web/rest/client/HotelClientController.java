package com.studia.bd2.hotels.server.web.rest.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.studia.bd2.hotels.server.web.rest.ResourcePaths.API_PATH;
import static com.studia.bd2.hotels.server.web.rest.ResourcePaths.CLIENTS_PATH;

@RestController
@RequestMapping(API_PATH + CLIENTS_PATH)
public class HotelClientController {
}
