package edu.wut.hotels.management.web.rest.hotel.dto;

import lombok.Data;

import java.util.List;

@Data
public class BuildingDto {

    private Long id;

    private String name;

    private int floors;

    private CategoryDto category;

    private List<DiscountDto> discounts;
}
