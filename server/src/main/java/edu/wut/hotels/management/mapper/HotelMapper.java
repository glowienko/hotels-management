package edu.wut.hotels.management.mapper;

import edu.wut.hotels.management.database.entity.Building;
import edu.wut.hotels.management.database.entity.Category;
import edu.wut.hotels.management.database.entity.Discount;
import edu.wut.hotels.management.database.entity.Hotel;
import edu.wut.hotels.management.web.rest.hotel.dto.BuildingDto;
import edu.wut.hotels.management.web.rest.hotel.dto.CategoryDto;
import edu.wut.hotels.management.web.rest.hotel.dto.DiscountDto;
import edu.wut.hotels.management.web.rest.hotel.dto.HotelDto;
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
