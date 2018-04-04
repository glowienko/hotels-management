package com.studia.bd2.hotels.management.server.database.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "hotel")
public class Hotel implements Serializable {
    private static final long serialVersionUID = -8896113256073629672L;

    public Hotel(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String name;

}
