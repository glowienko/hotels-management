package edu.wut.hotels.management.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity(name = "buildings")
@NoArgsConstructor
@Setter
@Getter
public class Building {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "floors_count")
    private int floorsCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "building")
    private List<Room> rooms;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "buildings_discounts",
            joinColumns = {@JoinColumn(name = "building_id")},
            inverseJoinColumns = {@JoinColumn(name = "discount_id")}
    )
    private List<Discount> discounts;
}
