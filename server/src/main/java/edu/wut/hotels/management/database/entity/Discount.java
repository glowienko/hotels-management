package edu.wut.hotels.management.database.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity(name = "discounts")
public class Discount implements Identifiable {

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
