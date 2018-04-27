package com.studia.bd2.hotels.server.web.rest.hotel.dto.room;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PriceDto {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal value;
}
