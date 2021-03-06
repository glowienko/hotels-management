package edu.wut.hotels.management.database.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Getter @Setter @EqualsAndHashCode
@Entity(name = "client_fields")
public class ClientField implements Identifiable {

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

    @Override
    public String toString() {
        return "ClientField{" +
                "id=" + id +
                ", type=" + type +
                ", value='" + value + '\'' +
                ", client=" + client.getId() +
                '}';
    }
}
