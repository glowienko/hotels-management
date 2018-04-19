package com.studia.bd2.hotels.server.database.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity(name = "client_fields")
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class ClientField {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ClientFieldType type;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client;
}
