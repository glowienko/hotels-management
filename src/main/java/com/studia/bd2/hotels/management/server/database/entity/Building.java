package com.studia.bd2.hotels.management.server.database.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity(name = "buildings")
public class Building {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String name;

    @Column(name = "floors_count")
    private int floorsCount;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
