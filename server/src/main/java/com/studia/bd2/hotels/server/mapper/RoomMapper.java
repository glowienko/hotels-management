package com.studia.bd2.hotels.server.mapper;

import com.studia.bd2.hotels.server.database.entity.Discount;
import com.studia.bd2.hotels.server.database.entity.Price;
import com.studia.bd2.hotels.server.database.entity.Reservation;
import com.studia.bd2.hotels.server.database.entity.Room;
import com.studia.bd2.hotels.server.web.rest.hotel.dto.DiscountDto;
import com.studia.bd2.hotels.server.web.rest.hotel.dto.room.PriceDto;
import com.studia.bd2.hotels.server.web.rest.hotel.dto.room.RoomDto;
import com.studia.bd2.hotels.server.web.rest.hotel.dto.room.RoomReservationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface RoomMapper {


    RoomDto toRoomDto(Room room);

    Room toRoomEntity(RoomDto dto);


    @Mappings({
            @Mapping(target = "percentageHeight", source = "discount.percentage"),
    })
    DiscountDto toDiscountDto(Discount discount);

    Discount toDiscountEntity(DiscountDto dto);


    PriceDto toPriceDto(Price price);
    Price toPriceEntity(PriceDto dto);

    RoomReservationDto toRootReservationDto(Reservation reservation);
    Reservation toReservationEntity(RoomReservationDto dto);
}
