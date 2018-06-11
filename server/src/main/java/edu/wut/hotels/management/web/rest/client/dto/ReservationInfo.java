package edu.wut.hotels.management.web.rest.client.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReservationInfo {
    private String city;
    private int hotelStars;
    private int maxPrice;
    private BigDecimal totalReservationCost;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
