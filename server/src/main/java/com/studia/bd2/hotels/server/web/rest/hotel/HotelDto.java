package com.studia.bd2.hotels.server.web.rest.hotel;

import com.studia.bd2.hotels.server.database.entity.Building;
import com.studia.bd2.hotels.server.database.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class HotelDto {
    private Long id;
    private String name;
    private String location;
    private Integer starsCount;
    private Category category;
    private Set<Building> buildings;
}
