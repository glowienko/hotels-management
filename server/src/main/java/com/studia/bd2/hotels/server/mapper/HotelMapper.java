package com.studia.bd2.hotels.server.mapper;

import com.studia.bd2.hotels.server.database.entity.Hotel;
import com.studia.bd2.hotels.server.web.rest.hotel.HotelDto;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface HotelMapper {


    HotelDto toHotelDto(Hotel hotel);
    Hotel toHotelEntity(HotelDto hotelDto);
}
