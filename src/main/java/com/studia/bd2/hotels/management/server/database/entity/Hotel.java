package com.studia.bd2.hotels.management.server.database.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String name;

    private String location; //probably it will be nice to create separate class for location infos

    @Column(name = "stars_count")
    private int starsCount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "hotel")
    private Set<Building> buildings = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "hotels_discounts",
            joinColumns = {@JoinColumn(name = "hotel_id")},
            inverseJoinColumns = {@JoinColumn(name = "discount_id")}
    )
    private Set<Discount> discounts;


}
