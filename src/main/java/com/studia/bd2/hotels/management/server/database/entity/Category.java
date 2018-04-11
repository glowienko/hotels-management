package com.studia.bd2.hotels.management.server.database.entity;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;


    @Enumerated(EnumType.STRING)
    private CategoryName name;

    @OneToMany(mappedBy = "category")
    private Set<Hotel> hotels;
}
