package com.studia.bd2.hotels.server.database.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity(name = "discounts")
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Discount {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "percentage")
    private int percentage;

    @ManyToMany(mappedBy = "discounts")
    private Set<Hotel> hotels;

    @ManyToMany(mappedBy = "discounts")
    private Set<Room> rooms;

    @ManyToMany(mappedBy = "discounts")
    private Set<Building> buildings;
}
