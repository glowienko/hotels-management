package com.studia.bd2.hotels.management.server.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity(name = "hotels")
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Hotel {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location; //probably it will be nice to create separate class for location infos

    @Column(name = "stars_count")
    private Integer starsCount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "hotel")
    private Set<Building> buildings;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "hotels_discounts",
            joinColumns = {@JoinColumn(name = "hotel_id")},
            inverseJoinColumns = {@JoinColumn(name = "discount_id")}
    )
    private List<Discount> discounts;


}
