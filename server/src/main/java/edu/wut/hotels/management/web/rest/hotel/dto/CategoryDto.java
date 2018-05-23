package edu.wut.hotels.management.web.rest.hotel.dto;

import edu.wut.hotels.management.database.entity.CategoryName;
import lombok.Data;

@Data
public class CategoryDto {
    private CategoryName name;
}
