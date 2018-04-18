package com.studia.bd2.hotels.management.server.database.entity;

import javax.persistence.*;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "buildings_discounts",
            joinColumns = {@JoinColumn(name = "building_id")},
            inverseJoinColumns = {@JoinColumn(name = "discount_id")}
    )
    private List<Discount> discounts;
}