package com.example.cnspring6restmvc.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Builder
@Entity
public class BeerOrder {

    public BeerOrder(UUID id, Long version, Timestamp createDate, Timestamp lastModifiedDate, String customerRef,
                     Customer customer, Set<BeerOrderLine> beerOrderLines, BeerOrderShipment beerOrderShipment) {
        this.id = id;
        this.version = version;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
        this.customerRef = customerRef;
        this.setCustomer(customer);
        this.beerOrderLines = beerOrderLines;
        this.beerOrderShipment = beerOrderShipment;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name ="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36 , columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @Version
    private Long version;

    @CreationTimestamp
    @Column (name = "created_date", updatable = false)
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp  lastModifiedDate;

    public boolean isNew(){
        return this.id == null;
    }

    private String customerRef;

    @ManyToOne
    private Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
        customer.getBeerOrders().add(this);
    }

    @OneToMany(mappedBy = "beerOrder")
    private Set<BeerOrderLine> beerOrderLines;

    @OneToOne
    private BeerOrderShipment beerOrderShipment;
}
