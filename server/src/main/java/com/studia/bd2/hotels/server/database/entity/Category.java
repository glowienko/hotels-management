package com.studia.bd2.hotels.server.database.entity;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private CategoryName name;

//    @OneToMany(mappedBy = "category")
//    private Set<Hotel> hotels;

//    @OneToMany(mappedBy = "category")
//    private Set<Building> buildings;
}
