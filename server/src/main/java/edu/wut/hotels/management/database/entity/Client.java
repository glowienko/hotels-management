package edu.wut.hotels.management.database.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Getter @Setter @EqualsAndHashCode
@Entity(name = "clients")
public class Client implements Identifiable {

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

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<ClientField> clientFields;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", conference=" + conference +
                ", clientFields=" + Identifiable.extractIds(clientFields) +
                '}';
    }
}
