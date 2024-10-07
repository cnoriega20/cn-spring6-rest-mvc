package com.example.cnspring6restmvc.entities;

import com.example.cnspring6restmvc.model.BeerStyle;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Beer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @Version
    private Long version;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(length = 50)
    private String beerName;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "beer_style", columnDefinition = "smallint")
    private BeerStyle beerStyle;

    @NotNull
    @NotBlank
    @Size(max = 250)
    private String upc;
    private Integer quantityOnHand;

    @NotNull
    private BigDecimal price;

    @OneToMany(mappedBy = "beer")
    @ToString.Exclude
    private Set<BeerOrderLine> beerOrderLines;

    @ManyToMany
    @JoinTable(name = "beer_category",
            joinColumns =
    @JoinColumn(name = "beer_id"),
            inverseJoinColumns =
    @JoinColumn(name ="category_id"))
    @ToString.Exclude
    private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category) {
        this.categories.add(category);
        category.getBeers().add(this);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
        category.getBeers().remove(category);
    }

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;
}
