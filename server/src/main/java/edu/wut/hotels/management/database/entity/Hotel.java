package edu.wut.hotels.management.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity(name = "hotels")
@NoArgsConstructor
@Setter
@Getter
public class Hotel {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "img_path")
    private String imgPath;

    @Column(name = "stars_count")
    private Integer starsCount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Building> buildings;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "hotels_discounts",
            joinColumns = {@JoinColumn(name = "hotel_id")},
            inverseJoinColumns = {@JoinColumn(name = "discount_id")}
    )
    private List<Discount> discounts;


}
