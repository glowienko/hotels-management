package edu.wut.hotels.management.web.rest.hotel.dto;

import lombok.Data;

import java.util.Set;

@Data
public class HotelDto {
    private Long id;
    private String name;
    private String location;
    private String imgPath;
    private Integer stars;
    private CategoryDto category;
    private Set<BuildingDto> buildings;
}
