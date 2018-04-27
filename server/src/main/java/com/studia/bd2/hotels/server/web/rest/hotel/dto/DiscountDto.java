package com.studia.bd2.hotels.server.web.rest.hotel.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiscountDto {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int percentageHeight;
}
