package edu.wut.hotels.management.database.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity(name = "prices")
@Table(indexes = {
        @Index(name = "start_date_idx", columnList = "start_date"),
        @Index(name = "end_date_idx", columnList = "end_date"),
        @Index(name = "value_idx", columnList = "value")
})
public class Price implements Identifiable {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "value", precision = 6, scale = 2)
    private BigDecimal value;

    @ManyToMany(mappedBy = "prices")
    private List<Room> rooms;
}
