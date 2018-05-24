package edu.wut.hotels.management.mapper;

import edu.wut.hotels.management.database.entity.Discount;
import edu.wut.hotels.management.database.entity.Price;
import edu.wut.hotels.management.database.entity.Reservation;
import edu.wut.hotels.management.database.entity.Room;
import edu.wut.hotels.management.web.rest.hotel.dto.DiscountDto;
import edu.wut.hotels.management.web.rest.hotel.dto.room.PriceDto;
import edu.wut.hotels.management.web.rest.hotel.dto.room.RoomDto;
import edu.wut.hotels.management.web.rest.hotel.dto.room.RoomReservationDto;
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
