package it.nilaksha.cartservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "cart_product_mapping",
            joinColumns = {@JoinColumn(name = "cart_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Collection<ProductEntity> productEntities;

    private Long expirationIn;

}
