package com.studia.bd2.hotels.management.server.database.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    private int percentage;

    @ManyToMany(mappedBy = "discounts")
    private Set<Hotel> hotels;
}
