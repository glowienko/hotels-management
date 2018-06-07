package edu.wut.hotels.management.database.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity(name = "rooms")
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

    @Column(name = "imgPath")
    private boolean imgPath;

    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)
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
