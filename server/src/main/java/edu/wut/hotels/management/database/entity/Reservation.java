package edu.wut.hotels.management.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reservations")
@Table(indexes = {
        @Index(name = "check_in_date_idx", columnList = "check_in_date"),
        @Index(name = "check_out_date_idx", columnList = "check_out_date")
})
public class Reservation implements Identifiable {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(name = "check_in_date")
    private LocalDateTime checkInDate;

    @Column(name = "check_out_date")
    private LocalDateTime checkOutDate;

    @Column(name = "cost", precision = 10, scale = 2)
    private BigDecimal cost;

    @Enumerated(EnumType.STRING)
    private ReservationState state;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "rooms_reservations",
            joinColumns = {@JoinColumn(name = "reservation_id")},
            inverseJoinColumns = {@JoinColumn(name = "room_id")}
    )
    private List<Room> rooms;
}
