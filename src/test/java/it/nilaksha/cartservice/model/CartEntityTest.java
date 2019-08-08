package it.nilaksha.cartservice.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class CartEntityTest {

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier
                .forClass(CartEntity.class)
                .suppress(Warning.STRICT_INHERITANCE)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }

    @Test
    public void testToString() {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setCode("PR01");
        productEntity.setName("Voucher");
        productEntity.setDescription("Voucher to be used with any purchase");

        CartEntity cartEntity = new CartEntity();
        cartEntity.setId(1L);
        cartEntity.setProductEntities(Collections.singleton(productEntity));
        cartEntity.setExpirationIn(300L);

        assertEquals("CartEntity(id=1, productEntities=[ProductEntity(id=1, code=PR01, name=Voucher, description=Voucher to be used with any purchase)], expirationIn=300)", cartEntity.toString());

    }

}