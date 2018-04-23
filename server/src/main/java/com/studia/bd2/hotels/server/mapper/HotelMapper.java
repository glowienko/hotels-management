package com.studia.bd2.hotels.server.mapper;

import com.studia.bd2.hotels.server.database.entity.Building;
import com.studia.bd2.hotels.server.database.entity.Category;
import com.studia.bd2.hotels.server.database.entity.Discount;
import com.studia.bd2.hotels.server.database.entity.Hotel;
import com.studia.bd2.hotels.server.web.rest.hotel.dto.BuildingDto;
import com.studia.bd2.hotels.server.web.rest.hotel.dto.CategoryDto;
import com.studia.bd2.hotels.server.web.rest.hotel.dto.DiscountDto;
import com.studia.bd2.hotels.server.web.rest.hotel.dto.HotelDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface HotelMapper {


    @Mappings({
            @Mapping(target = "stars", source = "hotel.starsCount"),
    })
    HotelDto toHotelDto(Hotel hotel);

    Hotel toHotelEntity(HotelDto dto);


    @Mappings({
            @Mapping(target = "floors", source = "building.floorsCount"),
    })
    BuildingDto toBuildingDto(Building building);

    Building toBuildingEntity(BuildingDto dto);

    CategoryDto toCategoryDto(Category category);
    Category toCategoryEntity(CategoryDto dto);

    @Mappings({
            @Mapping(target = "percentageHeight", source = "discount.percentage"),
    })
    DiscountDto toDiscountDto(Discount discount);
    Discount toDiscountEntity(DiscountDto dto);

}
