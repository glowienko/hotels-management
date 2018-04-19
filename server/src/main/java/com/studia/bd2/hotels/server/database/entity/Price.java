package com.studia.bd2.hotels.server.database.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity(name = "prices")
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Price {
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
