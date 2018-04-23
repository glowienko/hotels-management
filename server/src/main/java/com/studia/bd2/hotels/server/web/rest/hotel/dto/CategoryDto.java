package com.studia.bd2.hotels.server.web.rest.hotel.dto;

import com.studia.bd2.hotels.server.database.entity.CategoryName;
import lombok.Data;

@Data
public class CategoryDto {
    private CategoryName name;
}
