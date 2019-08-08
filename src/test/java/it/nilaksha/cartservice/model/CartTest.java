package it.nilaksha.cartservice.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier
                .forClass(Cart.class)
                .withIgnoredFields("id")
                .suppress(Warning.STRICT_INHERITANCE)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }

    @Test
    public void testToString() {

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setProductIds(Collections.singleton(1L));
        cart.setExpirationIn(300L);

        assertEquals("Cart(id=1, productIds=[1], expirationIn=300)", cart.toString());

    }

}