package it.nilaksha.cartservice.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductEntityTest {

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier
                .forClass(ProductEntity.class)
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

        assertEquals("ProductEntity(id=1, code=PR01, name=Voucher, description=Voucher to be used with any purchase)", productEntity.toString());

    }

}