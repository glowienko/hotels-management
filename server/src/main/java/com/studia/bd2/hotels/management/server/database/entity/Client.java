package com.studia.bd2.hotels.management.server.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity(name = "clients")
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Client {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "conference")
    private Boolean conference;

    @OneToMany(mappedBy = "client")
    private Set<ClientField> clientFields;
}
