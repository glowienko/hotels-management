package com.studia.bd2.hotels.server.web.rest.hotel.dto;

import lombok.Data;

import java.util.Set;

@Data
public class HotelDto {
    private Long id;
    private String name;
    private String location;
    private Integer stars;
    private CategoryDto category;
    private Set<BuildingDto> buildings;
}
