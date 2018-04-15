package com.studia.bd2.hotels.management.server.database.entity;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private int floor;

    private int number;

    private int capacity;

    @Column(name = "is_premium")
    private boolean isPremium;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "rooms_discounts",
            joinColumns = {@JoinColumn(name = "room_id")},
            inverseJoinColumns = {@JoinColumn(name = "discount_id")}
    )
    private List<Discount> discounts;

    @ManyToMany(mappedBy = "rooms")
    List<Reservation> reservations;
}
