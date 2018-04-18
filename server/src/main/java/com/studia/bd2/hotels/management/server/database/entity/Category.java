package com.studia.bd2.hotels.management.server.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity(name = "categories")
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Category {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private CategoryName name;

    @OneToMany(mappedBy = "category")
    private Set<Hotel> hotels;

    @OneToMany(mappedBy = "category")
    private Set<Building> buildings;
}
