package com.studia.bd2.hotels.management.server.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity(name = "rooms")
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Room {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "floor")
    private int floor;

    @Column(name = "number")
    private int number;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "premium")
    private boolean premium;

    @ManyToOne
    @JoinColumn(name="building_id", nullable=false)
    private Building building;

    @ManyToMany(mappedBy = "rooms")
    private List<Reservation> reservations;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "rooms_prices",
            joinColumns = {@JoinColumn(name = "room_id")},
            inverseJoinColumns = {@JoinColumn(name = "price_id")}
    )
    private List<Price> prices;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "rooms_discounts",
            joinColumns = {@JoinColumn(name = "room_id")},
            inverseJoinColumns = {@JoinColumn(name = "discount_id")}
    )
    private List<Discount> discounts;
}
