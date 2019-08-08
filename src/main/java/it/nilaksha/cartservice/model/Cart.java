package it.nilaksha.cartservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Collection;

@Data
public class Cart implements Serializable {

    @EqualsAndHashCode.Exclude
    private Long id;

    private Collection<Long> productIds;

    private Long expirationIn;

}
